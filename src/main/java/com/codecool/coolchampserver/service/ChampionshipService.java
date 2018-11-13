package com.codecool.coolchampserver.service;


import com.codecool.coolchampserver.httpmodel.ChampPlayerObject;
import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipSettings;
import com.codecool.coolchampserver.model.ChampionshipStatus;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ChampionshipService {

    @Autowired
    ChampionshipRepository championshipRepository;

    @Autowired
    PlayerRepository playerRepository;

    public List<ChampionshipData> getAllActualChampionships() {
        List<Championship> actChamps = championshipRepository.findActualChampionships();
        List<ChampionshipData> actChampsData = new ArrayList<>();
        for (Championship championship : actChamps) {
            actChampsData.add(new ChampionshipData(championship.getId(), championship.getSettings().getNewChampName()));
        }
        return actChampsData;
    }

    public Set<Player> getSelectedPlayers(Integer id) {
        return championshipRepository.findById(id).getTemporalPlayers().getPlayers();
    }

    public Championship getChampionshipById(Integer id) {
        return championshipRepository.findById(id);
    }

    public ChampionshipStatus getChampionshipStatus(Integer id) {
        Championship championship = championshipRepository.findById(id);
        return championship.getStatus();
    }

    @Transactional
    public void updateSettings(Integer id, ChampionshipSettings settings) {
        System.out.println("update");
        System.out.println(settings.toString());
        Championship championship = championshipRepository.findById(id);
        championship.setSettings(settings);
        settings.setChampionship(championship);
        championshipRepository.save(championship);
    }

    @Transactional
    public void selectPlayer(Integer champId, Integer playerId) {
        Championship champ = championshipRepository.findById(champId);
        champ.getTemporalPlayers().addPlayer(playerRepository.findById(playerId));
        championshipRepository.save(champ);
    }

    @Transactional
    public void deselectPlayer(Integer champId, Integer playerId) {
        Championship champ = championshipRepository.findById(champId);
        champ.getTemporalPlayers().removePlayer(playerRepository.findById(playerId));
        championshipRepository.save(champ);
    }

    public Integer createChampionship(String name) {
        Championship newChampionship = new Championship(name);
        championshipRepository.save(newChampionship);
        return newChampionship.getId();
    }

}
