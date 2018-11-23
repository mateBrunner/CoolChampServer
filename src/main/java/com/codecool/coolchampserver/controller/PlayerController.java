package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.model.PlayerStats;
import com.codecool.coolchampserver.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/add-player")
    public Player addPlayer(@RequestBody Map<String, String> body) {
        return playerService.addPlayer(body.get("name"));
    }

    @GetMapping("/player-stats/{id}")
    public PlayerStats getPlayerStats(@PathVariable("id") String id) {
        return playerService.getPlayerStats(Integer.valueOf(id));
    }

}
