package com.dglapps.fiestadelarbol.services;

import com.dglapps.fiestadelarbol.R;
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

    public PlayerService() {
        playerMap.put(1, new Player(1, "Equipo 1", R.id.avatar_1));
        playerMap.put(2, new Player(2, "Equipo 2", R.id.avatar_3));
        playerMap.put(3, new Player(3, "Equipo 3", R.id.avatar_2));
        playerMap.put(4, new Player(4, "Equipo 4", R.id.avatar_6));
    }

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
