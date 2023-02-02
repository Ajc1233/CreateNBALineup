package com.lineupcreator.demo;

public class PlayerLineUpHolder {
    Player[] players;

    private final static PlayerLineUpHolder INSTANCE = new PlayerLineUpHolder();

    public PlayerLineUpHolder() {
    }

    public static PlayerLineUpHolder getInstance() {
        return INSTANCE;
    }

    public void setPlayer(Player[] players) {
        this.players = players;
    }

    public Player[] getPlayers() {
        return this.players;
    }

}
