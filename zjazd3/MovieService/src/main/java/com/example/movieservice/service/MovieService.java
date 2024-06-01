package com.example.movieservice.service;

import com.example.movieservice.exception.MovieNotFoundException;
import com.example.movieservice.model.Movie;
import com.example.movieservice.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) throws MovieNotFoundException {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public ResponseEntity<Movie> addMovie(Movie movie) {
        if (movie.getName() == null || movie.getCategory() == null || movie.getDirector() == null || movie.getReleaseYear() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Movie newMovie = movieRepository.save(movie);
        return new ResponseEntity<>(newMovie, HttpStatus.OK);
    }

    public ResponseEntity<Movie> updateMovie(Long id, Movie updatedMovie) throws MovieNotFoundException {
        if (updatedMovie.getName() == null || updatedMovie.getCategory() == null || updatedMovie.getDirector() == null || updatedMovie.getReleaseYear() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setName(updatedMovie.getName());
                    movie.setCategory(updatedMovie.getCategory());
                    movie.setDirector(updatedMovie.getDirector());
                    movie.setReleaseYear(updatedMovie.getReleaseYear());
                    Movie savedMovie = movieRepository.save(movie);
                    return new ResponseEntity<>(savedMovie, HttpStatus.OK);
                })
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public ResponseEntity<Void> deleteMovie(Long id) throws MovieNotFoundException {
        Movie movie = getMovieById(id);
        movieRepository.delete(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Void> makeMovieAvailable(Long id) throws MovieNotFoundException {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
        movie.setAvailable(true);
        movieRepository.save(movie);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
