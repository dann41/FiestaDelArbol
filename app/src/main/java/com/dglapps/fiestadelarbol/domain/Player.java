package com.dglapps.fiestadelarbol.domain;

public class Player {

    private final int playerId;
    private final String playerName;
    private final int avatarId;

    public Player(int playerId, String playerName, int avatarId) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.avatarId = avatarId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getAvatarId() {
        return avatarId;
    }
}
