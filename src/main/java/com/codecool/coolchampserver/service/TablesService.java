package com.codecool.coolchampserver.service;


import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.httpmodel.MatchResult;
import com.codecool.coolchampserver.httpmodel.PlayoffMatchResult;
import com.codecool.coolchampserver.model.TableMatch;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.model.Tables;
import com.codecool.coolchampserver.repository.ChampionshipRepository;
import com.codecool.coolchampserver.repository.TablesRepository;
import com.codecool.coolchampserver.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TablesService {

    @Autowired
    TablesRepository tablesRepository;

    @Autowired
    ChampionshipRepository championshipRepository;

    @Autowired
    ChampionshipService championshipService;

    @Autowired
    PlayoffService playoffService;

    @Autowired
    MatchService matchService;

    public List<Tables> getAll() {
        return tablesRepository.findAll().stream().map(table -> {
            if (table.getTableMatch() != null && table.getTableMatch().getMatch().isPlayed()) {
                table.setTableMatch(null);
            }
            return table;
            }
        ).collect(Collectors.toList());
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

    public void clearTable(Integer tableId) {
        Tables table = tablesRepository.findById(tableId);
        table.setTableMatch(null);
        tablesRepository.save(table);
    }

    public void newTable(Integer column, Integer row) {
        tablesRepository.save(new Tables(column, row));
    }

    public TableMatch randomMatch(Integer tableId) {
        System.out.println(tableId);
        Set<Player> busyPlayers = new HashSet<>();
        getAll().stream().forEach(table -> {
            if (table.getTableMatch() != null && table.getId() != tableId) {
                busyPlayers.addAll(table.getTableMatch().getMatch().getPlayers());
            }
        });

        List<Player> busyPlayerList = new ArrayList<>(busyPlayers);
        List<TableMatch> allMatches = new ArrayList<>();
        tablesRepository.findById(tableId).getChampionships().stream().forEach(champ ->
            allMatches.addAll(
                championshipService.getPlayableMatches(champ).stream()
                .filter(match -> !Util.isThereAinB(match.getPlayers(), busyPlayerList))
                .map(match -> new TableMatch(match, champ.getId())).collect(Collectors.toList())
            ));

        if (allMatches.size() > 0) {
            TableMatch randomMatch = allMatches.get(new Random().nextInt(allMatches.size()));
            Tables table = tablesRepository.findById(tableId);
            table.setTableMatch(randomMatch);
            tablesRepository.save(table);
            return randomMatch;
        } else {
            return null;
        }
    }

    public void saveTableMatch(Integer champId, MatchResult result) {
        if (championshipRepository.findById(champId).getPlayoff().containsMatch(result.getMatchId())) {
            playoffService.updatePlayoffByMatch(new PlayoffMatchResult(champId, result));
        } else {
            matchService.updateMatch(result);
        }
        Tables currentTable = null;
        for (Tables table: tablesRepository.findAll()) {
            if (table.getTableMatch() != null && table.getTableMatch().getMatch().getId().equals(result.getMatchId())) {
                currentTable = table;
                break;
            }
        }
        currentTable.setTableMatch(null);
        tablesRepository.save(currentTable);
    }

}
