package com.codecool.coolchampserver.httpmodel;

public class PlayoffMatchResult {

    private Integer champId;
    private MatchResult result;

    public PlayoffMatchResult() {}

    public PlayoffMatchResult(Integer champId, MatchResult result) {
        this.champId = champId;
        this.result = result;
    }

    public Integer getChampId() {
        return champId;
    }

    public MatchResult getResult() {
        return result;
    }
}
