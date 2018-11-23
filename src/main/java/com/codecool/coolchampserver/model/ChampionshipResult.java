package com.codecool.coolchampserver.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ChampionshipResult {

    private List<PlayerResult> playerResults = new ArrayList<>();
    private Integer sizeOfPlayoff;

    private ChampionshipResult() {}


    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public Integer getSizeOfPlayoff() {
        return sizeOfPlayoff;
    }

    public void setSizeOfPlayoff(Integer sizeOfPlayoff) {
        this.sizeOfPlayoff = sizeOfPlayoff;
    }


    public static ChampionshipResult generateResult(Championship champ) {
        ChampionshipResult champResult;
        Integer numberOfMatches = champ.getSettings().getNumberOfMatches();

        if (champ.getSettings().getFormat().equals("big-round")) {
            BigRoundStage bigRoundStage = (BigRoundStage) champ.getRegularStage();
            champResult = generateResultFromMatches(bigRoundStage.getMatches(),
                                                    bigRoundStage.getPlayerList(),
                                                    numberOfMatches);
            if (bigRoundStage.getExtraMatch() != null) {
                champResult = handleExtraMatch(champResult, bigRoundStage.getExtraMatch(), numberOfMatches);
            }
            Collections.sort(champResult.getPlayerResults());
            champResult.setSizeOfPlayoff(champ.getSettings().getSizeOfPlayoff());
            return champResult;
        }
        return null;
    }


    public static ChampionshipResult generateResultFromMatches(List<Match> matches, List<Player> players, Integer numberOfMatches) {
        ChampionshipResult champResult = new ChampionshipResult();
        for (Player player: players) {
            champResult.getPlayerResults().add(new PlayerResult(player));
        }
        List<Integer> playerIds = getPlayerIds(players);
        int player1_index;
        int player2_index;
        for (Match match : matches) {
            player1_index = playerIds.indexOf(match.getPlayer1().getId());
            player2_index = playerIds.indexOf(match.getPlayer2().getId());
            champResult.getPlayerResults().get(player1_index).incOppAverage(match.getPlayer2().getElo());
            champResult.getPlayerResults().get(player2_index).incOppAverage(match.getPlayer1().getElo());
            if ( match.isPlayed() ) {
                champResult.getPlayerResults().get(player1_index).incPlayedMatches(1);
                champResult.getPlayerResults().get(player1_index).incWonSets(match.getPoint1());
                champResult.getPlayerResults().get(player1_index).incLosenSets(match.getPoint2());
                champResult.getPlayerResults().get(player2_index).incPlayedMatches(1);
                champResult.getPlayerResults().get(player2_index).incWonSets(match.getPoint2());
                champResult.getPlayerResults().get(player2_index).incLosenSets(match.getPoint1());
                if (match.getPoint1() > match.getPoint2()) {
                    champResult.getPlayerResults().get(player1_index).incWonMatches(1);
                } else if (match.getPoint2() > match.getPoint1()) {
                    champResult.getPlayerResults().get(player2_index).incWonMatches(1);
                }
            }
        }

        for (PlayerResult playerResult: champResult.getPlayerResults()) {
            playerResult.setAverageOfOpponents(playerResult.getAverageOfOpponents()/numberOfMatches);
        }

        return champResult;
    }

    public static List<Integer> getPlayerIds(List<Player> players) {
        return players.stream().map(player -> player.getId()).collect(Collectors.toList());
    }

    public static ChampionshipResult handleExtraMatch(ChampionshipResult championshipResult, ExtraMatch extraMatch, Integer numberOfMatches) {
        for (PlayerResult playerResult : championshipResult.getPlayerResults()) {
            if (playerResult.getPlayer().getId() == extraMatch.getPlayer().getId()) {
                boolean isPlayer1 = extraMatch.getMatch().getPlayer1().getId() == extraMatch.getPlayer().getId();
                if ( extraMatch.getMatch().isPlayed() ) {
                    playerResult.incPlayedMatches(-1);
                    playerResult.incWonMatches(extraMatch.getMatch().getPoint1() > extraMatch.getMatch().getPoint2() ? (isPlayer1 ? -1 : 0) : (!isPlayer1 ? -1 : 0));
                    playerResult.incWonSets(isPlayer1 ? -1 * extraMatch.getMatch().getPoint1() : -1 * extraMatch.getMatch().getPoint2());
                    playerResult.incLosenSets(isPlayer1 ? -1 * extraMatch.getMatch().getPoint2() : -1 * extraMatch.getMatch().getPoint1());
                }
                if (isPlayer1) {
                    playerResult.setAverageOfOpponents((playerResult.getAverageOfOpponents() * numberOfMatches - extraMatch.getMatch().getPlayer2().getElo()) / numberOfMatches);
                } else {
                    playerResult.setAverageOfOpponents((playerResult.getAverageOfOpponents() * numberOfMatches - extraMatch.getMatch().getPlayer1().getElo()) / numberOfMatches);
                }
            }
        }
        return championshipResult;
    }

}

