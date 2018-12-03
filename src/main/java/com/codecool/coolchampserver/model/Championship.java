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
    private TemporalParticipants temporalParticipants;

    public Championship() {}

    public Championship(String name, ParticipantType pType) {
        this.status = ChampionshipStatus.NEW;
        this.settings = new ChampionshipSettings(name, "big-round", 2, 3, 8, pType);
        this.temporalParticipants = new TemporalParticipants();
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

    public TemporalParticipants getTemporalParticipants() {
        return this.temporalParticipants;
    }

    public void setTemporalParticipants(TemporalParticipants temporalParticipants) { this.temporalParticipants = temporalParticipants; }

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
