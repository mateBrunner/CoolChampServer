package com.codecool.coolchampserver.httpmodel;

public class ChampParticipantObject {

    private Integer champId;
    private Integer participantId;

    public ChampParticipantObject(Integer champId, Integer participantId) {
        this.champId = champId;
        this.participantId = participantId;
    }

    public ChampParticipantObject() {}

    public Integer getChampId() {
        return champId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setChampId(Integer champId) {
        this.champId = champId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }
}
