package com.codecool.coolchampserver.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Playoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Playoff() {
    }

}
