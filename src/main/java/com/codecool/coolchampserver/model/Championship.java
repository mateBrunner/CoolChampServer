package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ChampionshipStatus status;
    @OneToOne(cascade = CascadeType.ALL)
    private RegularStage regularStage;
    @OneToOne(cascade = CascadeType.ALL)
    private Playoff playoff;
    @OneToOne(orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private ChampionshipSettings settings;

    public Championship() {}

    public Championship(String name) {
        this.status = ChampionshipStatus.NEW;
        this.settings = new ChampionshipSettings(name, "big-round", 2, 3, 8);
    }

    public void start() {
        regularStage = new RegularStage();
        playoff = new Playoff();
        status = ChampionshipStatus.INPROGRESS;
    }

    public Integer getId() {
        return this.id;
    }

    public ChampionshipStatus getStatus() { return this.status; }

    public ChampionshipSettings getSettings() {
        return settings;
    }

    public void setSettings(ChampionshipSettings settings) {
        this.settings = settings;
    }
}
