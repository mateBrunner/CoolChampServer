package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.model.Doubles;
import com.codecool.coolchampserver.model.Participant;
import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.model.ParticipantStats;
import com.codecool.coolchampserver.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ParticipantController {

    @Autowired
    ParticipantService participantService;

    @PostMapping("/add-player")
    public Player addPlayer(@RequestBody Map<String, String> body) {
        return participantService.addPlayer(body.get("name"));
    }

    @GetMapping("/participant-stats/{id}")
    public ParticipantStats getParticipantStats(@PathVariable("id") Integer partId) {
        return participantService.getParticipantStats(partId);
    }

    @GetMapping("/participants")
    public List<Participant> participants() {
        return participantService.getParticipants();
    }

    @GetMapping("/players")
    public List<Player> players() {
        return participantService.getPlayers();
    }

    @GetMapping("/doubles")
    public List<Doubles> doubles() {
        return participantService.getDoubles();
    }

}
