package com.lineupcreator.demo;

import java.io.Serializable;

public class PlayerBaseLineStats implements Serializable {

    protected double minutesPlayed;
    //    protected double oldMinutesPlayed;
    protected double FGM; //Field Goals made
    protected double FGA; //Field Goal Attempts
    protected double threeFGM; //Three pointers made
    protected double threeFGA; //Three pointers attempted
    protected double twoFGM; //Two pointers made
    protected double twoFGA; //Two pointers attempted
    protected double freeThrowsM; //Free throws made
    protected double freeThrowsA; //Free throws attempted
    protected double offRB; //Offensive rebounds
    protected double defRB; //Defensive rebounds
    protected double totRB; //Total rebounds
    protected double assists;
    protected double steals;
    protected double blocks;
    protected double turnOvers;
    protected double fouls;
    protected double points;
    Player player;


    public void setMinutesPlayed(double minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public void setFGM(double FGM) {
        this.FGM = FGM;
    }

    public void setFGA(double FGA) {
        this.FGA = FGA;

    }


    public void setThreeFGM(double threeFGM) {
        this.threeFGM = threeFGM;
    }


    public void setThreeFGA(double threeFGA) {
        this.threeFGA = threeFGA;
    }


    public void setTwoFGM(double twoFGM) {
        this.twoFGM = twoFGM;
    }


    public void setTwoFGA(double twoFGA) {
        this.twoFGA = twoFGA;
    }


    public void setFreeThrowsM(double freeThrowsM) {
        this.freeThrowsM = freeThrowsM;
    }


    public void setFreeThrowsA(double freeThrowsA) {
        this.freeThrowsA = freeThrowsA;
    }


    public void setOffRB(double offRB) {
        this.offRB = offRB;
    }


    public void setDefRB(double defRB) {
        this.defRB = defRB;
    }


    public void setTotRB(double totRB) {
        this.totRB = totRB;
    }


    public void setAssists(double assists) {
        this.assists = assists;
    }

    public void setSteals(double steals) {
        this.steals = steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public void setTurnOvers(double turnOvers) {
        this.turnOvers = turnOvers;
    }

    public void setFouls(double fouls) {
        this.fouls = fouls;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}
