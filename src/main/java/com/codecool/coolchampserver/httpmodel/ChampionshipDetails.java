package com.codecool.coolchampserver.httpmodel;

public class ChampionshipDetails {

    private Integer id;
    private String name;
    private String status;
    private int numberOfGroups;
    private int numberOfMatches;
    private int numberOfSize;

    public ChampionshipDetails(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() { return status; }

}
