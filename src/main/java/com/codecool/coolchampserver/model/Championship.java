package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private ChampionshipStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    private RegularStage regularStage;
    @OneToOne(cascade = CascadeType.ALL)
    private Playoff playoff;

    public Championship() {}

    public Championship(String name) {
        this.name = name;
        status = ChampionshipStatus.NEW;
    }

    public void start() {
        regularStage = new RegularStage();
        playoff = new Playoff();
        status = ChampionshipStatus.INPROGRESS;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
