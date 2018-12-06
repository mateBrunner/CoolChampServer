package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.httpmodel.*;
import com.codecool.coolchampserver.model.*;
import com.codecool.coolchampserver.repository.DoublesRepository;
import com.codecool.coolchampserver.service.ChampionshipService;
import com.codecool.coolchampserver.service.MatchService;
import com.codecool.coolchampserver.service.ParticipantService;
import com.codecool.coolchampserver.service.PlayoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class ChampionshipController {

    @Autowired
    ChampionshipService championshipService;

    @GetMapping("/actual-championships")
    public List<ChampionshipData> actualChampionships() {
        return championshipService.getAllActualChampionshipsData();
    }

    @GetMapping("/inprogress-championships")
    public List<ChampionshipData> inprogressChampionships() { return championshipService.getAllInProgressChampionshipsData(); }

    @GetMapping("/selected-participants/{id}")
    public Set<Participant> selectedParticipants(@PathVariable("id") Integer champId) {
        return championshipService.getSelectedParticipants(champId);
    }

    @PostMapping("/add-double")
    public Set<Participant> addDouble(@RequestBody Map<String, Integer> body) {
        return championshipService.addDouble(body.get("data"));
    }

    @PostMapping("/add-player-to-double")
    public Set<Participant> addPlayerToDouble(@RequestBody Map<String, DoubleParticipantObject> body) {
        return championshipService.addPlayerToDouble(body.get("data"));
    }

    @PostMapping("/select-participant")
    public String selectParticipant(@RequestBody Map<String, ChampParticipantObject> body) {
        championshipService.selectParticipant(body.get("data").getChampId(), body.get("data").getParticipantId());
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/deselect-participant")
    public String deselectParticipant(@RequestBody Map<String, ChampParticipantObject> body) {
        championshipService.deselectParticipant(body.get("data").getChampId(), body.get("data").getParticipantId());
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/championship")
    public ChampionshipData createChampionship(@RequestBody Map<String, ChampionshipData> body) {
        Integer id = championshipService.createChampionship(body.get("data"));
        return new ChampionshipData(id, body.get("data").getName(), body.get("data").getType());
    }

    @GetMapping("/championship-status/{id}")
    public BasicValue championshipStatus(@PathVariable("id") Integer champId) {
        ChampionshipStatus status = championshipService.getChampionshipStatus(champId);
        return new BasicValue(status.toString());
    }

    @GetMapping("/championship-format/{id}")
    public BasicValue championshipFormat(@PathVariable("id") Integer champId) {
        String format = championshipService.getChampionshipFormat(champId);
        return new BasicValue(format);
    }

    @GetMapping("/championship-settings/{id}")
    public ChampionshipSettings championshipSettings(@PathVariable("id") Integer champId) {
        return championshipService.getChampionshipById(champId).getSettings();
    }

    @GetMapping("/championship-matches/{id}")
    public RegularStage championshipMatches(@PathVariable("id") Integer champId) {
        return championshipService.getChampionshipById(champId).getRegularStage();
    }

    @GetMapping("/championship-result/{id}")
    public ChampionshipResult championshipResult(@PathVariable("id") Integer id) {
        return championshipService.getChampionshipResult(id);
    }

    @GetMapping("/championship-playoff/{id}")
    public Playoff championshipPlayoff(@PathVariable("id") Integer champId) {
        return championshipService.getPlayoff(champId);
    }

    @GetMapping("/championship-chances/{id}")
    public String championshipChances(@PathVariable("id") Integer champId) {
        championshipService.getChances(champId);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/update-championship-settings/{id}")
    public String updateChampionshipSettings(@RequestBody Map<String, ChampionshipSettings> body,
                                             @PathVariable("id") Integer champId) {
        championshipService.updateSettings(champId, body.get("settings"));
        return "{\"value\":\"success\"}";
    }

    @GetMapping("/start/{id}")
    public String startChampionship(@PathVariable("id") Integer champId) {
        championshipService.startChampionship(champId);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/archivate-championship/{id}")
    public String archivateChampionship(@PathVariable("id") Integer champId) {
        championshipService.archivateChampionship(champId);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/delete-championship/{id}")
    public String deleteChampionship(@PathVariable("id") Integer champId) {
        championshipService.deleteChampionship(champId);
        return "{\"value\":\"success\"}";
    }




}
