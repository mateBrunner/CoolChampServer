package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Match;
import com.codecool.coolchampserver.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {


    Match findById(Integer id);

    @Query("SELECT m FROM Match m WHERE m.point1 IS NOT null AND ( m.player1 = :player OR m.player2 = :player ) ORDER BY m.date")
    List<Match> getMatchesByPlayer(@Param("player") Player player);

}
