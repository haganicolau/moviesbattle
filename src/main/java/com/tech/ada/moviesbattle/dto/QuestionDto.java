package com.tech.ada.moviesbattle.dto;

import java.util.List;
import java.util.Objects;

public class QuestionDto {

    private QuizDto quiz;
    private List<MovieDto> movies;
    private String status;
    private Long questionId;

    public QuizDto getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDto quiz) {
        this.quiz = quiz;
    }

    public List<MovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDto> movies) {
        this.movies = movies;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(quiz, that.quiz) && Objects.equals(movies, that.movies)
                && Objects.equals(status, that.status) && Objects.equals(questionId, that.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quiz, movies, status, questionId);
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "quiz=" + quiz +
                ", movies=" + movies +
                ", status='" + status + '\'' +
                ", questionId=" + questionId +
                '}';
    }
}
