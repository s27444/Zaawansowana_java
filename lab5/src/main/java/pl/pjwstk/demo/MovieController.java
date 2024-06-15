package pl.pjwstk.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movie Api", description = "documentation api for MovieController")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/all")
    @Operation(summary = "Gets all movies", description = "Retrieve a list of all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets movie by ID", description = "Movie must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Movie> getMovieById(@PathVariable Integer id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new movie", description = "Add a new movie to the collection")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie added successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid movie data supplied", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Movie> addMovie(@RequestBody Movie newMovie) {
        return ResponseEntity.ok(movieService.addMovie(newMovie));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie", description = "Update an existing movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid movie data supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie updatedMovie) {
        Movie existingMovie = movieService.getMovieById(id);
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setCategory(updatedMovie.getCategory());
        movieService.updateMovie(existingMovie);
        return ResponseEntity.ok(existingMovie);
    }

    @PutMapping("/{id}/available")
    @Operation(summary = "Set movie available", description = "Mark a movie as available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie marked as available", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Movie> setMovieAvailable(@PathVariable Integer id) {
        movieService.setMovieAvailable(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/unavailable")
    @Operation(summary = "Set movie unavailable", description = "Mark a movie as unavailable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie marked as unavailable", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Void> setMovieUnavailable(@PathVariable Integer id) {
        movieService.setMovieUnavailable(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie", description = "Delete an existing movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.noContent().build();
    }
}