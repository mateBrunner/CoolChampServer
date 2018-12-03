package com.codecool.coolchampserver.httpmodel;

public class MatchResult {

    private Integer matchId;
    private Integer participant1_id;
    private Integer participant2_id;
    private Points points;


    public MatchResult(Integer matchId, Integer participant1_id, Integer participant2_id, Points points) {
        this.matchId = matchId;
        this.participant1_id = participant1_id;
        this.participant2_id = participant2_id;
        this.points = points;
    }

    public MatchResult() {}


    public Integer getMatchId() {
        return matchId;
    }

    public Integer getParticipant1_id() {
        return participant1_id;
    }

    public Integer getParticipant2_id() {
        return participant2_id;
    }

    public Points getPoints() {
        return points;
    }


}


