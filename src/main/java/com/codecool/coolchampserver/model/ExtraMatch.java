package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class ExtraMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Player player;
    @ManyToOne
    private Match match;

    public ExtraMatch(Player player, Match match) {
        this.player = player;
        this.match = match;
    }

    public ExtraMatch() {}

    public Player getPlayer() {
        return player;
    }

    public Match getMatch() {
        return match;
    }
}
