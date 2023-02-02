package com.lineupcreator.demo;

import java.io.Serializable;


public class Player implements Serializable{

    private String name;
    private String position;
    private String age;
    private String team;
    private String gamesPlayed;
    private String gamesStarted;
    private String FGP;//fieldGoalPercentage
    private String threeFGP; //Three point percentage
    private String twoFGP; //Two point percentage
    private String eFGP; //Effective field goal percentage.
    private String freeThrowP; //Free throw percentage
    public PlayerStats playerStats;
    private String PositionSetByUser;



    public void setPositionSetByUser(String PositionSetByUser){
        this.PositionSetByUser = PositionSetByUser;
    }

    public String getPositionSetByUser (){
        return PositionSetByUser;
    }
    public Player() {

    }



    public Player(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setGamesP(String gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setGamesS(String gamesStarted) {
        this.gamesStarted = gamesStarted;
    }

    public void setFGP(String FGP) {
        //Some players do not have a Field Goal Percentage, if they do not,
        //the string 0.0 will be used instead of a blank space
        if (FGP.equals("")) {
            FGP = "0.0";
        }
        this.FGP = FGP;
    }

    public void setThreeFGP(String threeFGP) {
        if (threeFGP.equals("")) {
            threeFGP = "0.0";
        }
        this.threeFGP = threeFGP;
    }

    public void setTwoFGP(String twoFGP) {
        if (twoFGP.equals("")) {
            twoFGP = "0.0";
        }
        this.twoFGP = twoFGP;
    }

    public void setEFGP(String eFGP) {
        if (eFGP.equals("")) {
            eFGP = "0.0";

        }
        this.eFGP = eFGP;
    }

    public void setFreeThrowP(String freeThrowP) {
        if (freeThrowP.equals("")) {
            freeThrowP = "0.0";
        }
        this.freeThrowP = freeThrowP;
    }

    public void setPlayerStatClass(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }



    public String getPosition() {
        return position;
    }

    public int getAge() {
        return Integer.parseInt(age);
    }

    public String getTeam() {
        return team;
    }

    public int getGamesPlayed() {
        return Integer.parseInt(gamesPlayed);
    }

    public String getGamesStarted() {
        if(gamesStarted.equals(""))
            return "N/A";
        else
            return gamesStarted;
    }

    public double getFGP() {
        return Double.parseDouble(FGP);
    }

    public double getThreeFGP() {
        return Double.parseDouble(threeFGP);
    }

    public double getTwoFGP() {
        return Double.parseDouble(twoFGP);
    }

    public float getEFGP() {
        return Float.parseFloat(eFGP);
    }

    public float getFreeThrowP() {
        return Float.parseFloat(freeThrowP);
    }

    public double getMinutesPlayed() {
        return playerStats.minutesPlayed;
    }

    public double getFGM() {
        return playerStats.FGM;
    }

    public double getFGA() {
        return playerStats.FGA;
    }

    public double getThreeFGM() {
        return playerStats.threeFGM;
    }

    public double getThreeFGA() {
        return playerStats.threeFGA;
    }

    public double getTwoFGM() {
        return playerStats.twoFGM;
    }

    public double getTwoFGA() {
        return playerStats.twoFGA;
    }

    public double getFreeThrowsM() {
        return playerStats.freeThrowsM;
    }

    public double getFreeThrowsA() {
        return playerStats.freeThrowsA;
    }

    public double getDefRB() {
        return playerStats.getDefRB();
    }

    public double getOffRB() {
        return playerStats.offRB;
    }

    public double getTotRB() {
        return playerStats.totRB;
    }

    public double getAssists() {
        return playerStats.assists;
    }

    public double getSteals() {
        return playerStats.steals;
    }

    public double getBlocks() {
        return playerStats.blocks;
    }

    public double getTurnOvers() {
        return playerStats.turnOvers;
    }

    public double getFouls() {
        return playerStats.fouls;
    }

    public double getPoints() {
        return playerStats.points;
    }

    public void setMP(double minutesPlayed) {
        playerStats.setMP(minutesPlayed);
    }

    public void setOldMP(double oldMinutesPlayed) {
        playerStats.setOldMP(oldMinutesPlayed);
    }


    public void setBlocks(double blocks) {
        playerStats.setBlocks(blocks);
    }


    public void setPoints(double points) {
        playerStats.setPoints(points);
    }


    public void updateStats() {
        double oldMinutesPlayed = playerStats.getOldMinutesPlayed();
        double minutesPlayed = playerStats.minutesPlayed;

        playerStats.setFGM(Math.round((minutesPlayed * playerStats.getFGM() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setFGA(Math.round((minutesPlayed * playerStats.getFGA() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setThreeFGM(Math.round((minutesPlayed * playerStats.getThreeFGM() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setThreeFGA(Math.round((minutesPlayed * playerStats.getThreeFGA() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setTwoFGM(Math.round((minutesPlayed * playerStats.getTwoFGM() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setTwoFGA(Math.round((minutesPlayed * playerStats.getTwoFGA() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setFTM(Math.round((minutesPlayed * playerStats.getFreeThrowsM() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setFTA(Math.round((minutesPlayed * playerStats.getFreeThrowsA() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setOffRB(Math.round((minutesPlayed * playerStats.getOffRB() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setDefRB(Math.round((minutesPlayed * playerStats.getDefRB() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setTotRB(Math.round((minutesPlayed * playerStats.getTotRB() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setAssists(Math.round((minutesPlayed * playerStats.getAssists() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setSteals(Math.round((minutesPlayed * playerStats.getSteals() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setBlocks(Math.round((minutesPlayed * playerStats.getBlocks() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setTOs(Math.round((minutesPlayed * playerStats.getTurnOvers() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setFouls(Math.round((minutesPlayed * playerStats.getFouls() / oldMinutesPlayed) * 10.0) / 10.0);
        playerStats.setPoints(Math.round((minutesPlayed * playerStats.getPoints() / oldMinutesPlayed) * 10.0) / 10.0);

    }
}
