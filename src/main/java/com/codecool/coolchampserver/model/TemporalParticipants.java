package com.codecool.coolchampserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class TemporalParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER, cascade={CascadeType.REFRESH, CascadeType.PERSIST})
    @OrderColumn
    private Set<Participant> participants;

    public TemporalParticipants() {
    }

    public TemporalParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public Set<Participant> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

}
