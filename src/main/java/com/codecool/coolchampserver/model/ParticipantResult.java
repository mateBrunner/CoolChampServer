package com.codecool.coolchampserver.model;

public class ParticipantResult implements Comparable<ParticipantResult> {

    private Participant participant;
    private Integer numberOfPlayedMatches = 0;
    private Integer numberOfWonMatches = 0;
    private Integer numberOfWonSets = 0;
    private Integer numberOfLosenSets = 0;
    private Float averageOfOpponents = 0f;

    public ParticipantResult() {}

    public ParticipantResult(Participant participant) {
        this.participant = participant;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Integer getNumberOfPlayedMatches() {
        return numberOfPlayedMatches;
    }

    public void setNumberOfPlayedMatches(Integer numberOfPlayedMatches) {
        this.numberOfPlayedMatches = numberOfPlayedMatches;
    }

    public Integer getNumberOfWonMatches() {
        return numberOfWonMatches;
    }

    public void setNumberOfWonMatches(Integer numberOfWonMatches) {
        this.numberOfWonMatches = numberOfWonMatches;
    }

    public Integer getNumberOfWonSets() {
        return numberOfWonSets;
    }

    public void setNumberOfWonSets(Integer numberOfWonSets) {
        this.numberOfWonSets = numberOfWonSets;
    }

    public Integer getNumberOfLosenSets() {
        return numberOfLosenSets;
    }

    public void setNumberOfLosenSets(Integer numberOfLosenSets) {
        this.numberOfLosenSets = numberOfLosenSets;
    }

    public float getAverageOfOpponents() {
        return averageOfOpponents;
    }

    public void setAverageOfOpponents(Float averageOfOpponents) {
        this.averageOfOpponents = averageOfOpponents;
    }

    public void incPlayedMatches(Integer number) {
        this.numberOfPlayedMatches = this.numberOfPlayedMatches + number;
    }

    public void incWonMatches(Integer number) {
        this.numberOfWonMatches = this.numberOfWonMatches + number;
    }

    public void incWonSets(Integer number) {
        this.numberOfWonSets = this.numberOfWonSets + number;
    }

    public void incLosenSets(Integer number) {
        this.numberOfLosenSets = this.numberOfLosenSets + number;
    }

    public void incOppAverage(Integer number) {
        this.averageOfOpponents = this.averageOfOpponents + number;
    }

    @Override
    public int compareTo(ParticipantResult pr) {
        int compare = pr.numberOfWonMatches.compareTo(numberOfWonMatches);
        if (compare == 0) {
            compare = pr.numberOfWonSets.compareTo(numberOfWonSets);
            if (compare == 0) {
                compare = numberOfLosenSets.compareTo(pr.numberOfLosenSets);
                if (compare == 0) {
                    compare = pr.averageOfOpponents.compareTo(averageOfOpponents);
                    return compare;
                }
                return compare;
            }
            return compare;
        }
        return compare;
    }

}
