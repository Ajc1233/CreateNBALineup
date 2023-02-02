package com.lineupcreator.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author alecc
 */
public class ShowCompletedLineup implements Initializable {
    //Player [] playa = getPlayerArray();

    Player[] player;
    PlayerArrayHolder playerArray;

    private Scene scene;
    private Parent root;

    @FXML
    private TextField startingPGTF;
    @FXML
    private TextField startingSGTF;
    @FXML
    private TextField startingSFTF;
    @FXML
    private TextField startingPFTF;
    @FXML
    private TextField startingCTF;
    @FXML
    private TextField bench1TF;
    @FXML
    private TextField bench2TF;
    @FXML
    private TextField bench3TF;
    @FXML
    private TextField bench4TF;
    @FXML
    private TextField bench5TF;
    @FXML
    private TextField bench6TF;
    @FXML
    private TextField bench7TF;
    @FXML
    private TextField bench8TF;
    @FXML
    private TextField bench9TF;
    @FXML
    private TextField bench10TF;
    @FXML
    private Label teamLabelFGM;
    @FXML
    private Label teamLabelFGA;
    @FXML
    private Label teamLabelFGPercent;
    @FXML
    private Label teamLabelThreeMade;
    @FXML
    private Label teamLabelThreeAttempt;
    @FXML
    private Label teamLabelThreePercent;
    @FXML
    private Label teamLabelFTM;
    @FXML
    private Label teamLabelFTA;
    @FXML
    private Label teamLabelFTPercent;
    @FXML
    private Label teamLabelORB;
    @FXML
    private Label teamLabelDRB;
    @FXML
    private Label teamLabelTRB;
    @FXML
    private Label teamLabelAST;
    @FXML
    private Label teamLabelSTL;
    @FXML
    private Label teamLabelBLK;
    @FXML
    private Label teamLabelTOV;
    @FXML
    private Label teamLabelPF;
    @FXML
    private Label teamLabelPTS;
    @FXML
    private Label teamLabelTwoMade;
    @FXML
    private Label teamLabelTwoAttempt;
    @FXML
    private Label teamLabelTwoPercent;
    PlayerArrayHolder playerToSend;
    @FXML
    private Button saveFileBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button restartBtn;

    /**
     * Used to get the playerArray object so its contents can be used in this
     * controller
     *
     */
    public void switchToLineUpBtn() throws IOException {
        try {
            if (player == null) {
                //Get the playerArray class
                playerArray = PlayerArrayHolder.getInstance();
                //pLUH = PlayerLineUpHolder.getInstance();
                //set the Player object to the playerArray object
                player = playerArray.getPlayers();
            }
        } catch (Exception e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    /**
     * Change to the scene that shows the player's stats
     */
    @FXML
    public void switchToPlrStats(ActionEvent event) {
        //If the user did not click on a player box, then do not allow the user to change to the next scene
        if(playerToSend.getPlayer() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a player by clicking on the name box.");
            alert.showAndWait();
            return;
        }
        //Create the node that is used in the stage below
        Node node = (Node) event.getSource();

        //Gets the current stage to close it
        Stage stage = (Stage) node.getScene().getWindow();

        //Closes the current stage
        stage.close();

        try {

            //Pass the player object to the stage, so it can be used in the next controller
            stage.setUserData(playerToSend);
            //Load the fxml file that will be used next
            //Parent root = FXMLLoader.load(getClass().getResource("AssignMinAndPosition.fxml"));
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowPlayersStats.fxml")));

            //Create the scene and set the stage to the scene
            scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        playerToSend = PlayerArrayHolder.getInstance();

            try {
                //used to get the player class setup
                switchToLineUpBtn();
                //used to get the textfields setup
                setPlayerNamesInTF();
                getTeamTotalStats();
            } catch (IOException e) {
                System.err.printf("Error: %s%n", e.getMessage());
            }

    }

    Player[] newPlayerArray;

    /**
     * Used to set all the text fields to the selected player's name
     */
    public void setPlayerNamesInTF() {
        newPlayerArray = new Player[15];
        //Create a textField array that holds each textField
        TextField[] tfArray = {startingPGTF, startingSGTF, startingSFTF, startingPFTF,
                startingCTF, bench1TF, bench2TF, bench3TF, bench4TF, bench5TF, bench6TF,
                bench7TF, bench8TF, bench9TF, bench10TF};

        //Create an array of the player object's position that was set in the last scene
        String[] playerPositions = {player[0].getPositionSetByUser(),
                player[1].getPositionSetByUser(), player[2].getPositionSetByUser(),
                player[3].getPositionSetByUser(), player[4].getPositionSetByUser(),
                player[5].getPositionSetByUser(), player[6].getPositionSetByUser(),
                player[7].getPositionSetByUser(), player[8].getPositionSetByUser(),
                player[9].getPositionSetByUser(), player[10].getPositionSetByUser(),
                player[11].getPositionSetByUser(), player[12].getPositionSetByUser(),
                player[13].getPositionSetByUser(), player[14].getPositionSetByUser()};


        int playerIndex = 0, tfIndex = 0;
        String stringToCheck;
        String[] splitTextFieldString;
        //Go through each item in the string array and look for the position that
        //matches the minutes played field
        while (tfIndex != 15) {
            //Will start with 'startingPGTF'
            splitTextFieldString = tfArray[tfIndex].toString().split("[=,]");
            //Adding 'TF' to the player's position will eventually get you to startingPG + 'TF'
            stringToCheck = playerPositions[playerIndex] + "TF";
            if (splitTextFieldString[1].equals(stringToCheck)) {
                tfArray[tfIndex].setText(player[playerIndex].getName());
                newPlayerArray[tfIndex] = new Player();
                //Used to align the lineup with the index... StartingPG is now
                //player[0] and bench10 is now player[14]
                newPlayerArray[tfIndex] = player[playerIndex];
                tfIndex++;
                playerIndex = 0;
            } else {
                playerIndex++;
            }
        }

    }

    /**
     * This method will add together all the players stats who have minutes
     * per game. The team stats will be posted on the lineup
     */
    public void getTeamTotalStats() {
        double teamFGM = 0, teamFGA = 0, teamThreeMade = 0,
                teamThreeAttempt = 0, teamTwoMade = 0,
                teamTwoAttempt = 0, teamFTM = 0,
                teamFTA = 0, teamORB = 0, teamDRB = 0,
                teamTRB = 0, teamAST = 0, teamSTL = 0, teamBLK = 0,
                teamTOV = 0, teamPF = 0, teamPTS = 0;

        for (Player p : player) {
            teamFGM += p.getFGM();
            teamFGA += p.getFGA();
            teamThreeMade += p.getThreeFGM();
            teamThreeAttempt += p.getThreeFGA();
            teamTwoMade += p.getTwoFGM();
            teamTwoAttempt += p.getTwoFGA();
            teamFTM += p.getFreeThrowsM();
            teamFTA += p.getFreeThrowsA();
            teamORB += p.getOffRB();
            teamDRB += p.getDefRB();
            teamTRB += p.getTotRB();
            teamAST += p.getAssists();
            teamSTL += p.getSteals();
            teamBLK += p.getBlocks();
            teamTOV += p.getTurnOvers();
            teamPF += p.getFouls();
            teamPTS += p.getPoints();
        }

        double teamfieldGoalPercent = teamFGM / teamFGA;
        double teamThreePointPercent = teamThreeMade / teamThreeAttempt;
        double teamTwoPointPercent = teamTwoMade / teamTwoAttempt;
        double teamFreeThrowPercent = teamFTM / teamFTA;

        teamLabelFGM.setText(String.format("FGM: %.2f", teamFGM));
        teamLabelFGA.setText(String.format("FGA: %.2f", teamFGA));
        teamLabelFGPercent.setText(String.format("FG%%: %.2f", teamfieldGoalPercent));
        teamLabelThreeMade.setText(String.format("3PM: %.2f", teamThreeMade));
        teamLabelThreeAttempt.setText(String.format("3PA: %.2f", teamThreeAttempt));

        //Early eras of the NBA did not have a three point line, so this needed to be added in
        if(String.valueOf(teamThreePointPercent).equals("NaN"))
            teamLabelThreePercent.setText("3P%%: 0.00");
        else
            teamLabelThreePercent.setText(String.format("3P%%: %.2f", teamThreePointPercent));

        teamLabelTwoMade.setText(String.format("2PM: %.2f", teamTwoMade));
        teamLabelTwoAttempt.setText(String.format("2PA: %.2f", teamTwoAttempt));
        teamLabelTwoPercent.setText(String.format("2P%%: %.2f", teamTwoPointPercent));
        teamLabelFTM.setText(String.format("FTM: %.2f", teamFTM));
        teamLabelFTA.setText(String.format("FTA: %.2f", teamFTA));
        teamLabelFTPercent.setText(String.format("FT%%: %.2f", teamFreeThrowPercent));
        teamLabelORB.setText(String.format("ORB: %.2f", teamORB));
        teamLabelDRB.setText(String.format("DRB: %.2f", teamDRB));
        teamLabelTRB.setText(String.format("TRB: %.2f", teamTRB));
        teamLabelAST.setText(String.format("AST: %.2f", teamAST));
        teamLabelSTL.setText(String.format("STL: %.2f", teamSTL));
        teamLabelBLK.setText(String.format("BLK: %.2f", teamBLK));
        teamLabelTOV.setText(String.format("TOV: %.2f", teamTOV));
        teamLabelPF.setText(String.format("PF: %.2f", teamPF));
        teamLabelPTS.setText(String.format("Points: %.2f", teamPTS));
    }

    /**
     * If the user clicks on a box, set that player object to the playerToSend object, so that it can be sent to the
     * nest scene
     * @param event User clicked on box
     */
    @FXML
    private void showPGStats(MouseEvent event) {playerToSend.setPlayer(newPlayerArray[0]); }

    @FXML
    private void showSGStats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[1]);
    }

    @FXML
    private void showSFStats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[2]);
    }

    @FXML
    private void showPFStats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[3]);
    }

    @FXML
    private void showCStats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[4]);
    }

    @FXML
    private void showBench1Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[5]);
    }

    @FXML
    private void showBench2Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[6]);
    }

    @FXML
    private void showBench3Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[7]);
    }

    @FXML
    private void showBench4Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[8]);
    }

    @FXML
    private void showBench5Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[9]);
    }

    @FXML
    private void showBench6Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[10]);
    }

    @FXML
    private void showBench7Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[11]);
    }

    @FXML
    private void showBench8Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[12]);
    }

    @FXML
    private void showBench9Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[13]);
    }

    @FXML
    private void showBench10Stats(MouseEvent event) {
        playerToSend.setPlayer(newPlayerArray[14]);
    }

    /**
     * Allow the user to save their lineup to be used in later programs
     *
     */
    @FXML
    private void saveToAFile(ActionEvent event) throws IOException {

        String newFileName;
        TextInputDialog inputFileName = new TextInputDialog();
        ChoiceDialog<String> overWriteFile = new ChoiceDialog<>();
        Alert fileNotSaved = new Alert(Alert.AlertType.INFORMATION);
        fileNotSaved.setContentText("The file was not saved.");

        String savedFileName;

            //Get the name of the lineup to save

            inputFileName.setContentText("Enter the file name");
            inputFileName.showAndWait();

            newFileName = inputFileName.getResult();
            savedFileName = newFileName;
            newFileName = "Lineups\\" + newFileName;
            String shouldFileBeSaved;
            //Convert the users input to a .dat file and check to see if it exists
            File checkForFileName = new File(newFileName + ".dat");
            if (checkForFileName.exists()) {
                overWriteFile.setContentText("Would you like to overwrite your save?");
                overWriteFile.getItems().addAll("Yes", "No");
                overWriteFile.showAndWait();
                shouldFileBeSaved = overWriteFile.getSelectedItem();
                //Ask the user if they want to save over their previous lineup
                if (shouldFileBeSaved.equals("Yes")){
                    //Delete current file
                    checkForFileName.delete();
                    //Call method to save the new file
                    saveTheFile(newFileName, savedFileName, 1);
                    return;
                }
            } else {
                saveTheFile(newFileName, savedFileName, 2);
                return;
            }
            fileNotSaved.showAndWait();



    }

    private void saveTheFile(String newFileName, String savedFileName, int saveFileNameToTextFile) throws IOException {
        Alert savedComplete = new Alert(Alert.AlertType.INFORMATION);
        //Save the name of the file to a txt file to be used later
        if(saveFileNameToTextFile == 2) {
            FileWriter fwriter = new FileWriter("Lineups\\savedLineupNames.txt", true);
            PrintWriter pwriter = new PrintWriter(fwriter);
            pwriter.println(newFileName);
            pwriter.close();
        }
        //Create the name of the new file that will be used to save the
        //current lineup
        newFileName += ".dat";
        FileOutputStream outStream = new FileOutputStream(newFileName);
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);
        //write the player array to the file
        objectOutputFile.writeObject(player);
        objectOutputFile.close();
        savedComplete.setHeaderText("File '" + savedFileName + "' successfully saved.");
        savedComplete.showAndWait();
    }

    /**
     * Allow the user to close the program
     */
    @FXML
    private void exitProgram(ActionEvent event) {
        ChoiceDialog<String> exitProgram = new ChoiceDialog<>();
        Alert willNotExit = new Alert(Alert.AlertType.CONFIRMATION);

        exitProgram.setContentText("Would you like to exit the program?");
        exitProgram.getItems().addAll("Yes", "No");
        exitProgram.showAndWait();
        if(exitProgram.getSelectedItem() == null) {
            willNotExit.setContentText("The program will not close.");
            willNotExit.showAndWait();
            return;
        }
        String answer = exitProgram.getSelectedItem();

        if (answer.equals("Yes")) {
            System.exit(0);
        } else {
            willNotExit.setContentText("The program will not close.");
            willNotExit.showAndWait();
        }

    }

    /**
     * Allow the user to reset the program
     */
    @FXML
    private void restartProgram(ActionEvent event) {
        ChoiceDialog<String> resetProgram = new ChoiceDialog<>();
        Alert willNotReset = new Alert(Alert.AlertType.CONFIRMATION);

        resetProgram.setContentText("Would you like to restart the program?");
        resetProgram.getItems().addAll("Yes", "No");
        resetProgram.showAndWait();
        if(resetProgram.getSelectedItem() == null) {
            willNotReset.setContentText("The program will not restart.");
            willNotReset.showAndWait();
            return;
        }
        String answer = resetProgram.getSelectedItem();

        if (answer.equals("Yes")) {
            //Create the node that is used in the stage below
            Node node = (Node) event.getSource();

            //Gets the current stage to close it
            Stage stage = (Stage) node.getScene().getWindow();    //((Node) event.getSource()).getScene().getWindow();

            //Closes the current stage
            stage.close();

            try {
                //Load the fxml file that will be used next
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OpeningScene.fxml")));
                //Create the scene and set the stage to the scene
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                System.err.printf("Error: %s%n", e.getMessage());
            }

        } else {
            willNotReset.setContentText("The program will not restart.");
            willNotReset.showAndWait();
        }

    }
}

