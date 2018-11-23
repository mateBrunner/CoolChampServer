package com.codecool.coolchampserver.httpmodel;

public class MatchResult {

    private Integer matchId;
    private Integer player1_id;
    private Integer player2_id;
    private Points points;


    public MatchResult(Integer matchId, Integer player1_id, Integer player2_id, Points points) {
        this.matchId = matchId;
        this.player1_id = player1_id;
        this.player2_id = player2_id;
        this.points = points;
    }

    public MatchResult() {}


    public Integer getMatchId() {
        return matchId;
    }

    public Integer getPlayer1_id() {
        return player1_id;
    }

    public Integer getPlayer2_id() {
        return player2_id;
    }

    public Points getPoints() {
        return points;
    }


}


