package com.example.movieservice.model;
public class Movie {
    private int id;
    private String name;
    private String category;
    private String director;
    private int releaseYear;

    public Movie(int id, String name, String category, String director, int releaseYear) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}