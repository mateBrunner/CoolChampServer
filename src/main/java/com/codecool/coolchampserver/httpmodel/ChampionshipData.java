package com.codecool.coolchampserver.httpmodel;

import com.codecool.coolchampserver.model.ParticipantType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.criteria.CriteriaBuilder;

public class ChampionshipData {

    private Integer id;
    private String name;
    private String type;


    public ChampionshipData() {}


    public ChampionshipData(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public ChampionshipData(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public ChampionshipData(Integer id, String name) {
        this.id = id;
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() { return type; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name + " " + type;
    }

}
