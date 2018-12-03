package com.codecool.coolchampserver.httpmodel;

public class DoubleParticipantObject {

    private Integer champId;
    private Integer doubleId;
    private Integer participantId;

    public DoubleParticipantObject(Integer champId, Integer doubleId, Integer participantId) {
        this.champId = champId;
        this.doubleId = doubleId;
        this.participantId = participantId;
    }

    public DoubleParticipantObject() {}

    public Integer getChampId() { return champId; }

    public Integer getDoubleId() {
        return doubleId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setChampId(Integer champId) { this.champId = champId; }

    public void setDoubleId(Integer doubleId) {
        this.doubleId = doubleId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

}
