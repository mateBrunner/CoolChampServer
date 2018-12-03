package com.codecool.coolchampserver.model;

import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

public class ParticipantStats {

    MatchRepository matchRepository;

    private Participant participant;
    private Integer numberOfPlayedMatches = 0;
    private Integer numberOfWonMatches = 0;
    private Integer numberOfLosenMatches = 0;
    private Integer mostWinInRow = 0;
    private Integer mostLoseInRow = 0;
    private Participant mostPlayedParticipant = null;
    private Integer numberOfMatchesWithMostPlayedParticipant = 0;
    private Integer wonAgainstMostPlayedParticipant = 0;
    private String dateOfFirstMatch = null;
    private String dateOfLastMatch = null;

    public Participant getParticipant() { return participant; }

    public Integer getNumberOfPlayedMatches() { return numberOfPlayedMatches; }

    public Integer getNumberOfWonMatches() { return numberOfWonMatches; }

    public Integer getNumberOfLosenMatches() { return numberOfLosenMatches; }

    public Integer getMostWinInRow() { return mostWinInRow; }

    public Integer getMostLoseInRow() { return mostLoseInRow; }

    public Participant getMostPlayedParticipant() { return mostPlayedParticipant; }

    public Integer getNumberOfMatchesWithMostPlayedParticipant() { return numberOfMatchesWithMostPlayedParticipant; }

    public Integer getWonAgainstMostPlayedParticipant() { return wonAgainstMostPlayedParticipant; }

    public String getDateOfFirstMatch() { return dateOfFirstMatch; }

    public String getDateOfLastMatch() { return dateOfLastMatch; }

    public ParticipantStats(Participant participant, List<Match> matches) {
        this.participant = participant;
        generateParticipantStats(participant, matches);
    }

    public void generateParticipantStats(Participant participant, List<Match> allMatch) {
        numberOfPlayedMatches = allMatch.size();
        System.out.println("matches: " + numberOfPlayedMatches);
        if (numberOfPlayedMatches > 0) {
            dateOfFirstMatch = new SimpleDateFormat("yyyy-MM-dd").format(allMatch.get(0).getDate());
            dateOfLastMatch = new SimpleDateFormat("yyyy-MM-dd").format(allMatch.get(allMatch.size()-1).getDate());
        }

        System.out.println("counting");
        boolean isParticipant1;
        boolean isWon;
        Participant winner;
        Participant opponent;
        Integer wonInRow = 0;
        Integer loseInRow = 0;
        Map<Participant, Integer[]> opponents = new HashMap<>();

        for (Match match: allMatch) {
            isParticipant1 = match.getParticipant1().getId() == participant.getId();
            opponent = isParticipant1 ? match.getParticipant2() : match.getParticipant1();
            winner = match.getPoint1() > match.getPoint2() ? match.getParticipant1() : ( match.getPoint1() < match.getPoint2() ? match.getParticipant2() : null);
            isWon = participant == winner;
            if (isWon) {
                numberOfWonMatches++;
                wonInRow++;
                loseInRow = 0;
                if (wonInRow > mostWinInRow) {mostWinInRow++;}
            } else {
                wonInRow = 0;
                loseInRow++;
                if (loseInRow > mostLoseInRow) {mostLoseInRow++;}
            }

            if (!opponents.containsKey(opponent)) {
                Integer[] oppNumbers = {1, isWon ? 1 : 0};
                opponents.put(opponent, oppNumbers );
            } else {
                Integer[] oppNumbers = {opponents.get(opponent)[0] + 1, opponents.get(opponent)[1] + (isWon ? 1 : 0)};
                opponents.put(opponent, oppNumbers);
            }

        }

        for (Participant opp : opponents.keySet()) {
            if (opponents.get(opp)[0] > numberOfMatchesWithMostPlayedParticipant) {
                numberOfMatchesWithMostPlayedParticipant = opponents.get(opp)[0];
                wonAgainstMostPlayedParticipant = opponents.get(opp)[1];
                mostPlayedParticipant = opp;
            }
        }

        numberOfLosenMatches = numberOfPlayedMatches - numberOfWonMatches;

    }

}
