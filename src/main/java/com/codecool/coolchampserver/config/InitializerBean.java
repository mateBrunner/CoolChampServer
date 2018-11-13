package com.codecool.coolchampserver.config;

import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipSettings;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.model.TemporalPlayers;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import com.codecool.coolchampserver.repository.TemporalPlayersRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitializerBean {

    public InitializerBean(ChampionshipRepository championshipRepository,
                           PlayerRepository playerRepository,
                           TemporalPlayersRepository temporalPlayersRepository) {

        championshipRepository.save(new Championship("firsttt"));
        championshipRepository.save(new Championship("seconddd"));
        championshipRepository.save(new Championship("thirddd"));

        Player player1 = new Player("Máté");
        Player player2 = new Player("Gyuszi");
        Player player3 = new Player("Jakab");
        Player player4 = new Player("Béla");
        Player player5 = new Player("Feri");
        Player player6 = new Player("Tomi");
        Player player7 = new Player("Peti");
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        playerRepository.save(player5);
        playerRepository.save(player6);
        playerRepository.save(player7);

        Championship champ = championshipRepository.findById(1);
        champ.getTemporalPlayers().addPlayer(player1);
        champ.getTemporalPlayers().addPlayer(player2);
        champ.getTemporalPlayers().addPlayer(player3);
        champ.getTemporalPlayers().addPlayer(player4);
        championshipRepository.save(champ);

        Championship champ1 = championshipRepository.findById(3);
        champ1.getTemporalPlayers().addPlayer(player2);
        champ1.getTemporalPlayers().addPlayer(player3);
        champ1.getTemporalPlayers().addPlayer(player4);
        champ1.getTemporalPlayers().addPlayer(player5);
        champ1.getTemporalPlayers().addPlayer(player6);
        championshipRepository.save(champ1);
    }

}
