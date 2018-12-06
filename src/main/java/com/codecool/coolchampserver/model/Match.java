package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Participant participant1;
    @OneToOne(cascade = CascadeType.REFRESH)
    private Participant participant2;
    private Integer point1;
    private Integer point2;
    private Date date;

    public Match(Participant participant1, Participant participant2) {
        this.participant1 = participant1;
        this.participant2 = participant2;
    };

    public Match() {}

    public Integer getId() { return id; }

    public Participant getParticipant1() {
        return participant1;
    }

    public Participant getParticipant2() {
        return participant2;
    }

    public Integer getPoint1() {
        return point1;
    }

    public Integer getPoint2() {
        return point2;
    }

    public Date getDate() { return date; }

    public void setParticipant1(Participant participant1) {
        this.participant1 = participant1;
    }

    public void setParticipant2(Participant participant2) {
        this.participant2 = participant2;
    }

    public void setPoint1(Integer point1) {
        this.point1 = point1;
    }

    public void setPoint2(Integer point2) {
        this.point2 = point2;
    }

    public void setDate(Date date) { this.date = date; }

    public void clearMatch() {
        this.participant1 = null;
        this.participant2 = null;
        this.point1 = null;
        this.point2 = null;
    }

    public void clearMatch(int participantNumber) {
        this.point1 = null;
        this.point2 = null;
        if (participantNumber == 1) { this.participant1 = null; }
        if (participantNumber == 2) { this.participant2 = null; }
    }

    public void clearResult() {
        this.point1 = null;
        this.point2 = null;
    }

    @JsonIgnore
    public boolean isPlayed() {
        return this.point1 != null;
    }

    @JsonIgnore
    public boolean isPlayable() {
        return this.participant1 != null && this.participant2 != null && this.point1 == null;
    }

    @JsonIgnore
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.addAll(participant1.getPlayerss());
        players.addAll(participant2.getPlayerss());
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, participant1, participant2, point1, point2, date);
    }
}
