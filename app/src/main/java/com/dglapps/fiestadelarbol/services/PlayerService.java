package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.domain.Player;

import java.util.*;

public class PlayerService {

    private static final Comparator<? super Player> ID_COMPARATOR = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            return Integer.compare(o1.getPlayerId(), o2.getPlayerId());
        }
    };

    private Map<Integer, Player> playerMap = new HashMap<>();

    public void savePlayer(Player player) {
        playerMap.put(player.getPlayerId(), player);
    }

    public List<Player> getPlayers(){
        List<Player> list = new ArrayList<>(playerMap.values());
        Collections.sort(list, ID_COMPARATOR);
        return list;
    }

    public Player getPlayer(int playerId) {
        return playerMap.get(playerId);
    }

}
