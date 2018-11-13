package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.httpmodel.BasicValue;
import com.codecool.coolchampserver.httpmodel.ChampPlayerObject;
import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipSettings;
import com.codecool.coolchampserver.model.ChampionshipStatus;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.repository.PlayerRepository;
import com.codecool.coolchampserver.service.ChampionshipService;
import com.codecool.coolchampserver.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ChampionshipController {

    @Autowired
    ChampionshipService championshipService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/actual-championships")
    public List<ChampionshipData> actualChampionships(HttpServletResponse response) {
        return championshipService.getAllActualChampionships();
    }

    @GetMapping("/selected-players/{id}")
    public Set<Player> selectedPlayers(@PathVariable("id") String id) {
        return championshipService.getSelectedPlayers(Integer.valueOf(id));
    }

    @GetMapping("/players")
    public List<Player> players() {
        return playerService.getPlayers();
    }

    @PostMapping("/select-player")
    public String selectPlayer(@RequestBody Map<String, ChampPlayerObject> body) {
        championshipService.selectPlayer(body.get("data").getChampId(), body.get("data").getPlayerId());
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/deselect-player")
    public String deselectPlayer(@RequestBody Map<String, ChampPlayerObject> body) {
        championshipService.deselectPlayer(body.get("data").getChampId(), body.get("data").getPlayerId());
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/championship")
    public ChampionshipData createChampionship(@RequestBody Map<String, String> body, HttpServletResponse response) {
        Integer id = championshipService.createChampionship(body.get("name"));
        return new ChampionshipData(id, body.get("name"));
    }

    @GetMapping("/championship-status/{id}")
    public BasicValue championshipStatus(@PathVariable("id") String id) {
        ChampionshipStatus status = championshipService.getChampionshipStatus(Integer.valueOf(id));
        return new BasicValue(status.toString());
    }

    @GetMapping("/championship-settings/{id}")
    public ChampionshipSettings championshipSettings(@PathVariable("id") String id) {
        return championshipService.getChampionshipById(Integer.valueOf(id)).getSettings();
    }

    @PostMapping("/update-championship-settings/{id}")
    public String updateChampionshipSettings(@RequestBody Map<String, ChampionshipSettings> body,
                                             @PathVariable("id") String id) {
        championshipService.updateSettings(Integer.valueOf(id), body.get("settings"));
        return "{\"value\":\"success\"}";
    }

}
