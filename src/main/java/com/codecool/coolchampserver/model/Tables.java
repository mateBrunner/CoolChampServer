package com.codecool.coolchampserver.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Tables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer col;
    private Integer row;
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    private Match match;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Championship> championships;

    public Tables() {}

    public Tables(Integer col, Integer row) {
        this.col = col;
        this.row = row;
    }

    public Tables(Integer col, Integer row, List<Championship> championships) {
        this.col = col;
        this.row = row;
        this.championships = championships;
    }

    public Integer getId() { return this.id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getCol() { return this.col; }

    public void setCol(Integer col) { this.col = col; }

    public Integer getRow() { return this.row; }

    public void setRow(Integer row) { this.row = row; }

    public Match getMatch() { return this.match; }

    public void setMatch(Match match) { this.match = match; }

    public List<Championship> getChampionships() { return this.championships; }

    public void setChampionships(List<Championship> champs) { this.championships = champs; }

    public void addChampionship(Championship champ) { this.championships.add(champ); }

}
