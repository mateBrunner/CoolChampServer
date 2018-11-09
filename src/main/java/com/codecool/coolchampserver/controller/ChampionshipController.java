package com.codecool.coolchampserver.controller;


import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.service.ChampionshipService;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class ChampionshipController {

    @Autowired
    ChampionshipService championshipService;

    @PutMapping("/championship")
    public String createChampionship(@RequestBody Map<String, String> body) {
        Integer id = championshipService.createChampionship(body.get("name"));
        return "{\"id\":" + id + "}";
    }

    @GetMapping("/actual-championships")
    public List<ChampionshipData> actualChampionships(HttpServletResponse response) {
        System.out.println(123);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return championshipService.getAllActualChampionships();
    }

}
