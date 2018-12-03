package com.codecool.coolchampserver.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ChampionshipResult {

    private List<ParticipantResult> participantResults = new ArrayList<>();
    private Integer sizeOfPlayoff;

    private ChampionshipResult() {}


    public List<ParticipantResult> getParticipantResults() {
        return participantResults;
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
                                                    bigRoundStage.getParticipantList(),
                                                    numberOfMatches);
            if (bigRoundStage.getExtraMatch() != null) {
                champResult = handleExtraMatch(champResult, bigRoundStage.getExtraMatch(), numberOfMatches);
            }
            Collections.sort(champResult.getParticipantResults());
            champResult.setSizeOfPlayoff(champ.getSettings().getSizeOfPlayoff());
            return champResult;
        }
        return null;
    }


    public static ChampionshipResult generateResultFromMatches(List<Match> matches, List<Participant> participants, Integer numberOfMatches) {
        ChampionshipResult champResult = new ChampionshipResult();
        for (Participant participant: participants) {
            champResult.getParticipantResults().add(new ParticipantResult(participant));
        }
        List<Integer> participantIds = getParticipantIds(participants);
        int participant1_index;
        int participant2_index;
        for (Match match : matches) {
            participant1_index = participantIds.indexOf(match.getParticipant1().getId());
            participant2_index = participantIds.indexOf(match.getParticipant2().getId());
            champResult.getParticipantResults().get(participant1_index).incOppAverage(match.getParticipant2().getElo());
            champResult.getParticipantResults().get(participant2_index).incOppAverage(match.getParticipant1().getElo());
            if ( match.isPlayed() ) {
                champResult.getParticipantResults().get(participant1_index).incPlayedMatches(1);
                champResult.getParticipantResults().get(participant1_index).incWonSets(match.getPoint1());
                champResult.getParticipantResults().get(participant1_index).incLosenSets(match.getPoint2());
                champResult.getParticipantResults().get(participant2_index).incPlayedMatches(1);
                champResult.getParticipantResults().get(participant2_index).incWonSets(match.getPoint2());
                champResult.getParticipantResults().get(participant2_index).incLosenSets(match.getPoint1());
                if (match.getPoint1() > match.getPoint2()) {
                    champResult.getParticipantResults().get(participant1_index).incWonMatches(1);
                } else if (match.getPoint2() > match.getPoint1()) {
                    champResult.getParticipantResults().get(participant2_index).incWonMatches(1);
                }
            }
        }

        for (ParticipantResult participantResult: champResult.getParticipantResults()) {
            participantResult.setAverageOfOpponents(participantResult.getAverageOfOpponents()/numberOfMatches);
        }

        return champResult;
    }

    public static List<Integer> getParticipantIds(List<Participant> participants) {
        return participants.stream().map(participant -> participant.getId()).collect(Collectors.toList());
    }

    public static ChampionshipResult handleExtraMatch(ChampionshipResult championshipResult, ExtraMatch extraMatch, Integer numberOfMatches) {
        for (ParticipantResult participantResult : championshipResult.getParticipantResults()) {
            if (participantResult.getParticipant().getId() == extraMatch.getParticipant().getId()) {
                boolean isParticipant1 = extraMatch.getMatch().getParticipant1().getId() == extraMatch.getParticipant().getId();
                if ( extraMatch.getMatch().isPlayed() ) {
                    participantResult.incPlayedMatches(-1);
                    participantResult.incWonMatches(extraMatch.getMatch().getPoint1() > extraMatch.getMatch().getPoint2() ? (isParticipant1 ? -1 : 0) : (!isParticipant1 ? -1 : 0));
                    participantResult.incWonSets(isParticipant1 ? -1 * extraMatch.getMatch().getPoint1() : -1 * extraMatch.getMatch().getPoint2());
                    participantResult.incLosenSets(isParticipant1 ? -1 * extraMatch.getMatch().getPoint2() : -1 * extraMatch.getMatch().getPoint1());
                }
                if (isParticipant1) {
                    participantResult.setAverageOfOpponents((participantResult.getAverageOfOpponents() * numberOfMatches - extraMatch.getMatch().getParticipant2().getElo()) / numberOfMatches);
                } else {
                    participantResult.setAverageOfOpponents((participantResult.getAverageOfOpponents() * numberOfMatches - extraMatch.getMatch().getParticipant1().getElo()) / numberOfMatches);
                }
            }
        }
        return championshipResult;
    }

}

