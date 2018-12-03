package com.codecool.coolchampserver.repository;

import com.codecool.coolchampserver.model.Tables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TablesRepository extends JpaRepository<Tables, Long> {

    List<Tables> findAll();

    Tables findById(Integer id);

}
