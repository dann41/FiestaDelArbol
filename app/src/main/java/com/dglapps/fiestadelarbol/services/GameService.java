package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.domain.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameService {

    private List<Player> playerList;
    private Iterator<Player> iterator;
    private int round;

    public GameService() {
        playerList = new ArrayList<>();
    }

    public void initGame(List<Player> players) {
        playerList = new ArrayList<>(players);
        round = 0;
        nextRound();
    }

    public Player nextPlayer() {
        Player player = null;
        if (!isEndOfRound()) {
            player = iterator.next();
        }
        return player;
    }

    public boolean isEndOfRound() {
        return !iterator.hasNext();
    }

    public void nextRound() {
        round++;
        iterator = playerList.iterator();
    }

    public int getRound() {
        return round;
    }

}
