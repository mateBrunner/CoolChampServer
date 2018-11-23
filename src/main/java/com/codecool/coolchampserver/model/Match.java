package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Player player1;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Player player2;
    private Integer point1;
    private Integer point2;
    private Date date;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    };

    public Match() {}

    public Integer getId() { return id; }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Integer getPoint1() {
        return point1;
    }

    public Integer getPoint2() {
        return point2;
    }

    public Date getDate() { return date; }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPoint1(Integer point1) {
        this.point1 = point1;
    }

    public void setPoint2(Integer point2) {
        this.point2 = point2;
    }

    public void setDate(Date date) { this.date = date; }

    public void clearMatch() {
        this.player1 = null;
        this.player2 = null;
        this.point1 = null;
        this.point2 = null;
    }

    public void clearMatch(int playerNumber) {
        this.point1 = null;
        this.point2 = null;
        if (playerNumber == 1) { this.player1 = null; }
        if (playerNumber == 2) { this.player2 = null; }
    }

    public void clearResult() {
        this.point1 = null;
        this.point2 = null;
    }

    public boolean isPlayed() {
        return this.point1 != null;
    }

}
