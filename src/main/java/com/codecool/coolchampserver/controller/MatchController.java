package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.httpmodel.MatchResult;
import com.codecool.coolchampserver.httpmodel.PlayoffMatchResult;
import com.codecool.coolchampserver.service.MatchService;
import com.codecool.coolchampserver.service.PlayoffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MatchController {

    @Autowired
    MatchService matchService;

    @Autowired
    PlayoffService playoffService;

    @PostMapping("/save-match")
    public String saveMatch(@RequestBody Map<String, MatchResult> body) {
        matchService.updateMatch(body.get("result"));
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/delete-match-result")
    public String deleteResult(@RequestBody Map<String, Integer> body) {
        matchService.deleteResult(body.get("id"));
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/save-playoff-match")
    public String savePlayoffMatch(@RequestBody Map<String, PlayoffMatchResult> body) {
        PlayoffMatchResult result = body.get("result");
        playoffService.updatePlayoffByMatch(result);
        return "{\"value\":\"success\"}";
    }

}
