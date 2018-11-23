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

        Player player1 = new Player("Karcsi", 1970);
        Player player2 = new Player("Gyuszi", 1930);
        Player player3 = new Player("Jakab", 1870);
        Player player4 = new Player("Béla", 1856);
        Player player5 = new Player("Feri", 1438);
        Player player6 = new Player("Tomi", 2100);
        Player player7 = new Player("Peti", 1322);
        Player player8 = new Player("Máté",1856);
        Player player9 = new Player("Rita", 1023);
        Player player10 = new Player("Eszter", 1213);
        Player player11 = new Player("Gyula", 1451);
        Player player12 = new Player("Ottó", 1677);
        Player player13 = new Player("Tamás", 1111);
        Player player14 = new Player("Attila", 1083);
        Player player15 = new Player("Balázs", 980);
        Player player16 = new Player("Dani", 1311);
        Player player17 = new Player("Anna", 1434);
        Player player18 = new Player("Bence", 1238);
        Player player19 = new Player("Zsolt", 1970);
        Player player20 = new Player("Zsolti", 1930);
        Player player21 = new Player("Péter", 1870);
        Player player22 = new Player("Ferenc", 1856);
        Player player23 = new Player("Bélus", 1438);
        Player player24 = new Player("Andrea", 2100);
        Player player25 = new Player("Gabi", 1322);
        Player player26 = new Player("Jani",1856);
        Player player27 = new Player("Imre", 1023);
        Player player28 = new Player("Imi", 1213);
        Player player29 = new Player("Balu", 1451);
        Player player30 = new Player("Szilárd", 1677);
        Player player31 = new Player("Csongor", 1111);
        Player player32 = new Player("Csanád", 1083);
        Player player33 = new Player("Csenge", 980);
        Player player34 = new Player("Domi", 1311);
        Player player35 = new Player("Benedek", 1434);
        Player player36 = new Player("Samu", 1238);
        Player player37 = new Player("Sámuel", 1970);
        Player player38 = new Player("Palkó", 1930);
        Player player39 = new Player("Pál", 1870);
        Player player40 = new Player("Kristóf", 1856);
        Player player41 = new Player("Hanna", 1438);
        Player player42 = new Player("Anita", 2100);
        Player player43 = new Player("Réka", 1322);
        Player player44 = new Player("Márti",1856);
        Player player45 = new Player("Tibi", 1023);
        Player player46 = new Player("Bulcsú", 1213);
        Player player47 = new Player("Anikó", 1451);
        Player player48 = new Player("Norbi", 1677);
        Player player49 = new Player("Judit", 1111);
        Player player50 = new Player("Robi", 1083);
        Player player51 = new Player("Áron", 980);
        Player player52 = new Player("Nándi", 1311);
        Player player53 = new Player("Szilvi", 1434);
        Player player54 = new Player("Gábor", 1238);
        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        playerRepository.save(player5);
        playerRepository.save(player6);
        playerRepository.save(player7);
        playerRepository.save(player8);
        playerRepository.save(player9);
        playerRepository.save(player10);
        playerRepository.save(player11);
        playerRepository.save(player12);
        playerRepository.save(player13);
        playerRepository.save(player14);
        playerRepository.save(player15);
        playerRepository.save(player16);
        playerRepository.save(player17);
        playerRepository.save(player18);
        playerRepository.save(player19);
        playerRepository.save(player20);
        playerRepository.save(player21);
        playerRepository.save(player22);
        playerRepository.save(player23);
        playerRepository.save(player24);
        playerRepository.save(player25);
        playerRepository.save(player26);
        playerRepository.save(player27);
        playerRepository.save(player28);
        playerRepository.save(player29);
        playerRepository.save(player30);
        playerRepository.save(player31);
        playerRepository.save(player32);
        playerRepository.save(player33);
        playerRepository.save(player34);
        playerRepository.save(player35);
        playerRepository.save(player36);
        playerRepository.save(player37);
        playerRepository.save(player38);
        playerRepository.save(player39);
        playerRepository.save(player40);
        playerRepository.save(player41);
        playerRepository.save(player42);
        playerRepository.save(player43);
        playerRepository.save(player44);
        playerRepository.save(player45);
        playerRepository.save(player46);
        playerRepository.save(player47);
        playerRepository.save(player48);
        playerRepository.save(player49);
        playerRepository.save(player50);
        playerRepository.save(player51);
        playerRepository.save(player52);
        playerRepository.save(player53);
        playerRepository.save(player54);

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
