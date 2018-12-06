package com.codecool.coolchampserver.util;

import com.codecool.coolchampserver.model.Player;

import java.util.List;

public class Util {

    public static boolean isThereAinB(List<Player> listA, List<Player> listB) {
        listA.retainAll(listB);
        return listA.size() > 0;
    }

}
