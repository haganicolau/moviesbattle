package com.tech.ada.moviesbattle.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToMany
    @JoinTable(
            name = "question_movies",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movieList;

    @Enumerated(EnumType.STRING)
    protected StatusQuestion status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public StatusQuestion getStatus() {
        return status;
    }

    public void setStatus(StatusQuestion status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", movieList=" + movieList +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(quiz, question.quiz)
                && Objects.equals(movieList, question.movieList) && status == question.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quiz, movieList, status);
    }
}
