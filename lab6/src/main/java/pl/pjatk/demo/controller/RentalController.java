package pl.pjatk.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.demo.exception.BadRequestException;
import pl.pjatk.demo.exception.MovieNotFoundException;
import pl.pjatk.demo.exception.MovieServiceUnavailableException;
import pl.pjatk.demo.model.Movie;
import pl.pjatk.demo.service.RentalService;

@RestController
@Tag(name = "Api for RentalController", description = "documentation for RentalController")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/movies/{id}")
    @Operation(summary = "Gets movie by ID", description = "Fetch a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie retrieved successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "502", description = "Service unavailable", content = @Content)
    })
    public ResponseEntity<Movie> getMovie(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.getMovie(id));
    }

    @PutMapping("/movies/{id}/available")
    @Operation(summary = "Return a movie", description = "Mark a movie as available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie returned successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "502", description = "Service unavailable", content = @Content)
    })
    public ResponseEntity<Movie> returnMovie(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.returnMovie(id));
    }

    @PutMapping("/movies/{id}/unavailable")
    @Operation(summary = "Rent a movie", description = "Mark a movie as unavailable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie rented successfully", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content),
            @ApiResponse(responseCode = "502", description = "Service unavailable", content = @Content)
    })
    public ResponseEntity<Movie> rentMovie(@PathVariable Long id){
        return ResponseEntity.ok(rentalService.rentMovie(id));
    }

    @ExceptionHandler(MovieNotFoundException.class)
    @Operation(summary = "Handle MovieNotFoundException", description = "Handle exceptions when a movie is not found")
    @ApiResponse(responseCode = "404", description = "Movie not found", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = String.class)) })
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @Operation(summary = "Handle BadRequestException", description = "Handle exceptions for bad requests")
    @ApiResponse(responseCode = "400", description = "Invalid request", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = String.class)) })
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MovieServiceUnavailableException.class)
    @Operation(summary = "Handle MovieServiceUnavailableException", description = "Handle exceptions when the movie service is unavailable")
    @ApiResponse(responseCode = "502", description = "Service unavailable", content =
            { @Content(mediaType = "application/json", schema =
            @Schema(implementation = String.class)) })
    public ResponseEntity<String> handleMovieServiceUnavailableException(MovieServiceUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(ex.getMessage());
    }
}
