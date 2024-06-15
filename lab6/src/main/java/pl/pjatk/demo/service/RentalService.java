package pl.pjatk.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.demo.exception.BadRequestException;
import pl.pjatk.demo.exception.MovieNotFoundException;
import pl.pjatk.demo.model.Movie;

@Service
public class RentalService {

    private final RestTemplate restTemplate;

    @Autowired
    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovie(Long id) {
        try {
            return restTemplate.getForObject("http://localhost:8081/movies/" + id, Movie.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MovieNotFoundException("MovieService oraz RentalService zwracają 404, nie znaleziono filmu z ID: " + id + " (IC0006)");
            } else if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new BadRequestException("Bad request for movie id: " + id + " (IC0007)");
            }
            throw e;
        }
    }

    public Movie returnMovie(Long id) {
        try {
            restTemplate.put("http://localhost:8081/movies/" + id + "/available", null);
            return getMovie(id);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MovieNotFoundException("MovieService oraz RentalService zwracają 404, nie znaleziono filmu z ID: " + id + " (IC0008)");
            }
            throw e;
        }
    }

    public Movie rentMovie(Long id) {
        try {
            restTemplate.put("http://localhost:8081/movies/" + id + "/unavailable", null);
            return getMovie(id);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new MovieNotFoundException("MovieService oraz RentalService zwracają 404, nie znaleziono filmu z ID: " + id + " (IC0009)");
            }
            throw e;
        }
    }
}