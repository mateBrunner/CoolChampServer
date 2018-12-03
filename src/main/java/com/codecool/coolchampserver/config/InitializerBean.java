package com.codecool.coolchampserver.config;

import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.model.Doubles;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.ParticipantRepository;
import com.codecool.coolchampserver.repository.TablesRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class InitializerBean {

    public InitializerBean(ChampionshipRepository championshipRepository,
                           ParticipantRepository participantRepository,
                           TablesRepository tableRepository) {

        championshipRepository.save(new Championship("CHAMP 1", ParticipantType.SINGLE));
        championshipRepository.save(new Championship("CHAMP 2", ParticipantType.DOUBLE));
        championshipRepository.save(new Championship("CHAMP 3", ParticipantType.SINGLE));

        Participant player1 = new Player("Karcsi", 1970);
        Participant player2 = new Player("Gyuszi", 1930);
        Participant player3 = new Player("Jakab", 1870);
        Participant player4 = new Player("Zoli", 1856);
        Participant player5 = new Player("Feri", 1438);
        Participant player6 = new Player("Tomi", 2100);
        Participant player7 = new Player("Peti", 1322);
        Participant player8 = new Player("Máté",1856);
        Participant player9 = new Player("Rita", 1023);
        Participant player10 = new Player("Eszter", 1213);
        Participant player11 = new Player("Gyula", 1451);
        Participant player12 = new Player("Ottó", 1677);
        Participant player13 = new Player("Tamás", 1111);
        Participant player14 = new Player("Attila", 1083);
        Participant player15 = new Player("Balázs", 980);
        Participant player16 = new Player("Dani", 1311);
        Participant player17 = new Player("Anna", 1434);
        Participant player18 = new Player("Bence", 1238);
        Participant player19 = new Player("Zsolt", 1970);
        Participant player20 = new Player("Zsolti", 1930);
        Participant player21 = new Player("Péter", 1870);
        Participant player22 = new Player("Ferenc", 1856);
        Participant player23 = new Player("Bélus", 1438);
        Participant player24 = new Player("Andrea", 2100);
        Participant player25 = new Player("Gabi", 1322);
        Participant player26 = new Player("Jani",1856);
        Participant player27 = new Player("Imre", 1023);
        Participant player28 = new Player("Imi", 1213);
        Participant player29 = new Player("Balu", 1451);
        Participant player30 = new Player("Szilárd", 1677);

        participantRepository.save(player1);
        participantRepository.save(player2);
        participantRepository.save(player3);
        participantRepository.save(player4);
        participantRepository.save(player5);
        participantRepository.save(player6);
        participantRepository.save(player7);
        participantRepository.save(player8);
        participantRepository.save(player9);
        participantRepository.save(player10);
        participantRepository.save(player11);
        participantRepository.save(player12);
        participantRepository.save(player13);
        participantRepository.save(player14);
        participantRepository.save(player15);
        participantRepository.save(player16);
        participantRepository.save(player17);
        participantRepository.save(player18);
        participantRepository.save(player19);
        participantRepository.save(player20);
        participantRepository.save(player21);
        participantRepository.save(player22);
        participantRepository.save(player23);
        participantRepository.save(player24);
        participantRepository.save(player25);
        participantRepository.save(player26);
        participantRepository.save(player27);
        participantRepository.save(player28);
        participantRepository.save(player29);
        participantRepository.save(player30);


        Doubles double1 = new Doubles();
        double1.addPlayer((Player) player1);
        double1.addPlayer((Player) player2);

        Doubles double2 = new Doubles();
        double2.addPlayer((Player) player3);
        double2.addPlayer((Player) player4);

        Doubles double3 = new Doubles();
        double3.addPlayer((Player) player12);

        participantRepository.save(double1);
        participantRepository.save(double2);
        participantRepository.save(double3);


        Championship champ = championshipRepository.findById(1);
        champ.getTemporalParticipants().addParticipant(player1);
        champ.getTemporalParticipants().addParticipant(player2);
        champ.getTemporalParticipants().addParticipant(player3);
        champ.getTemporalParticipants().addParticipant(player4);
        championshipRepository.save(champ);

        Championship champ2 = championshipRepository.findById(2);
        champ2.getTemporalParticipants().addParticipant(double1);
        champ2.getTemporalParticipants().addParticipant(double2);
        champ2.getTemporalParticipants().addParticipant(double3);
        championshipRepository.save(champ2);

        Championship champ1 = championshipRepository.findById(3);
        champ1.getTemporalParticipants().addParticipant(player2);
        champ1.getTemporalParticipants().addParticipant(player3);
        champ1.getTemporalParticipants().addParticipant(player4);
        champ1.getTemporalParticipants().addParticipant(player5);
        champ1.getTemporalParticipants().addParticipant(player6);
        champ1.getTemporalParticipants().addParticipant(player7);
        champ1.getTemporalParticipants().addParticipant(player8);
        champ1.getTemporalParticipants().addParticipant(player9);
        champ1.getTemporalParticipants().addParticipant(player10);
        champ1.getTemporalParticipants().addParticipant(player11);
        champ1.getTemporalParticipants().addParticipant(player12);
        champ1.getTemporalParticipants().addParticipant(player13);
        championshipRepository.save(champ1);

        List<Championship> champs = championshipRepository.findInProgressChampionships();

        tableRepository.save(new Tables(0, 0, champs));
        tableRepository.save(new Tables(1, 0, champs));
        tableRepository.save(new Tables(2, 0, champs));
        tableRepository.save(new Tables(0, 1, champs));
        tableRepository.save(new Tables(3, 1, champs));
        tableRepository.save(new Tables(0, 2, champs));
        tableRepository.save(new Tables(1, 2, champs));
        tableRepository.save(new Tables(2, 2, champs));

    }

}
