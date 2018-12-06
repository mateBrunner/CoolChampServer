package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.httpmodel.PlayoffMatchResult;
import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PlayoffService {

    @Autowired
    ChampionshipRepository championshipRepository;

    @Autowired
    MatchService matchService;

    @Transactional
    public void updatePlayoffByMatch(PlayoffMatchResult result) {
        Championship championship = championshipRepository.findById(result.getChampId());
        Playoff playoff = championship.getPlayoff();
        matchService.updateMatch(result.getResult());
        Participant winner = matchService.getWinner(result.getResult());
        Participant loser = matchService.getLoser(result.getResult());
        Integer level = null;
        Integer row = null;
        for (PlayoffMatch poMatch : championshipRepository.findById(result.getChampId()).getPlayoff().getMatches()) {
            if (result.getResult().getMatchId().equals(poMatch.getMatch().getId())) {
                level = poMatch.getLevel();
                row = poMatch.getRow();
                break;
            }
        }
        if (level != 3) {
            PlayoffMatch nextMatch = playoff.getMatchByPosition(new Playoff.Position(level + 1, row / 2, 0));
            if (row % 2 == 0) {
                if (nextMatch.getMatch().getParticipant1() != null && !nextMatch.getMatch().getParticipant1().equals(winner)) {
                    playoff.clearNextMatches(new Playoff.Position(level, row));
                }
                nextMatch.getMatch().setParticipant1(winner);
                if (level == 2) {
                 playoff.getMatchByPosition(new Playoff.Position(3, 1)).getMatch().setParticipant1(loser);
                }
            } else {
                if (nextMatch.getMatch().getParticipant2() != null && !nextMatch.getMatch().getParticipant2().equals(winner)) {
                    playoff.clearNextMatches(new Playoff.Position(level, row));
                }
                nextMatch.getMatch().setParticipant2(winner);
                if (level == 2) {
                    playoff.getMatchByPosition(new Playoff.Position(3, 1)).getMatch().setParticipant2(loser);
                }
            }
        }
        championshipRepository.save(championship);
    }

}
