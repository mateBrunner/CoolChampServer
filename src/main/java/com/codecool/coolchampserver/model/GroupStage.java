package com.codecool.coolchampserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class GroupStage extends RegularStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public GroupStage () {}

    @Override
    public List<Match> getMatches() { return null; }

}
