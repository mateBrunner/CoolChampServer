package com.codecool.coolchampserver.model;

import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

public class PlayerStats {

    MatchRepository matchRepository;

    private Player player;
    private Integer numberOfPlayedMatches = 0;
    private Integer numberOfWonMatches = 0;
    private Integer numberOfLosenMatches = 0;
    private Integer mostWinInRow = 0;
    private Integer mostLoseInRow = 0;
    private Player mostPlayedPlayer = null;
    private Integer numberOfMatchesWithMostPlayedPlayer = 0;
    private Integer wonAgainstMostPlayedPlayer = 0;
    private String dateOfFirstMatch = null;
    private String dateOfLastMatch = null;

    public Player getPlayer() { return player; }

    public Integer getNumberOfPlayedMatches() { return numberOfPlayedMatches; }

    public Integer getNumberOfWonMatches() { return numberOfWonMatches; }

    public Integer getNumberOfLosenMatches() { return numberOfLosenMatches; }

    public Integer getMostWinInRow() { return mostWinInRow; }

    public Integer getMostLoseInRow() { return mostLoseInRow; }

    public Player getMostPlayedPlayer() { return mostPlayedPlayer; }

    public Integer getNumberOfMatchesWithMostPlayedPlayer() { return numberOfMatchesWithMostPlayedPlayer; }

    public Integer getWonAgainstMostPlayedPlayer() { return wonAgainstMostPlayedPlayer; }

    public String getDateOfFirstMatch() { return dateOfFirstMatch; }

    public String getDateOfLastMatch() { return dateOfLastMatch; }

    public PlayerStats(Player player, List<Match> matches) {
        this.player = player;
        generatePlayerStats(player, matches);
    }

    public void generatePlayerStats(Player player, List<Match> allMatch) {
        numberOfPlayedMatches = allMatch.size();
        System.out.println("matches: " + numberOfPlayedMatches);
        if (numberOfPlayedMatches > 0) {
            dateOfFirstMatch = new SimpleDateFormat("yyyy-MM-dd").format(allMatch.get(0).getDate());
            dateOfLastMatch = new SimpleDateFormat("yyyy-MM-dd").format(allMatch.get(allMatch.size()-1).getDate());
        }

        System.out.println("counting");
        boolean isPlayer1;
        boolean isWon;
        Player winner;
        Player opponent;
        Integer wonInRow = 0;
        Integer loseInRow = 0;
        Map<Player, Integer[]> opponents = new HashMap<>();

        for (Match match: allMatch) {
            isPlayer1 = match.getPlayer1().getId() == player.getId();
            opponent = isPlayer1 ? match.getPlayer2() : match.getPlayer1();
            winner = match.getPoint1() > match.getPoint2() ? match.getPlayer1() : ( match.getPoint1() < match.getPoint2() ? match.getPlayer2() : null);
            isWon = player == winner;
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

        for (Player opp : opponents.keySet()) {
            if (opponents.get(opp)[0] > numberOfMatchesWithMostPlayedPlayer) {
                numberOfMatchesWithMostPlayedPlayer = opponents.get(opp)[0];
                wonAgainstMostPlayedPlayer = opponents.get(opp)[1];
                mostPlayedPlayer = opp;
            }
        }

        numberOfLosenMatches = numberOfPlayedMatches - numberOfWonMatches;

    }

}
