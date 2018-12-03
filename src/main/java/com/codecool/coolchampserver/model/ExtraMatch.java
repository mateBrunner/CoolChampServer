package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class ExtraMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Participant participant;
    @ManyToOne
    private Match match;

    public ExtraMatch(Participant participant, Match match) {
        this.participant = participant;
        this.match = match;
    }

    public ExtraMatch() {}

    public Participant getParticipant() {
        return participant;
    }

    public Match getMatch() {
        return match;
    }
}
