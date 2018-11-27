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
        Player winner = matchService.getWinner(result.getResult());
        Player loser = matchService.getLoser(result.getResult());
        Integer level = null;
        Integer row = null;
        System.out.println("*****************");
        System.out.println("result: " + result.getResult().getMatchId());
        for (PlayoffMatch poMatch : championshipRepository.findById(result.getChampId()).getPlayoff().getMatches()) {
            System.out.println("pomatch: " + poMatch.getMatch().getId());
            if (result.getResult().getMatchId() == poMatch.getMatch().getId()) {
                level = poMatch.getLevel();
                row = poMatch.getRow();
                break;
            }
        }
        if (level != 3) {
            PlayoffMatch nextMatch = playoff.getMatchByPosition(new Playoff.Position(level + 1, row / 2, 0));
            if (row % 2 == 0) {
                if (nextMatch.getMatch().getPlayer1() != null && !nextMatch.getMatch().getPlayer1().equals(winner)) {
                    playoff.clearNextMatches(new Playoff.Position(level, row));
                }
                nextMatch.getMatch().setPlayer1(winner);
                if (level == 2) {
                 playoff.getMatchByPosition(new Playoff.Position(3, 1)).getMatch().setPlayer1(loser);
                }
            } else {
                if (nextMatch.getMatch().getPlayer2() != null && !nextMatch.getMatch().getPlayer2().equals(winner)) {
                    playoff.clearNextMatches(new Playoff.Position(level, row));
                }
                nextMatch.getMatch().setPlayer2(winner);
                if (level == 2) {
                    playoff.getMatchByPosition(new Playoff.Position(3, 1)).getMatch().setPlayer2(loser);
                }
            }
        }
        championshipRepository.save(championship);
    }

}
