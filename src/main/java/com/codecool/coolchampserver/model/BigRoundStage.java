package com.codecool.coolchampserver.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class BigRoundStage extends RegularStage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @OrderColumn
    private List<Participant> participantList;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Match> matches;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private ExtraMatch extraMatch;


    public BigRoundStage(Set<Participant> participants, int numberOfMatches) {
        participantList = new ArrayList<>(participants);
        Collections.shuffle(participantList);
        matches = new ArrayList<>();
        generateMatches(numberOfMatches);
    }

    public BigRoundStage() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public List<Participant> getParticipantList() {
        return participantList;
    }

    public void setPlayerList(List<Participant> participantList) {
        this.participantList = participantList;
    }

    @Override
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public ExtraMatch getExtraMatch() {
        return this.extraMatch;
    }

    private void generateMatches(int numberOfMatches) {
        for (int i = 1; i <= numberOfMatches / 2; i++) {
            for (int j = 0; j < participantList.size(); j++ ) {
                matches.add(new Match(
                        participantList.get(j),
                        participantList.get((j + i) % participantList.size())
                ));
            }
        }
        if (numberOfMatches % 2 == 1) {
            for (int j = 0; j < participantList.size() / 2; j++ ) {
                matches.add(new Match(
                        participantList.get(j),
                        participantList.get((j + (participantList.size() / 2)) % participantList.size() )
                ));
            }

            if (participantList.size() % 2 == 1) {
                Match match = new Match(
                        participantList.get(participantList.size() - 1),
                        participantList.get(participantList.size() / 2)
                );
                matches.add(match);
                this.extraMatch = new ExtraMatch(
                        participantList.get(participantList.size() / 2),
                        match
                );
            }

        }

    }

}
