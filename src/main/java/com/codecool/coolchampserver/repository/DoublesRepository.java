package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Doubles;
import com.codecool.coolchampserver.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoublesRepository extends JpaRepository<Doubles, Long> {

    List<Doubles> findAll();

    Doubles findById(Integer id);

}
