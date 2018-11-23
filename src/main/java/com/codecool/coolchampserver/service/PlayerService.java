package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.model.PlayerStats;
import com.codecool.coolchampserver.repository.MatchRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    MatchRepository matchRepository;

    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public Player addPlayer(String name) {
        Player newPlayer = new Player(name);
        playerRepository.save(newPlayer);
        return newPlayer;
    }

    public PlayerStats getPlayerStats(Integer id) {
        Player player = playerRepository.findById(id);
        return new PlayerStats(player, matchRepository.getMatchesByPlayer(player));
    }

}
