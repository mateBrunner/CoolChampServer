package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Doubles extends Participant {

    @ManyToMany(cascade = CascadeType.MERGE)
    @OrderColumn
    private List<Player> players;

    public Doubles(List<Player> players) {
        this.players = players;
        countEloAndName();
    }

    public Doubles() {
        this.players = new ArrayList<>(2);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        if (players.size() < 2) {
            players.add(player);
            countEloAndName();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void replacePlayer(Player from, Player to) {
        players.remove(from);
        players.add(to);
        countEloAndName();
    }

    private void countEloAndName() {
        if (players.size() == 2) {
            super.elo = (players.get(0).getElo() + players.get(1).getElo()) / 2;
            super.name = (players.get(0).getName() + "/" + players.get(1).getName());
        }
    }

}
