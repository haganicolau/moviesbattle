package com.tech.ada.moviesbattle.dto;

import java.io.Serializable;
import java.util.Objects;

public class AnswerDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String titleMovie;
    private Long questionId;
    private String imdbId;

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
                "titleMovie='" + titleMovie + '\'' +
                ", questionId=" + questionId +
                ", imdbId='" + imdbId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDto answerDto = (AnswerDto) o;
        return Objects.equals(titleMovie, answerDto.titleMovie) && Objects.equals(questionId, answerDto.questionId)
                && Objects.equals(imdbId, answerDto.imdbId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleMovie, questionId, imdbId);
    }
}
