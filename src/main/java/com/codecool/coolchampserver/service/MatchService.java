package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.httpmodel.MatchResult;
import com.codecool.coolchampserver.model.Match;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PlayerRepository playerRepository;


    public Match getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    public void updateMatch(MatchResult result) {
        Match match = getMatchById(result.getMatchId());
        match.setDate(new Date());
        if (match.getPlayer1().getId() == result.getPlayer1_id() ) {
            match.setPoint1(result.getPoints().getPoint1());
            match.setPoint2(result.getPoints().getPoint2());
        } else {
            match.setPoint2(result.getPoints().getPoint1());
            match.setPoint1(result.getPoints().getPoint2());
        }
        matchRepository.save(match);
    }

    public void deleteResult(Integer id) {
        matchRepository.findById(id).clearResult();
    }

    public Player getWinner(MatchResult result) {
        if (result.getPoints().getPoint1() != null) {
            if (result.getPoints().getPoint1() > result.getPoints().getPoint2()) {
                return playerRepository.findById(result.getPlayer1_id());
            } else if (result.getPoints().getPoint2() > result.getPoints().getPoint1()) {
                return playerRepository.findById(result.getPlayer2_id());
            } else {
                return null;
            }
        }
        return null;
    }

    public Player getLoser(MatchResult result) {
        if (result.getPoints().getPoint1() != null) {
            if (result.getPoints().getPoint1() > result.getPoints().getPoint2()) {
                return playerRepository.findById(result.getPlayer2_id());
            } else if (result.getPoints().getPoint2() > result.getPoints().getPoint1()) {
                return playerRepository.findById(result.getPlayer1_id());
            } else {
                return null;
            }
        }
        return null;
    }


}
