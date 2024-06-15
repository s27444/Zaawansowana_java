package pl.pjatk.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details about a movie")
public class Movie {
    @Schema(description = "The unique ID of the movie", example = "1")
    private Integer id;

    @Schema(description = "The title of the movie", example = "Inception")
    private String title;

    @Schema(description = "The category of the movie")
    private Category category;

    @Schema(description = "Availability status of the movie", example = "true")
    private boolean isAvailable;

    public Movie() {}

    public Movie(Integer id, String title, Category category, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }
}