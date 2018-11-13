package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findAll();

    Player findById(Integer id);

}
