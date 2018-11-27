package com.codecool.coolchampserver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Playoff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderColumn
    private List<PlayoffMatch> matches = new ArrayList<>();

    @ElementCollection
    @OrderColumn
    private List<Integer> rankings;

    @OneToOne(cascade = CascadeType.ALL)
    private Match bronzeMatch;

    @Transient
    private final int[][][] RANKING_POSITIONS = {
            {{1, 16}, {9, 8}, {5, 12}, {13, 4}, {3, 14}, {11, 6}, {7,10}, {15, 2}},
            {{1, 8}, {5, 4}, {3, 6}, {7, 2}},
            {{1, 4}, {3, 2}},
            {{1, 2}, {3, 4}}
    };

    public Playoff() {
    }

    public Playoff(Integer sizeOfPlayoff) {
        initPlayoff(sizeOfPlayoff);
        if (sizeOfPlayoff >=4 ) {
            bronzeMatch = new Match();
        }
    }

    public List<PlayoffMatch> getMatches() {
        return matches;
    }

    public List<Integer> getRankings() { return rankings; }

    private void initPlayoff(Integer sizeOfPlayoff) {
        int initialLevel = sizeOfPlayoff > 8 ? 0 : sizeOfPlayoff > 4 ? 1 : sizeOfPlayoff > 2 ? 2 : 3;
        for (int level = initialLevel; level < 4; level++) {
            for (int row = 0; row < RANKING_POSITIONS[level].length; row++) {
                if (Math.max(RANKING_POSITIONS[level][row][0], RANKING_POSITIONS[level][row][1]) <= sizeOfPlayoff) {
                    matches.add(new PlayoffMatch(level, row));
                }
            }
        }
    }

    public void recountFromBiground(ChampionshipResult champResult) {
        rankings = getRankings(champResult);
        Position position;
        Match match;
        for (int ranking = champResult.getSizeOfPlayoff(); ranking > 0; ranking--) {
            position = getPlayerInitPositionByRanking(ranking, champResult.getSizeOfPlayoff());
            match = getMatchByPosition(position).getMatch();
            Player playerInPlace = position.getPlayerRow() == 0 ? match.getPlayer1() : match.getPlayer2();
            Player playerInRanking = champResult.getPlayerResults().get(ranking-1).getPlayer();
            if (!playerInRanking.equals(playerInPlace)) {
                if (position.getPlayerRow() == 0) {
                    match.setPlayer1(playerInRanking);
                } else {
                    match.setPlayer2(playerInRanking);
                }
                match.clearResult();
                int row = position.getRow();
                for (int level = position.getLevel() + 1; level < 4; level++) {
                    row = (int) Math.floor(row/2);
                    getMatchByPosition(new Position(level, row)).getMatch().clearMatch();
                }
            }
        }
    }

    public PlayoffMatch getMatchByPosition(Position position) {
        for (PlayoffMatch match: matches) {
            if (match.getLevel() == position.getLevel() && match.getRow() == position.getRow()) {
                return match;
            }
        }
        return null;
    }

    public Position getPlayerInitPositionByRanking(Integer ranking, Integer sizeOfPlayoff) {
         boolean isPropagated = ranking <= Math.pow(2, Math.ceil(Math.log(sizeOfPlayoff) / Math.log(2))) - sizeOfPlayoff;
         int level = 4 - (int) Math.ceil(Math.log(sizeOfPlayoff) / Math.log(2)) + (isPropagated ? 1 : 0);
         for (int r = 0; r < RANKING_POSITIONS[level].length; r++) {
             if (RANKING_POSITIONS[level][r][0] == ranking) {
                 return new Position(level, r, 0);
             } else if (RANKING_POSITIONS[level][r][1] == ranking) {
                 return new Position(level, r, 1);
             }
         }
        return null;
    }

    public void clearNextMatches(Position position) {
        position.level++;
        position.row = position.row / 2;
        Match match = getMatchByPosition(position).getMatch();
        match.clearResult();
        int nextRow;
        while (position.level != 3) {
            position.level++;
            nextRow = position.row / 2;
            match = getMatchByPosition(new Position(position.level, nextRow)).getMatch();
            match.clearMatch((position.row % 2) + 1);
            if (position.level == 3) {
                match = getMatchByPosition(new Position(3, 1)).getMatch();
                match.clearMatch((position.row % 2) + 1);
            }
        }
    }

    public List<Integer> getRankings(ChampionshipResult result) {
        return result.getPlayerResults().stream().limit(result.getSizeOfPlayoff()).map(pr -> pr.getPlayer().getId()).collect(Collectors.toList());
    }


    public static class Position {
        private int level;
        private int row;
        private int playerRow;

        public Position(int level, int row, int playerRow) {
            this.level = level;
            this.row = row;
            this.playerRow = playerRow;
        }

        public Position(int level, int row) {
            this.level = level;
            this.row = row;
        }

        public int getLevel() {return this.level;}
        public int getRow() {return this.row;}
        public int getPlayerRow() {return this.playerRow;}
    }

}
