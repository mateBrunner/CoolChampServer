package com.codecool.coolchampserver.service;

import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.httpmodel.DoubleParticipantObject;
import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.ParticipantRepository;
import com.codecool.coolchampserver.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ChampionshipService {

    @Autowired
    ChampionshipRepository championshipRepository;

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    TablesRepository tablesRepository;

    public List<ChampionshipData> getAllActualChampionshipsData() {
        List<Championship> actChamps = getAllActualChampionships();
        List<ChampionshipData> actChampsDataList = new ArrayList<>();
        for (Championship championship : actChamps) {
            actChampsDataList.add(new ChampionshipData(championship.getId(), championship.getSettings().getNewChampName()));
        }
        return actChampsDataList;
    }

    public List<Championship> getAllActualChampionships() {
        return championshipRepository.findActualChampionships();
    }

    public List<ChampionshipData> getAllInProgressChampionshipsData() {
        List<Championship> ipChamps = getAllInProgressChampionships();
        List<ChampionshipData> ipChampsDataList = new ArrayList<>();
        for (Championship championship : ipChamps) {
            ipChampsDataList.add(new ChampionshipData(championship.getId(), championship.getSettings().getNewChampName()));
        }
        return ipChampsDataList;
    }

    public List<Championship> getAllInProgressChampionships() { return championshipRepository.findInProgressChampionships(); }

    public Set<Participant> getSelectedParticipants(Integer id) {
        return championshipRepository.findById(id).getTemporalParticipants().getParticipants();
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
    public Set<Participant> addDouble(Integer champId) {
        Championship championship = championshipRepository.findById(champId);
        championship.getTemporalParticipants().addParticipant(new Doubles());
        championshipRepository.save(championship);
        return championship.getTemporalParticipants().getParticipants();
    }

    @Transactional
    public Set<Participant> addPlayerToDouble(DoubleParticipantObject data) {
        Doubles doubles = (Doubles) participantRepository.findById(data.getDoubleId());
        doubles.addPlayer((Player) participantRepository.findById(data.getParticipantId()));
        participantRepository.save(doubles);
        return championshipRepository.findById(data.getChampId()).getTemporalParticipants().getParticipants();
    }

    @Transactional
    public void selectParticipant(Integer champId, Integer participantId) {
        Championship champ = championshipRepository.findById(champId);
        champ.getTemporalParticipants().addParticipant(participantRepository.findById(participantId));
        championshipRepository.save(champ);
    }

    @Transactional
    public void deselectParticipant(Integer champId, Integer participantId) {
        Championship champ = championshipRepository.findById(champId);
        champ.getTemporalParticipants().removeParticipant(participantRepository.findById(participantId));
        championshipRepository.save(champ);
    }

    public Integer createChampionship(ChampionshipData data) {
        Championship newChampionship = new Championship(data.getName(), ParticipantType.valueOf(data.getType()));
        championshipRepository.save(newChampionship);
        return newChampionship.getId();
    }

    public void startChampionship(Integer id) {
        Championship champ = championshipRepository.findById(id);
        Set<Participant> participants = champ.getTemporalParticipants().getParticipants();
        String format = champ.getSettings().getFormat();
        if (format.equals("big-round")) {
            int numberOfMatches = champ.getSettings().getNumberOfMatches();
            champ.setRegularStage(new BigRoundStage(participants, numberOfMatches));
        } else if (format.equals("group")) {
            champ.setRegularStage(new GroupStage());
        }
        champ.setPlayoff(new Playoff(champ.getSettings().getSizeOfPlayoff()));
        champ.setStatus(ChampionshipStatus.INPROGRESS);
        championshipRepository.save(champ);

        tablesRepository.findAll().stream().forEach(table -> {
            table.addChampionship(champ);
            tablesRepository.save(table);
        });
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
