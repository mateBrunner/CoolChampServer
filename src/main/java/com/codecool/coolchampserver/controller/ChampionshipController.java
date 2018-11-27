package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.httpmodel.*;
import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.service.ChampionshipService;
import com.codecool.coolchampserver.service.MatchService;
import com.codecool.coolchampserver.service.PlayerService;
import com.codecool.coolchampserver.service.PlayoffService;
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
    PlayoffService playoffService;

    @Autowired
    MatchService matchService;

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

    @GetMapping("/championship-format/{id}")
    public BasicValue championshipFormat(@PathVariable("id") String id) {
        String format = championshipService.getChampionshipFormat(Integer.valueOf(id));
        return new BasicValue(format);
    }

    @GetMapping("/championship-settings/{id}")
    public ChampionshipSettings championshipSettings(@PathVariable("id") String id) {
        return championshipService.getChampionshipById(Integer.valueOf(id)).getSettings();
    }

    @GetMapping("/championship-matches/{id}")
    public RegularStage championshipMatches(@PathVariable("id") Integer id) {
        return championshipService.getChampionshipById(id).getRegularStage();
    }

    @GetMapping("/championship-result/{id}")
    public ChampionshipResult championshipResult(@PathVariable("id") Integer id) {
        return championshipService.getChampionshipResult(id);
    }

    @GetMapping("/championship-playoff/{id}")
    public Playoff championshipPlayoff(@PathVariable("id") Integer id) {
        return championshipService.getPlayoff(id);
    }

    @PostMapping("/update-championship-settings/{id}")
    public String updateChampionshipSettings(@RequestBody Map<String, ChampionshipSettings> body,
                                             @PathVariable("id") String id) {
        championshipService.updateSettings(Integer.valueOf(id), body.get("settings"));
        return "{\"value\":\"success\"}";
    }

    @GetMapping("/start/{id}")
    public String startChampionship(@PathVariable("id") String id) {
        championshipService.startChampionship(Integer.valueOf(id));
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/archivate-championship/{id}")
    public String archivateChampionship(@PathVariable("id") String id) {
        championshipService.archivateChampionship(Integer.valueOf(id));
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/delete-championship/{id}")
    public String deleteChampionship(@PathVariable("id") String id) {
        championshipService.deleteChampionship(Integer.valueOf(id));
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/save-match")
    public String saveMatch(@RequestBody Map<String, MatchResult> body) {
        MatchResult result = body.get("result");
        matchService.updateMatch(result);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/save-playoff-match")
    public String savePlayoffMatch(@RequestBody Map<String, PlayoffMatchResult> body) {
        PlayoffMatchResult result = body.get("result");
        playoffService.updatePlayoffByMatch(result);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/delete-match-result")
    public String deleteResult(@RequestBody Map<String, Integer> body) {
        matchService.deleteResult(body.get("id"));
        return "{\"value\":\"success\"}";
    }

}
