package com.tech.ada.moviesbattle.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "quizes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "finish_date_time")
    private LocalDateTime finishDateTime;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @Enumerated(EnumType.STRING)
    protected StatusQuiz status;

    @Column(name = "mistakes")
    private int mistakes;

    public Quiz() {}

    public Quiz(LocalDateTime startDateTime, Player player, StatusQuiz status, int mistakes) {
        this.startDateTime = startDateTime;
        this.player = player;
        this.status = status;
        this.mistakes = mistakes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getFinishDateTime() {
        return finishDateTime;
    }

    public void setFinishDateTime(LocalDateTime finishDateTime) {
        this.finishDateTime = finishDateTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public StatusQuiz getStatus() {
        return status;
    }

    public void setStatus(StatusQuiz status) {
        this.status = status;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", startDateTime=" + startDateTime +
                ", finishDateTime=" + finishDateTime +
                ", player=" + player +
                ", questionList=" + questionList +
                ", status=" + status +
                ", mistakes=" + mistakes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quiz quiz = (Quiz) o;
        return mistakes == quiz.mistakes && Objects.equals(id, quiz.id)
                && Objects.equals(startDateTime, quiz.startDateTime)
                && Objects.equals(finishDateTime, quiz.finishDateTime)
                && Objects.equals(player, quiz.player) && Objects.equals(questionList, quiz.questionList)
                && status == quiz.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDateTime, finishDateTime, player, questionList, status, mistakes);
    }
}
