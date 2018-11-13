package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class ChampionshipSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String newChampName;
    private String format;
    private int numberOfGroups;
    private int numberOfMatches;
    private int sizeOfPlayoff;
    @JsonIgnore
    @OneToOne(mappedBy = "settings")
    private Championship championship;

    public ChampionshipSettings(String newChampName, String format, int numberOfGroups, int numberOfMatches, int sizeOfPlayoff) {
        this.newChampName = newChampName;
        this.format = format;
        this.numberOfGroups = numberOfGroups;
        this.numberOfMatches = numberOfMatches;
        this.sizeOfPlayoff = sizeOfPlayoff;
    }

    public ChampionshipSettings() {}

    public Championship getChampionship() {
        return championship;
    }

    public void setChampionship(Championship championship) {
        this.championship = championship;
    }

    public Integer getId() { return id; }

    public String getNewChampName() {
        return newChampName;
    }

    public String getFormat() {
        return format;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getSizeOfPlayoff() {
        return sizeOfPlayoff;
    }

    public void setNewChampName(String newChampName) {
        this.newChampName = newChampName;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }

    public void setNumberOfMatches(int numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }

    public void setSizeOfPlayoff(int sizeOfPlayoff) {
        this.sizeOfPlayoff = sizeOfPlayoff;
    }

    public void update(ChampionshipSettings settings) {
        this.newChampName = settings.getNewChampName();
        this.format = settings.getFormat();
        this.numberOfGroups = settings.getNumberOfGroups();
        this.numberOfMatches = settings.getNumberOfMatches();
        this.sizeOfPlayoff = settings.getSizeOfPlayoff();
    }

    @Override
    public String toString() {
        return this.newChampName + " " + this.format;
    }
}
