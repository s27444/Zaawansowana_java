package pl.pjwstk.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "movies")
@Schema(description = "Details about a movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the movie", example = "1")
    private Integer id;

    @Schema(description = "The title of the movie", example = "Inception")
    private String title;

    @Schema(description = "The category of the movie", example = "Sci-Fi")
    private String category;

    @Schema(description = "Availability status of the movie", example = "true")
    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
