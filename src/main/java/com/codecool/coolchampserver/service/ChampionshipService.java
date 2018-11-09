package com.codecool.coolchampserver.service;


import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipStatus;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChampionshipService {

    @Autowired
    ChampionshipRepository championshipRepository;

    public List<ChampionshipData> getAllActualChampionships() {
        List<Championship> actChamps = championshipRepository.findActualChampionships();
        List<ChampionshipData> actChampsData = new ArrayList<>();
        for (Championship championship : actChamps) {
            actChampsData.add(new ChampionshipData(championship.getId(), championship.getName()));
        }
        return actChampsData;
    }

    public Integer createChampionship(String name) {
        Championship newChampionship = new Championship(name);
        championshipRepository.save(newChampionship);
        return newChampionship.getId();
    }

}
