package com.tech.ada.moviesbattle.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "year_movie", length = 4, nullable = false)
    private String year;

    @Column(name = "imdb_id")
    private String imdbId;

    @Column(name = "imdb_rating")
    private double imdbRating;

    @Column(name = "imdb_votes")
    private int imdbVotes;

    @ManyToMany(mappedBy = "movieList")
    private List<Question> questionList;

    public Movie(String title, String year, String imdbId) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public double calculatorPoints() {
        return getImdbVotes() * getImdbRating();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(imdbRating, movie.imdbRating) == 0 && imdbVotes == movie.imdbVotes
                && Objects.equals(id, movie.id) && Objects.equals(title, movie.title)
                && Objects.equals(year, movie.year) && Objects.equals(imdbId, movie.imdbId)
                && Objects.equals(questionList, movie.questionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, imdbId, imdbRating, imdbVotes, questionList);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", imdbRating=" + imdbRating +
                ", imdbVotes=" + imdbVotes +
                ", questionList=" + questionList +
                '}';
    }
}
