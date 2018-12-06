package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Player extends Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer elo;

    public Player(String name) {
        this.name = name;
        this.elo = 1500;
    }

    public Player(String name, Integer elo) {
        this.name = name;
        this.elo = elo;
    }

    public Player() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    @Override
    public String toString() {
        return "id: " + this.id + "  name: " + this.name;
    }

    @JsonIgnore
    @Override
    public List<Player> getPlayerss() {
        List<Player> players = new ArrayList<>();
        players.add(this);
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
