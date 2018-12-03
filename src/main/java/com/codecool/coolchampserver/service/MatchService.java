package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.httpmodel.MatchResult;
import com.codecool.coolchampserver.model.Match;
import com.codecool.coolchampserver.model.Participant;
import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    ParticipantRepository participantRepository;


    public Match getMatchById(Integer id) {
        return matchRepository.findById(id);
    }

    public void updateMatch(MatchResult result) {
        Match match = getMatchById(result.getMatchId());
        match.setDate(new Date());
        if (match.getParticipant1().getId() == result.getParticipant1_id() ) {
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

    public Participant getWinner(MatchResult result) {
        if (result.getPoints().getPoint1() != null) {
            if (result.getPoints().getPoint1() > result.getPoints().getPoint2()) {
                return participantRepository.findById(result.getParticipant1_id());
            } else if (result.getPoints().getPoint2() > result.getPoints().getPoint1()) {
                return participantRepository.findById(result.getParticipant2_id());
            } else {
                return null;
            }
        }
        return null;
    }

    public Participant getLoser(MatchResult result) {
        if (result.getPoints().getPoint1() != null) {
            if (result.getPoints().getPoint1() > result.getPoints().getPoint2()) {
                return participantRepository.findById(result.getParticipant2_id());
            } else if (result.getPoints().getPoint2() > result.getPoints().getPoint1()) {
                return participantRepository.findById(result.getParticipant1_id());
            } else {
                return null;
            }
        }
        return null;
    }


}
