package com.codecool.coolchampserver.httpmodel;

public class ChampPlayerObject {

    private Integer champId;
    private Integer playerId;

    public ChampPlayerObject(Integer champId, Integer playerId) {
        this.champId = champId;
        this.playerId = playerId;
    }

    public ChampPlayerObject() {}

    public Integer getChampId() {
        return champId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setChampId(Integer champId) {
        this.champId = champId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
