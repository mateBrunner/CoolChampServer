package com.codecool.coolchampserver.model;

import javax.persistence.*;

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


}
