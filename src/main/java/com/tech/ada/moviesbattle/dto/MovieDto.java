package com.tech.ada.moviesbattle.dto;

import java.io.Serializable;
import java.util.Objects;

public class MovieDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String title;
    private String year;
    private String imdbId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbId='" + imdbId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieDto movieDto = (MovieDto) o;
        return Objects.equals(title, movieDto.title) && Objects.equals(year, movieDto.year)
                && Objects.equals(imdbId, movieDto.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, imdbId);
    }
}
