package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Championship;
import com.codecool.coolchampserver.model.ChampionshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {

    Championship findById(Integer id);

    @Query("SELECT c FROM Championship c WHERE c.status = com.codecool.coolchampserver.model.ChampionshipStatus.NEW")
    List<Championship> findActualChampionships();

}
