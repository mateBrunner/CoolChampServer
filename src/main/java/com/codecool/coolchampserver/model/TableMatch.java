package com.codecool.coolchampserver.model;

import javax.persistence.*;

@Entity
public class TableMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.MERGE)
    private Match match;
    private Integer champId;

    public TableMatch() {}

    public TableMatch(Match match, Integer champId) {
        this.match = match;
        this.champId = champId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Integer getChampId() {
        return champId;
    }

    public void setChampId(Integer champId) {
        this.champId = champId;
    }

}
