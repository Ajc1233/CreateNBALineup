package com.lineupcreator.demo;

import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

import java.util.concurrent.*;


/**
 *
 * @author alecc
 */
public class OpeningSceneController implements Initializable {

    //Class to hold the player array, so it can be sent to different controller files
    PlayerArrayHolder playerArray;
    Player[] retrievePlayers;
    String yearSelected;
    @FXML
    private Button loadFileBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File checkForDirectory = new File("Lineups");
        if (!checkForDirectory.exists())
            checkForDirectory.mkdirs();

    }


    //Button will load the next scene
    @FXML
    public void switchToLineUpBtn(ActionEvent event) throws IOException, InterruptedException {
        boolean checkForSelection;
        //Make sure the user selected a year to pull players from
        checkForSelection = getUserToSelectYear();
        if(!checkForSelection)
            return;
        //Get event source and cast it to node to create the node that is used in the stage below
        Node node = (Node) event.getSource();

        //Gets the current stage to close it
        Stage stage = (Stage) node.getScene().getWindow();

        //Closes the current stage
        stage.close();

        try {
            //Gets the playerArray class and passes the Player array to it
            playerArray = PlayerArrayHolder.getInstance();
            playerArray.setPlayers(PlayerClassCreator());

            //Pass the playerArray object to the stage, so it can be used in the next controller
            stage.setUserData(playerArray);
            //Load the fxml file that will be used next
            //Parent root = FXMLLoader.load(getClass().getResource("ShowCompletedLineup.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListOfAllPlayers.fxml")));

            //Create the scene and set the stage to the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }

    }

    private boolean getUserToSelectYear() throws InterruptedException, IOException {
        String [] findYear;

        ChoiceDialog<Integer> selectYear = new ChoiceDialog<>();
        selectYear.setContentText("Please select the year you would like to select from.");

        Date d = new Date();
        String getYear = d.toString();
        findYear = getYear.split(" ");

        String currentYear = findYear[5];
        int year = 1952;

        while (year != Integer.parseInt(currentYear) + 1) {
            selectYear.getItems().add(year);
            year++;
        }
        selectYear.showAndWait();
        String userSelectedYear;
        if(selectYear.getSelectedItem() == null)
            return false;
        userSelectedYear = selectYear.getSelectedItem().toString();
        yearSelected = userSelectedYear;
        buildPlayersFile(userSelectedYear);

        return true;
    }

    private void buildPlayersFile(String userSelectedYear) throws InterruptedException, IOException {
        //Python script was condensed into an executable
        //The script creates the players text file
        new ProcessBuilder("main.exe", String.valueOf(userSelectedYear)).start();
        TimeUnit.SECONDS.sleep(4);
    }


    public Player[] PlayerClassCreator() throws IOException {

        Player[] player;
        PlayerBaseLineStats[] pBLS;
        //Opens the file that main.exe created
        File file = new File("players.txt");
        Scanner getFileSize = new Scanner(file);
        int fileSize = 0;
        while(getFileSize.hasNext()) {
            fileSize++;
            getFileSize.nextLine();
        }
        getFileSize.close();

        Scanner inputFile = new Scanner(file);
        String line = inputFile.nextLine();
        //Create a string array that will all the program to go through each
        //stat for each player
        String[] tokenized;

        //Create the player object array to hold al  the players 'static' stats
        player = new Player[fileSize];
        //Create the playerStats object array to hold all the players stats
        //that will be changed when the user sets the players minutes per game
        PlayerStats[] playerStats = new PlayerStats[fileSize];
        pBLS = new PlayerBaseLineStats[fileSize];

        int statToAdd;
        int index = 0;
        //While loop to go through the string array
        while (true) {
            statToAdd = 0;
            //Split each stat by the new tab escape sequence. In main.exe each
            //stat was separated by the new tab character.
            tokenized = line.split("\t");
            //Set the player and PlayerStats objects index to index. This was as index
            //increments, the stats will be added to the correct player
            player[index] = new Player();
            playerStats[index] = new PlayerStats();
            pBLS[index] = new PlayerBaseLineStats();
            //Go through each stat in the String array
            for (String s : tokenized) {
                //Call the statSwitchCase function so that each stat can be added
                //to their respective class. Pass the current player
                //and playerStats objects
                statSwitchCase(s, statToAdd, player[index], playerStats[index], pBLS[index]);
                //The statToAdd variable is used only for the function above
                statToAdd++;
            }

            //Set the playerStats class to the player class, so the player object
            //has access to all the players stats
            player[index].setPlayerStatClass(playerStats[index]);
            pBLS[index].setPlayer(player[index]);
            playerStats[index].setBaseLine(pBLS[index]);

            if(index == fileSize-1)
                break;

            //Move the scanner object to the next line of the file
            line = inputFile.nextLine();
            index++;

        }
        inputFile.close();
        return player;
    }


    /**
     * This function calls the respective method of the respective class. This
     * adds the stats to the correct variable within the classes.
     *
     * @param s This is the stat that will be used in the method call
     * @param statToAdd This is the switch expression
     * @param player This is player object to which the stat will be added
     * @param playerStats This is playerStats object to which the stat will be
     * @param pBLS Sets the players baseline stats. Used in same places at the
     * playerStats object added
     */
    public void statSwitchCase(String s, int statToAdd, Player player,
                               PlayerStats playerStats, PlayerBaseLineStats pBLS) {
        switch (statToAdd) {
            case 0 -> player.setName(s);
            case 1 -> player.setPosition(s);
            case 2 -> player.setAge(s);
            case 3 -> player.setTeam(s);
            case 4 -> player.setGamesP(s);
            case 5 -> player.setGamesS(s);
            case 6 -> {
                s = checkStringForEmpty(s);
                pBLS.setMinutesPlayed(Double.parseDouble(s));
                playerStats.setMP(Double.parseDouble(s));
            }
            case 7 -> {
                s = checkStringForEmpty(s);
                pBLS.setFGM(Double.parseDouble(s));
                playerStats.setFGM(Double.parseDouble(s));
            }
            case 8 -> {
                s = checkStringForEmpty(s);
                pBLS.setFGA(Double.parseDouble(s));
                playerStats.setFGA(Double.parseDouble(s));
            }
            case 9 -> player.setFGP(s);
            case 10 -> {
                s = checkStringForEmpty(s);
                pBLS.setThreeFGM(Double.parseDouble(s));
                playerStats.setThreeFGM(Double.parseDouble(s));
            }
            case 11 -> {
                s = checkStringForEmpty(s);
                pBLS.setThreeFGA(Double.parseDouble(s));
                playerStats.setThreeFGA(Double.parseDouble(s));
            }
            case 12 -> player.setThreeFGP(s);
            case 13 -> {
                s = checkStringForEmpty(s);
                pBLS.setTwoFGM(Double.parseDouble(s));
                playerStats.setTwoFGM(Double.parseDouble(s));
            }
            case 14 -> {
                s = checkStringForEmpty(s);
                pBLS.setTwoFGA(Double.parseDouble(s));
                playerStats.setTwoFGA(Double.parseDouble(s));
            }
            case 15 -> player.setTwoFGP(s);
            case 16 -> player.setEFGP(s);
            case 17 -> {
                s = checkStringForEmpty(s);
                pBLS.setFreeThrowsM(Double.parseDouble(s));
                playerStats.setFTM(Double.parseDouble(s));
            }
            case 18 -> {
                s = checkStringForEmpty(s);
                pBLS.setFreeThrowsA(Double.parseDouble(s));
                playerStats.setFTA(Double.parseDouble(s));
            }
            case 19 -> player.setFreeThrowP(s);
            case 20 -> {
                s = checkStringForEmpty(s);
                pBLS.setOffRB(Double.parseDouble(s));
                playerStats.setOffRB(Double.parseDouble(s));
            }
            case 21 -> {
                s = checkStringForEmpty(s);
                pBLS.setDefRB(Double.parseDouble(s));
                playerStats.setDefRB(Double.parseDouble(s));
            }
            case 22 -> {
                s = checkStringForEmpty(s);
                pBLS.setTotRB(Double.parseDouble(s));
                playerStats.setTotRB(Double.parseDouble(s));
            }
            case 23 -> {
                s = checkStringForEmpty(s);
                pBLS.setAssists(Double.parseDouble(s));
                playerStats.setAssists(Double.parseDouble(s));
            }
            case 24 -> {
                s = checkStringForEmpty(s);
                pBLS.setSteals(Double.parseDouble(s));
                playerStats.setSteals(Double.parseDouble(s));
            }
            case 25 -> {
                s = checkStringForEmpty(s);
                pBLS.setBlocks(Double.parseDouble(s));
                playerStats.setBlocks(Double.parseDouble(s));
            }
            case 26 -> {
                s = checkStringForEmpty(s);
                pBLS.setTurnOvers(Double.parseDouble(s));
                playerStats.setTOs(Double.parseDouble(s));
            }
            case 27 -> {
                s = checkStringForEmpty(s);
                pBLS.setFouls(Double.parseDouble(s));
                playerStats.setFouls(Double.parseDouble(s));
            }
            case 28 -> {
                s = checkStringForEmpty(s);
                pBLS.setPoints(Double.parseDouble(s));
                playerStats.setPoints(Double.parseDouble(s));
            }
        }
    }

    private String checkStringForEmpty(String s) {
        if(s.equals("")) {
            s = "0.0";
            return s;
        }
        return s;
    }

    @FXML
    private void LoadDeleteUserFile(ActionEvent event) throws IOException, ClassNotFoundException {

        ChoiceDialog<String> loadOrDelete = new ChoiceDialog<>();
        loadOrDelete.setContentText("Would you like to load a lineup or delete one?");
        loadOrDelete.getItems().addAll("Load", "Delete");
        loadOrDelete.showAndWait();

        if(loadOrDelete.getSelectedItem() == null)
            return;

        String userSelection = loadOrDelete.getSelectedItem();
        if(userSelection.equals("Load"))
            LoadUserFile(event);
        else if (userSelection.equals("Delete"))
            DeleteUserFile();
    }

    private void DeleteUserFile() throws IOException {
        String fileSelected;
        String deleteAlertFile;




        //Get the text file of the saved lineup names
        File getSavedFileNames = new File("Lineups\\savedLineupNames.txt");
        //If the file does not exist, then create the text file to hold lineup names
        if(!getSavedFileNames.exists())
            getSavedFileNames.createNewFile();

        //Go through the names in the text file
        Scanner getFileNames = new Scanner(getSavedFileNames);
        ChoiceDialog<String> selectFileToLoad = new ChoiceDialog<>();
        //If there are no names in the text file then let the user know and return
        if(!getFileNames.hasNext()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There are no files currently available to load." +
                    "\nPlease create and save a lineup before attempting to find a lineup." );
            alert.showAndWait();
            return;
        }
        String [] removeFilePath;

        while (getFileNames.hasNext()) {
            //Get the name of the file and add it to the choice dialog box
            removeFilePath = getFileNames.nextLine().split("\\\\");

            selectFileToLoad.getItems().add(removeFilePath[1]);
        }
        getFileNames.close();
        selectFileToLoad.showAndWait();
        try {
            fileSelected = selectFileToLoad.getSelectedItem();
        } catch (NullPointerException nullP) {
            return;
        }
        deleteAlertFile = fileSelected;
        //Add the path and the file extension after the user makes their choice
        String deleteDatFile = "Lineups\\" + fileSelected + ".dat";
        //Get the lineup info from the dat file

        //Delete the dat file that the user chose
        File fileToDelete = new File(deleteDatFile);
        fileToDelete.delete();


        //Make a string of path and file name to be deleted
        String deleteFileName = "Lineups\\" + fileSelected;
        Scanner removeFileName = new Scanner(getSavedFileNames);
        String matchFileToDelete;
        FileWriter fileWriter = new FileWriter("Lineups\\tempTxt.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        while (removeFileName.hasNext()) {
            //Get the name of the file and add it to the choice dialog box
            matchFileToDelete = removeFileName.nextLine();

            //Skip adding the filename to the temp text file if it is the one the user chose to delete
            if(matchFileToDelete.equals(deleteFileName))
                continue;

            printWriter.println(matchFileToDelete);
        }
        printWriter.close();
        fileWriter.close();
        removeFileName.close();
        getFileNames.close();
        //Delete the original lineup text file
        while(getSavedFileNames.exists())
            getSavedFileNames.delete();


        //Create the saved lineup text file
        File recreateFileNameTxt = new File("Lineups\\savedLineupNames.txt");
        File tempTxtFile = new File("Lineups\\tempTxt.txt");
        //Rename the temp text file to savedLineUpNames.txt, so it can be used later
        tempTxtFile.renameTo(recreateFileNameTxt);

        Alert deleteComplete = new Alert(Alert.AlertType.INFORMATION);
        deleteComplete.setHeaderText("File '" +  deleteAlertFile + "' successfully deleted.");
        deleteComplete.showAndWait();
    }

    private void LoadUserFile(ActionEvent event) throws IOException, ClassNotFoundException {
        String fileSelected;

        //Get the text file of the saved lineup names
        File getSavedFileNames = new File("Lineups\\savedLineupNames.txt");
        //If the file does not exist, then create the text file to hold lineup names
        if(!getSavedFileNames.exists())
            getSavedFileNames.createNewFile();

        //Go through the names in the text file
        Scanner getFileNames = new Scanner(getSavedFileNames);
        ChoiceDialog<String> selectFileToLoad = new ChoiceDialog<>();
        //If there are no names in the text file then let the user know and return
        if(!getFileNames.hasNext()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("There are no files currently available to load." +
                    "\nPlease create and save a lineup before attempting to find a lineup." );
            alert.showAndWait();
            return;
        }
        String [] removeFilePath;

        while (getFileNames.hasNext()) {
            //Get the name of the file and add it to the choice dialog box
            removeFilePath = getFileNames.nextLine().split("\\\\");

            selectFileToLoad.getItems().add(removeFilePath[1]);
        }

        selectFileToLoad.showAndWait();
        try {
            fileSelected = selectFileToLoad.getSelectedItem();

        } catch (NullPointerException nullP) {
            return;
        }
        //Add the path and the file extension after the user makes their choice
        fileSelected = "Lineups\\" + fileSelected + ".dat";
        //Get the lineup info from the dat file
        FileInputStream inStream = new FileInputStream(fileSelected);
        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
        retrievePlayers = (Player[]) objectInputFile.readObject();
        objectInputFile.close();


        ChoiceDialog<String> updateLineup = new ChoiceDialog<>();
        updateLineup.setContentText("Would you like to edit the positions and minutes "
                + "of your lineup?");
        updateLineup.getItems().addAll("Yes", "No");
        updateLineup.showAndWait();
        if(updateLineup.getSelectedItem() == null)
            return;
        String userChoice;
        userChoice = updateLineup.getSelectedItem();

        if (userChoice.equals("Yes")) {
            goToEditLineup(event, 0);
        } else {
            goToEditLineup(event, 1);
        }
    }

    public void goToEditLineup(ActionEvent event, int whereToGo) {
        //Create the node that is used in the stage below
        Node node = (Node) event.getSource();
        //Gets the current stage to close it
        Stage stage = (Stage) node.getScene().getWindow();
        //Closes the current stage
        stage.close();

        try {
            //Gets the playerArray class and passes the Player array to it
            playerArray = PlayerArrayHolder.getInstance();
            playerArray.setPlayers(retrievePlayers);

            //Pass the playerArray object to the stage, so it can be used in the next controller
            stage.setUserData(playerArray);
            //Load the fxml file that will be used next.
            Parent root;
            if (whereToGo == 0) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AssignMinAndPosition.fxml")));
            } else {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowCompletedLineup.fxml")));
            }

            //Create the scene and set the stage to the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

}