package com.tech.ada.moviesbattle.dto;

import java.io.Serializable;
import java.util.Objects;

public class QuizDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    private String startDateTime;
    private String status;
    private Long id;
    private int mistakes;

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "startDateTime='" + startDateTime + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", mistakes=" + mistakes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDto quizDto = (QuizDto) o;
        return mistakes == quizDto.mistakes && Objects.equals(startDateTime, quizDto.startDateTime)
                && Objects.equals(status, quizDto.status) && Objects.equals(id, quizDto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDateTime, status, id, mistakes);
    }
}
