package com.codecool.coolchampserver.service;


import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.Tables;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TablesService {

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    ChampionshipRepository championshipRepository;

    public List<Tables> getAll() {
        return tablesRepository.findAll();
    }

    public List<ChampionshipData> getChampsForTable(Integer tableId) {
        return tablesRepository.findById(tableId).getChampionships().stream().map(champ ->
        new ChampionshipData(champ.getId(), champ.getSettings().getNewChampName())
        ).collect(Collectors.toList());
    }

    public void saveChampsToTable(Integer tableId, List<Integer> champIds) {
        Tables table = tablesRepository.findById(tableId);
        table.setChampionships(
                champIds.stream().map(champId ->
                championshipRepository.findById(champId)).collect(Collectors.toList()));
        tablesRepository.save(table);
    }

    public void deleteTable(Integer tableId) {
        tablesRepository.delete(tablesRepository.findById(tableId));
    }

    public void newTable(Integer column, Integer row) {
        tablesRepository.save(new Tables(column, row));
    }

}
