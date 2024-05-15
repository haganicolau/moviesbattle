package com.tech.ada.moviesbattle.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class ScoreDto implements Serializable {

    private static final Long serialVersionUID = 1L;

    Map<String, Integer> scores;

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "ScoreDto{" +
                "scores=" + scores +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreDto scoreDto = (ScoreDto) o;
        return Objects.equals(scores, scoreDto.scores);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(scores);
    }
}
