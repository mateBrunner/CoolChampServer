package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.PlayerRepository;
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

    public String getChampionshipFormat(Integer id) {
        Championship championship = championshipRepository.findById(id);
        return championship.getSettings().getFormat();
    }

    @Transactional
    public void updateSettings(Integer id, ChampionshipSettings settings) {
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

    public void startChampionship(Integer id) {
        Championship champ = championshipRepository.findById(id);
        Set<Player> players = champ.getTemporalPlayers().getPlayers();
        String format = champ.getSettings().getFormat();
        if (format.equals("big-round")) {
            int numberOfMatches = champ.getSettings().getNumberOfMatches();
            champ.setRegularStage(new BigRoundStage(players, numberOfMatches));
        } else if (format.equals("group")) {
            champ.setRegularStage(new GroupStage());
        }
        champ.setPlayoff(new Playoff(champ.getSettings().getSizeOfPlayoff()));
        champ.setStatus(ChampionshipStatus.INPROGRESS);
        championshipRepository.save(champ);
    }

    public ChampionshipResult getChampionshipResult(Integer id) {
        return ChampionshipResult.generateResult(championshipRepository.findById(id));
    }

    public Playoff getPlayoff(Integer id) {
        Championship champ = championshipRepository.findById(id);
        if (champ.getSettings().getFormat().equals("big-round")) {
            champ.getPlayoff().recountFromBiground(ChampionshipResult.generateResult(champ));
        }
        championshipRepository.save(champ);
        return champ.getPlayoff();
    }

    @Transactional
    public void deleteChampionship(Integer id) {
        Championship champ = championshipRepository.findById(id);
        championshipRepository.delete(champ);
    }

    public void archivateChampionship(Integer id) {
        Championship champ = championshipRepository.findById(id);
        champ.setStatus(ChampionshipStatus.ARCHIVE);
        championshipRepository.save(champ);
    }

}
