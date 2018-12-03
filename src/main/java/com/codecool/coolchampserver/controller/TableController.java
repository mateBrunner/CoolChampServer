package com.codecool.coolchampserver.controller;

import com.codecool.coolchampserver.httpmodel.ChampionshipData;
import com.codecool.coolchampserver.model.Tables;
import com.codecool.coolchampserver.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
public class TableController {

    @Autowired
    TablesService tableService;

    @GetMapping("/tables")
    public List<Tables> getTables() {
        return tableService.getAll();
    }

    @GetMapping("/champs-for-table/{id}")
    public List<ChampionshipData> getChampsForTable(@PathVariable("id") Integer tableId) {
        return tableService.getChampsForTable(tableId);
    }

    @PostMapping("/save-champs-to-table")
    public String saveChampsToTable(@RequestBody Map<String, Object> data) {
        tableService.saveChampsToTable((Integer) data.get("tableId"), (List<Integer>) data.get("champs"));
        return "{\"value\":\"success\"}";
    }

    @GetMapping("/delete-table/{id}")
    public String deleteTable(@PathVariable("id") Integer tableId) {
        tableService.deleteTable(tableId);
        return "{\"value\":\"success\"}";
    }

    @PostMapping("/new-table")
    public String newTable(@RequestBody Map<String, Integer> data) {
        tableService.newTable(data.get("col"), data.get("row"));
        return "{\"value\":\"success\"}";
    }


}
