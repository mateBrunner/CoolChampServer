package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.model.Doubles;
import com.codecool.coolchampserver.model.Participant;
import com.codecool.coolchampserver.model.ParticipantStats;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.repository.DoublesRepository;
import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.ParticipantRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.util.List;

@Service
public class ParticipantService {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    DoublesRepository doublesRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MatchRepository matchRepository;

    public List<Participant> getParticipants() {
        return participantRepository.findAll();
    }

    public List<Doubles> getDoubles() { return doublesRepository.findAll(); }

    public List<Player> getPlayers() { return playerRepository.findAll(); }

    public Player addPlayer(String name) {
        Participant newPlayer = new Player(name);
        participantRepository.save(newPlayer);
        return (Player) newPlayer;
    }

    public ParticipantStats getParticipantStats(Integer id) {
        Participant participant = participantRepository.findById(id);
        return new ParticipantStats(participant, matchRepository.getMatchesByParticipant(participant));
    }

}
