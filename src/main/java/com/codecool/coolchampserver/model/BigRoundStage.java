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
    private List<Player> playerList;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Match> matches;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private ExtraMatch extraMatch;


    public BigRoundStage(Set<Player> players, int numberOfMatches) {
        playerList = new ArrayList<>(players);
        Collections.shuffle(playerList);
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
    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

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
            for (int j = 0; j < playerList.size(); j++ ) {
                matches.add(new Match(
                    playerList.get(j),
                    playerList.get((j + i) % playerList.size())
                ));
            }
        }
        if (numberOfMatches % 2 == 1) {
            for (int j = 0; j < playerList.size() / 2; j++ ) {
                matches.add(new Match(
                   playerList.get(j),
                   playerList.get((j + (playerList.size() / 2)) % playerList.size() )
                ));
            }

            if (playerList.size() % 2 == 1) {
                Match match = new Match(
                        playerList.get(playerList.size() - 1),
                        playerList.get(playerList.size() / 2)
                );
                matches.add(match);
                this.extraMatch = new ExtraMatch(
                        playerList.get(playerList.size() / 2),
                        match
                );
            }

        }

    }

}
