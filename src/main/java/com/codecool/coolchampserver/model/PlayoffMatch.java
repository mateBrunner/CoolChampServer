package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class PlayoffMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer level;
    private Integer row;
    @OneToOne(cascade = CascadeType.ALL)
    private Match match;

    public PlayoffMatch() {
    }

    public PlayoffMatch(Integer level, Integer row) {
        this.level = level;
        this.row = row;
        this.match = new Match();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
