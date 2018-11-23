package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ChampionshipStatus status;
    @JsonIgnore
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private RegularStage regularStage;
    @JsonIgnore
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private Playoff playoff;
    @OneToOne(orphanRemoval = true, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE })
    private ChampionshipSettings settings;
    @JsonIgnore
    @OneToOne(orphanRemoval = true, cascade = { CascadeType.ALL })
    private TemporalPlayers temporalPlayers;

    public Championship() {}

    public Championship(String name) {
        this.status = ChampionshipStatus.NEW;
        this.settings = new ChampionshipSettings(name, "big-round", 2, 3, 8);
        this.temporalPlayers = new TemporalPlayers();
    }


    public Integer getId() {
        return this.id;
    }

    public ChampionshipStatus getStatus() { return this.status; }

    public void setStatus(ChampionshipStatus status) {
        this.status = status;
    }

    public ChampionshipSettings getSettings() {
        return settings;
    }

    public void setSettings(ChampionshipSettings settings) {
        this.settings = settings;
    }

    public TemporalPlayers getTemporalPlayers() {
        return this.temporalPlayers;
    }

    public void setTemporalPlayers(TemporalPlayers temporalPlayers) { this.temporalPlayers = temporalPlayers; }

    public RegularStage getRegularStage() {
        return regularStage;
    }

    public void setRegularStage(RegularStage regularStage) {
        this.regularStage = regularStage;
    }

    public Playoff getPlayoff() { return this.playoff; }

    public void setPlayoff(Playoff playoff) {
        this.playoff = playoff;
    }

}
