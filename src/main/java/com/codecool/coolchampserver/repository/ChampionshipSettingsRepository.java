package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChampionshipSettingsRepository extends JpaRepository<ChampionshipSettings, Long> {


}
