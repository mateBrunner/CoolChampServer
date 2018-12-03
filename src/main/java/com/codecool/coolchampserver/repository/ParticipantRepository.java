package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Participant;
import com.codecool.coolchampserver.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    List<Participant> findAll();

    Participant findById(Integer id);

}
