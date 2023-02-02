package com.lineupcreator.demo;


public final class PlayerArrayHolder{


    private Player[] players;
    private Player player;
    private final static PlayerArrayHolder INSTANCE = new PlayerArrayHolder();


    private PlayerArrayHolder(){}

    public static PlayerArrayHolder getInstance(){
        return INSTANCE;
    }


    public void setPlayers(Player[] players){
        this.players = players;
    }

    public Player[] getPlayers(){
        return this.players;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return this.player;
    }
}

