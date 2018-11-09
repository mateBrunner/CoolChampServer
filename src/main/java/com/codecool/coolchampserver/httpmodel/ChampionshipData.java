package com.codecool.coolchampserver.httpmodel;

public class ChampionshipData {

    private Integer id;
    private String name;

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

}
