package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.model.Player;
import com.codecool.coolchampserver.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/add-player")
    public Player addPlayer(@RequestBody Map<String, String> body) {
        return playerService.addPlayer(body.get("name"));
    }

}
