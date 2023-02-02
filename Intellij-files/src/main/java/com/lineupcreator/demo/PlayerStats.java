package com.lineupcreator.demo;

import java.io.Serializable;

public class PlayerStats implements Serializable{

    protected double minutesPlayed;
    protected double oldMinutesPlayed;
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
    PlayerBaseLineStats playerBaseLineStats;

    public PlayerStats() {
    }

    public void setBaseLine(PlayerBaseLineStats playerBaseLineStats) {
        this.playerBaseLineStats = playerBaseLineStats;
    }

    public void setMP(double minutesPlayed) {
        this.minutesPlayed = minutesPlayed;

    }

    public void setOldMP(double oldMinutesPlayed) {
        this.oldMinutesPlayed = oldMinutesPlayed;
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

    public void setFTM(double freeThrowsM) {
        this.freeThrowsM = freeThrowsM;
    }

    public void setFTA(double freeThrowsA) {
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

    public void setBlocks(double blocks) {
        this.blocks = blocks;
    }

    public void setTOs(double turnOvers) {
        this.turnOvers = turnOvers;
    }

    public void setFouls(double fouls) {
        this.fouls = fouls;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public double getOldMinutesPlayed() {
        return playerBaseLineStats.minutesPlayed;
    }

    public double getFGM() {
        return FGM;
    }

    public double getFGA() {
        return FGA;
    }

    public double getThreeFGM() {
        return threeFGM;
    }

    public double getThreeFGA() {
        return threeFGA;
    }

    public double getTwoFGM() {
        return twoFGM;
    }

    public double getTwoFGA() {
        return twoFGA;
    }

    public double getFreeThrowsM() {
        return freeThrowsM;
    }

    public double getFreeThrowsA() {
        return freeThrowsA;
    }

    public double getOffRB() {
        return offRB;
    }

    public double getDefRB() {
        return defRB;
    }

    public double getTotRB() {
        return totRB;
    }

    public double getAssists() {
        return assists;
    }

    public double getSteals() {
        return steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public double getTurnOvers() {
        return turnOvers;
    }

    public double getFouls() {
        return fouls;
    }

    public double getPoints() {
        return points;
    }

    public void setPlayerStatsToBaseLine() {
        oldMinutesPlayed = playerBaseLineStats.minutesPlayed;
        FGM = playerBaseLineStats.FGM;
        FGA = playerBaseLineStats.FGA;
        threeFGM = playerBaseLineStats.threeFGM;
        threeFGA = playerBaseLineStats.threeFGA;
        twoFGM = playerBaseLineStats.twoFGM;
        twoFGA = playerBaseLineStats.twoFGA;
        freeThrowsM = playerBaseLineStats.freeThrowsM;
        freeThrowsA = playerBaseLineStats.freeThrowsA;
        offRB = playerBaseLineStats.offRB;
        defRB = playerBaseLineStats.defRB;
        totRB = playerBaseLineStats.totRB;
        assists = playerBaseLineStats.assists;
        steals = playerBaseLineStats.steals;
        blocks = playerBaseLineStats.blocks;
        turnOvers = playerBaseLineStats.turnOvers;
        fouls = playerBaseLineStats.fouls;
        points = playerBaseLineStats.points;
    }
}
