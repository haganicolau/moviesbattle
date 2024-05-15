package com.tech.ada.moviesbattle.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "username", nullable = false)
    private String username ;

    @Column(name = "password", nullable = false)
    private String password ;

    @Column(name = "score", nullable = false)
    private int score ;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    private List<Quiz> quizList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return score == player.score && Objects.equals(id, player.id) && Objects.equals(username, player.username)
                && Objects.equals(quizList, player.quizList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, score, quizList);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", quizList=" + quizList +
                '}';
    }
}
