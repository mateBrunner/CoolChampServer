package com.codecool.coolchampserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RegularStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id = null;

    public List<Participant> getParticipantList() {
        return new ArrayList<>();
    }

}
