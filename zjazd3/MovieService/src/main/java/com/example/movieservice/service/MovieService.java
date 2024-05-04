package com.example.movieservice.service;

import com.example.movieservice.exception.MovieNotFoundException;
import com.example.movieservice.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MovieService {
    private final List<Movie> movies = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger();

    public MovieService() {
        movies.add(new Movie(1, "The Shawshank Redemption", "Drama", "Frank Darabont", 1994));
        movies.add(new Movie(2, "The Godfather", "Crime", "Francis Ford Coppola", 1972));
        movies.add(new Movie(3, "The Dark Knight", "Action", "Christopher Nolan", 2008));
        movies.add(new Movie(4, "The Lord of the Rings: The Return of the King", "Adventure", "Peter Jackson", 2003));
        movies.add(new Movie(5, "Pulp Fiction", "Crime", "Quentin Tarantino", 1994));
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public Movie getMovieById(int id) throws MovieNotFoundException {
        return movies.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MovieNotFoundException("Movie with id " + id + " not found"));
    }

    public Movie addMovie(Movie movie) {
        int newId = idCounter.incrementAndGet();
        Movie newMovie = new Movie(newId, movie.getName(), movie.getCategory(), movie.getDirector(), movie.getReleaseYear());
        movies.add(newMovie);
        return newMovie;
    }
}