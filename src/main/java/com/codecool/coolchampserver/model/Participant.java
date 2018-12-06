package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String name;
    protected Integer elo;

    public String getName() {
        return name;
    };

    public Integer getId() {
        return id;
    };

    public Integer getElo() {
        return elo;
    };

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    @JsonIgnore
    public abstract List<Player> getPlayerss();

}
