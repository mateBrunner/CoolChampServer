package com.codecool.coolchampserver.controller;


import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.service.ChampionshipService;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class ChampionshipController {

    @Autowired
    ChampionshipService championshipService;

    @PostMapping("/championship")
    public ChampionshipData createChampionship(@RequestBody Map<String, String> body, HttpServletResponse response) {
        Integer id = championshipService.createChampionship(body.get("name"));
        return new ChampionshipData(id, body.get("name"));
    }

    @GetMapping("/actual-championships")
    public List<ChampionshipData> actualChampionships(HttpServletResponse response) {
        //response.addHeader("Access-Control-Allow-Origin", "*");
        return championshipService.getAllActualChampionships();
    }

}
