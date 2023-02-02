package com.lineupcreator.demo;

import java.io.IOException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alecc
 */
public class AssignMinAndPositionController implements Initializable {

    PlayerArrayHolder playerArray;
    Player[] player;
    PlayerLineUpHolder pLUH;


    @FXML
    private ListView<String> obListOfSelectedPlayers;
    @FXML
    private TextField startingPGTF;
    @FXML
    private Button setUnSetPG;
    @FXML
    private TextField startingSGTF;
    @FXML
    private Button setUnSetSG;
    @FXML
    private TextField startingSFTF;
    @FXML
    private Button setUnSetSF;
    @FXML
    private TextField startingPFTF;
    @FXML
    private Button setUnSetPF;
    @FXML
    private TextField startingCTF;
    @FXML
    private Button setUnSetC;
    @FXML
    private TextField bench1TF;
    @FXML
    private Button setUnSetBench1;
    @FXML
    private TextField bench2TF;
    @FXML
    private Button setUnSetBench2;
    @FXML
    private TextField bench3TF;
    @FXML
    private Button setUnSetBench3;
    @FXML
    private TextField bench4TF;
    @FXML
    private Button setUnSetBench4;
    @FXML
    private TextField bench5TF;
    @FXML
    private Button setUnSetBench5;
    @FXML
    private TextField bench6TF;
    @FXML
    private Button setUnSetBench6;
    @FXML
    private TextField bench7TF;
    @FXML
    private Button setUnSetBench7;
    @FXML
    private TextField bench8TF;
    @FXML
    private Button setUnSetBench8;
    @FXML
    private TextField bench9TF;
    @FXML
    private Button setUnSetBench9;
    @FXML
    private TextField bench10TF;
    @FXML
    private Button setUnSetBench10;
    @FXML
    private TextField startingPGMP;
    private double prevStartingPGMP;
    @FXML
    private TextField startingSGMP;
    private double prevStartingSGMP;
    @FXML
    private TextField startingSFMP;
    private double prevStartingSFMP;
    @FXML
    private TextField startingPFMP;
    private double prevStartingPFMP;
    @FXML
    private TextField startingCMP;
    private double prevStartingCMP;
    @FXML
    private TextField bench1MP;
    private double prevBench1MP;
    @FXML
    private TextField bench2MP;
    private double prevBench2MP;
    @FXML
    private TextField bench3MP;
    private double prevBench3MP;
    @FXML
    private TextField bench4MP;
    private double prevBench4MP;
    @FXML
    private TextField bench5MP;
    private double prevBench5MP;
    @FXML
    private TextField bench6MP;
    private double prevBench6MP;
    @FXML
    private TextField bench7MP;
    private double prevBench7MP;
    @FXML
    private TextField bench8MP;
    private double prevBench8MP;
    @FXML
    private TextField bench9MP;
    private double prevBench9MP;
    @FXML
    private TextField bench10MP;
    private double prevBench10MP;
    @FXML
    private Button changeStatsBtn;
    @FXML
    private TextField totalTeamMinutes;
    Alert alert;
    @FXML
    private Button setPGMP;
    @FXML
    private Button setSGMP;
    @FXML
    private Button setSFMP;
    @FXML
    private Button setPFMP;
    @FXML
    private Button setCMP;
    @FXML
    private Button setBench1MP;
    @FXML
    private Button setBench2MP;
    @FXML
    private Button setBench3MP;
    @FXML
    private Button setBench4MP;
    @FXML
    private Button setBench5MP;
    @FXML
    private Button setBench6MP;
    @FXML
    private Button setBench7MP;
    @FXML
    private Button setBench8MP;
    @FXML
    private Button setBench9MP;
    @FXML
    private Button setBench10MP;


    @FXML
    private Button swithcbtn;




    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Get the playerArray object from the OpeningController file
        playerArray = PlayerArrayHolder.getInstance();
        //Set the Player object to the playerArray, so it can be used in the file
        player = playerArray.getPlayers();
        //Create the array used to hold the selected players from the tableview
        pLUH = new PlayerLineUpHolder();
        //Call the method to build the tableview

        if (player[0].getPositionSetByUser() != null)
            recreateLineup();
        else
            buildListView();
    }

    /**
     * This method will add the player to the selected textField
     *
     * @param event Clicked button event
     */
    @FXML
    private void addPlayerToTextField(ActionEvent event) {

        //Get the selected player in the listview
        String selectedPlayer = obListOfSelectedPlayers.getSelectionModel().getSelectedItem();


        //Get the event source and set it to a string
        String buttonSelected = event.getSource().toString();

        //Split the event string to pull out the buttonID
        String[] getID = buttonSelected.split("[=,]");
        //Set the buttonID to the sring btn
        String btn = getID[1];

        //Call the buttonFinder method and send the btn string as the argument
        TextField fieldToPopulate = findTFByButtonAndReturnTF(btn);
        String [] getTF = fieldToPopulate.toString().split("[=,]");
        Button buttonToChange = useTFStringToReturnButtonObject(getTF[1]);
        //if the textfield has not been set yet, populate it with the player
        if (fieldToPopulate.getText().equals("")) {
            //Make sure the user selected a player
            if (selectedPlayer == null) {
                //Calls the method that handles the alert popups
                alertHandler(1);
                return;
            }
            //use the returned TextField to populate the selected textfield with
            //the selected player
            fieldToPopulate.setText(selectedPlayer);
            associatePlayerNameWtihPlayerObject(selectedPlayer, btn);
            //Remove the player from the ListView
            obListOfSelectedPlayers.getItems().remove(selectedPlayer);
            buttonToChange.setText("Deselect");
            //If the textfield has a player's name already set, then allow
            //the user to deselect that player and add them back into the listView
        } else {
            String addToListView = fieldToPopulate.getText();
            fieldToPopulate.setText("");
            obListOfSelectedPlayers.getItems().add(0, addToListView);
            buttonToChange.setText("Select");

        }

    }


    /**
     * When entering minutes for the player, update their stats based on the minutes played
     * @param newMinutesPlayed User selected minutes
     * @param player Player object to update
     */
    public void changePStatsByMPG(double newMinutesPlayed, Player player) {
        player.playerStats.setPlayerStatsToBaseLine();
        player.setOldMP(player.getMinutesPlayed());
        player.setMP(newMinutesPlayed);
        player.updateStats();
    }

    /**
     * The user will enter the minutes that the player will play for their team
     * @param event Clicked button event
     */
    @FXML
    public void addUserMinutesToMPField(ActionEvent event) {
        //Get the event source and set it to a string
        String mpFieldForWhichPlayer = event.getSource().toString();
        //Split the event string to pull out the buttonID
        String[] getID = mpFieldForWhichPlayer.split("[=,]");
        //Set the buttonID to the string btn

        String getPlayerField = getID[1];
        //Once the user hits the enter button run the code below

        //Get the field to reset the minutes
        TextField resetFieldsMinutes = useMPStringToReturnMPObject(getPlayerField);
        //Check to see if the user entered a player for that textfield
        //Run the if statement if they did not
        if (!checkForPlayerInTF(getPlayerField)) {
            changePlayerMPFieldAndTotalTime(getPlayerField, resetFieldsMinutes, "Error");
            return;
        }
        changePlayerMPFieldAndTotalTime(getPlayerField, resetFieldsMinutes, "");
    }


    /**
     * Controls the players MP textField and the totalTime
     *
     * @param getPlayerField Used to find the correct player textField for that
     * MP textField
     * @param MPTextField This is the MP textField object
     * @param isError Used for specific calls to this method where there is an
     * error.
     */
    public void changePlayerMPFieldAndTotalTime(String getPlayerField, TextField MPTextField, String isError) {
        double totalMinutes, subtractMP, addMinutesBack;
        String[] splitMPTextField;
        splitMPTextField = MPTextField.toString().split("[=,]");


        //If the string passed in equals Error, reset the textField
        if (isError.equals("Error")) {
            MPTextField.setText("");
            return;
        }

        //If the user entry is blank or a space, set the Minutes Played field to 0
        if (MPTextField.getText().equals("") || MPTextField.getText().equals(" ")) {
            if (MPTextField.getOpacity() == 0.99) {
                addMinutesBack = returnMPTFPreviousNumber(getPlayerField);
                double convertedTime = reconvertTimeToMinutes(addMinutesBack);

                //totalMinutes = Double.parseDouble(totalTeamMinutes.getText());
                convertedTime += addMinutesBack;
                totalTeamMinutes.setText(String.valueOf(convertedTime));
                setMPTFPreviousNumber(getPlayerField, 0.0);
                MPTextField.setText("");
                return;
            }
            MPTextField.setText("");
        }
        //If this is the first entry in the MP field, run this if statement
        if (MPTextField.getOpacity() == 1.00) {
            totalMinutes = Double.parseDouble(totalTeamMinutes.getText());
            //If the user entry is anything but a number, the catch block will run
            try {
                subtractMP = Double.parseDouble(MPTextField.getText());
                subtractMP = convertTimeToMinutes(subtractMP);
                MPTextField.setText(String.valueOf(subtractMP));
            } catch (NumberFormatException badEntry) {
                //Recursive call to set the MP field to 0
                changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
                //Calls the alert method
                alertHandler(7);
                //set the player's previous minutes to 0.0
                setMPTFPreviousNumber(getPlayerField, 0.0);
                return;
            }
            //If try block does not have an error, subtract the userEntry from the total minutes
            totalMinutes -= subtractMP;
            totalMinutes = Math.round(totalMinutes * 100.0) / 100.0;
            //Check the totalMinutes field to assure it is not below 0
            if (checkUserEntryInMPField(subtractMP, totalMinutes, getPlayerField, MPTextField)) {
                return;
            }
            totalMinutes = convertTimeToMinutes(totalMinutes);
            //Set the new total team minutes
            totalTeamMinutes.setText(String.valueOf(totalMinutes));
            //Change the opacity to have a marker that the mp field has had at least one entry
            MPTextField.setOpacity(0.99);
            Player transportPlayerObject = getPlayerObjectPosition(splitMPTextField[1]);
            changePStatsByMPG(subtractMP, transportPlayerObject);
            //Set the user entered number to that textField's MP field, so that
            //it can be used later if the user decides to change the player's minutes
            setMPTFPreviousNumber(getPlayerField, subtractMP);

        } //Run this statement if the MP field has been edited at least once
        else if (MPTextField.getOpacity() == 0.99) {

            addMinutesBack = returnMPTFPreviousNumber(getPlayerField);
            totalMinutes = Double.parseDouble(totalTeamMinutes.getText());
            totalMinutes += addMinutesBack;
            try {
                subtractMP = Double.parseDouble(MPTextField.getText());
                subtractMP = convertTimeToMinutes(subtractMP);
                MPTextField.setText(String.valueOf(subtractMP));
            } catch (NumberFormatException badEntry) {
                changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
                alertHandler(7);
                //Set the total team minutes here so that the previous user entry
                //is wiped away.
                totalTeamMinutes.setText(String.valueOf(totalMinutes));
                setMPTFPreviousNumber(getPlayerField, 0.0);
                return;
            }
            totalMinutes -= subtractMP;
            if (checkUserEntryInMPField(subtractMP, totalMinutes, getPlayerField, MPTextField)) {
                return;
            }
            totalMinutes = Math.round(totalMinutes * 100.0) / 100.0;
            totalMinutes = convertTimeToMinutes(totalMinutes);
            totalTeamMinutes.setText(String.valueOf(totalMinutes));
            Player transportPlayerObject = getPlayerObjectPosition(splitMPTextField[1]);
            changePStatsByMPG(subtractMP, transportPlayerObject);
            setMPTFPreviousNumber(getPlayerField, subtractMP);
        }

    }

    /**
     * Checks the MP Field's player field to assure a player was selected before
     * minutes were assigned
     *
     * @param isPlayerFieldPopulated this is the player's MP field
     * @return returns false if the player's name is not set
     */
    public boolean checkForPlayerInTF(String isPlayerFieldPopulated) {
        //Returns the players textField object
        TextField fieldToCheck = (useMPTFToREturnPlayerNameTF(isPlayerFieldPopulated));
        //Checks that the players field has been populated
        if (fieldToCheck.getText().equals("")) {
            alertHandler(2);
            return false;
        }
        return true;
    }

    /**
     * Check the user Minutes played entry to assure it is within the correct
     * range
     *
     * @param userMinutes User entered minutes
     * @param totalMinutes The teams total minutes
     * @param getPlayerField Used in the call to changePlayerMPFieldAndTotalTime
     * @param MPTextField Used in the call to changePlayerMPFieldAndTotalTime
     * @return return true if there is an issue, false otherwise
     */
    public boolean checkUserEntryInMPField(double userMinutes, double totalMinutes, String getPlayerField, TextField MPTextField) {

        String stringUserMinutes = String.valueOf(userMinutes);
        //If the entry is just a '.' then the entry is invaild
        if (stringUserMinutes.equals(".")) {
            alertHandler(3);
            changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
            return true;
        }
        if (totalMinutes < 0) {
            alertHandler(4);
            changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
            return true;
        }
        if (userMinutes > 48.0) {
            alertHandler(5);
            changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
            return true;
        } else if (userMinutes < 0.0) {
            alertHandler(6);
            changePlayerMPFieldAndTotalTime(getPlayerField, MPTextField, "Error");
            return true;
        }
        return false;
    }


    /**
     * Builds the list view that the user will select players from
     */
    public void buildListView() {
        //Build the listview with the player array
        for (Player value : player) {
            //Once the end of the array has been reached, break the loop
            if (value == null) {
                break;
            }
            obListOfSelectedPlayers.getItems().add(value.getName());
            //obListOfSelectedPlayersNames.getItems().add(player[i].getName());
        }
    }


    /**
     * Convert the total time to act like minutes and hours
     * @param timeToCheck How much time there is currently
     * @return The converted time
     */
    public double convertTimeToMinutes(double timeToCheck) {

        //String to hold the value of the double
        String splitUserTime = String.valueOf(timeToCheck);
        //split the double up by the .
        String[] checkUserTimeOver = splitUserTime.split("\\.");
        String getSecondsForStringBuilder = checkUserTimeOver[1];
        //Use stringBuilder to get the first number of the checkUserTimeOver[1]
        StringBuilder sb;
        sb = new StringBuilder(getSecondsForStringBuilder);
        //If the length is equal to 2, then delete the last number in the stringBuilder

        if (checkUserTimeOver[1].length() == 2) {
            sb.deleteCharAt(1);
            sb.append(0);
        }
        if (checkUserTimeOver[1].length() == 1) {
            sb.append(0);
        }
        double checksForNeededConversion = Double.parseDouble(sb.toString());
        //String used to piece back together the double that was taken apart
        String userTimePutTogetherString;
        //set the string to the double and return it
        double userTimePutTogether;
        //Take the first part of the tokenized and set it to the userMinutes variable
        int userMinutes = Integer.parseInt(checkUserTimeOver[0]);
        //Take the second part of the tokenized and set it to the userSecond variable
        int userSeconds = Integer.parseInt(checkUserTimeOver[1]);
        sb.deleteCharAt(1);
        int isolateFirstDigit = Integer.parseInt(sb.toString());

        int userSecondsSecondNumber = userSeconds % 10;

        if (checksForNeededConversion >= 60) {
            userMinutes++;
            isolateFirstDigit -= 6;
            if (checkUserTimeOver[1].length() == 1) {
                userTimePutTogetherString = userMinutes + "." + isolateFirstDigit + "0";
            } else {
                userTimePutTogetherString = userMinutes + "." + isolateFirstDigit + userSecondsSecondNumber;
            }

            userTimePutTogether = Double.parseDouble(userTimePutTogetherString);


            return userTimePutTogether;
        }
        return timeToCheck;

    }

    /**
     * Reconvert the total time if the user deletes a players minutes
     * @param lastTwoOfUserEntry last two digits of the total minutes
     * @return returns the converted time
     */
    public double reconvertTimeToMinutes(double lastTwoOfUserEntry) {
        //String to hold the value of the double
        String splitUserTime = String.valueOf(lastTwoOfUserEntry);
        String[] checkUserTimeOver = splitUserTime.split("\\.");
        String getUserSecondsForIntVariable = checkUserTimeOver[1];
        int lastTwoDigitsOfUserEntry = Integer.parseInt(getUserSecondsForIntVariable);
        if(checkLastTwoForZero(lastTwoDigitsOfUserEntry))
            return Double.parseDouble(totalTeamMinutes.getText());


        String splitTotalTime = String.valueOf(totalTeamMinutes.getText());

        //split the double up by the .
        String[] checkTotalTimeOver = splitTotalTime.split("\\.");
        String getSecondsForStringBuilder = checkTotalTimeOver[1];
        //Use stringBuilder to get the first number of the checkUserTimeOver[1]
        StringBuilder sb;
        sb = new StringBuilder(getSecondsForStringBuilder);
        //If the length is equal to 2, then delete the last number in the stringBuilder

        if (checkTotalTimeOver[1].length() == 2) {
            sb.deleteCharAt(1);
            sb.append(0);
        }
        if (checkTotalTimeOver[1].length() == 1) {
            sb.append(0);
        }

        //String used to piece back together the double that was taken apart
        String totalTimePutTogetherString;
        //set the string to the double and return it
        double totalTimePutTogether;
        //Take the first part of the tokenized and set it to the userMinutes variable
        int userMinutes = Integer.parseInt(checkTotalTimeOver[0]);
        //Take the second part of the tokenized and set it to the userSecond variable
        int userSeconds = Integer.parseInt(checkTotalTimeOver[1]);
        sb.deleteCharAt(1);
        int isolateFirstDigit = Integer.parseInt(sb.toString());

        //Get the second number of seconds, if it is there
        int userSecondsSecondNumber = userSeconds % 10;


        userMinutes--;
        isolateFirstDigit += 6;

        //If the seconds are over 60, add a minute back and remove 60 from the seconds
        if (isolateFirstDigit >= 10) {
            userMinutes++;
            isolateFirstDigit -= 6;
        }

        //Get specific minute entries to stop here and send back the number
        if (isolateFirstDigit == 6 && userSecondsSecondNumber != 0) {
            if (checkLastTwo(lastTwoDigitsOfUserEntry)) {
                if (userSecondsSecondNumber != 5) {
                    totalTimePutTogetherString = userMinutes + "." + isolateFirstDigit + userSecondsSecondNumber;
                    totalTimePutTogether = Double.parseDouble(totalTimePutTogetherString);
                    return convertTimeToMinutes(totalTimePutTogether);
                }
            }
        }


        if (checkTotalTimeOver[1].length() == 1) {
            totalTimePutTogetherString = userMinutes + "." + isolateFirstDigit + "0";
        } else {
            totalTimePutTogetherString = userMinutes + "." + isolateFirstDigit + userSecondsSecondNumber;
        }
        totalTimePutTogether = Double.parseDouble(totalTimePutTogetherString);
        return totalTimePutTogether;

    }


    /**
     * Used when trying to figure out how to reconvert the total team minutes
     * @param lastTwo if this argument is one of the cases return false
     * @return return false if it is one of the following second cases
     */
    public boolean checkLastTwo(int lastTwo) {
        return switch (lastTwo) {
            case 31, 32, 33, 34, 36, 37, 38, 39 -> false;
            default -> true;
        };
    }
    /**
     * Figure out if the seconds on the total team minutes ends with a 0
     * @param lastTwo Find out if there are one or two digits for the seconds of the total time
     * @return return true if just 1 digit, false otherwise
     */
    public boolean checkLastTwoForZero(int lastTwo) {
        return lastTwo == 0;
    }

    /**
     * Used to create the lineup in an array that will be passed to the next scene
     * @param selectedPlayer The player that was selected
     * @param getPositionWithButton Position that the player was assigned to
     */
    private void associatePlayerNameWtihPlayerObject(String selectedPlayer, String getPositionWithButton) {
        for (Player value : player) {
            if (selectedPlayer.equals(value.getName())) {
                setPlayerObjectToCorrectPosition(value, getPositionWithButton);
            }

        }
    }


    /**
     * Get the player object that is attached to the minutes played field
     * @param MPField Used to check for the matching player object
     * @return player object
     */
    private Player getPlayerObjectPosition(String MPField) {
        //Create a string array of each player and their position that was assigned
        String[] playerPositions = {player[0].getPositionSetByUser(),
                player[1].getPositionSetByUser(), player[2].getPositionSetByUser(),
                player[3].getPositionSetByUser(), player[4].getPositionSetByUser(),
                player[5].getPositionSetByUser(), player[6].getPositionSetByUser(),
                player[7].getPositionSetByUser(), player[8].getPositionSetByUser(),
                player[9].getPositionSetByUser(), player[10].getPositionSetByUser(),
                player[11].getPositionSetByUser(), player[12].getPositionSetByUser(),
                player[13].getPositionSetByUser(), player[14].getPositionSetByUser()};

        int playerIndex = 0;
        String stringToCheck;
        //Go through each item in the string array and look for the position that
        //matches the minutes played field
        while (true) {
            stringToCheck = playerPositions[playerIndex] + "MP";
            if (MPField.equals(stringToCheck)) {
                return player[playerIndex];
            }
            if (playerIndex == 14) {
                break;
            }
            playerIndex++;
        }
        return player[0];

    }

    /**
     * Used to make sure the user has given all 240 minutes to the players
     * on their team
     *
     * @return return true if the player did not give all the minutes, return
     * false if they did.
     */
    private boolean checkForTime() {
        double checkTime = Double.parseDouble(totalTeamMinutes.getText());
        if(checkTime !=0 ){
            alertHandler(8);
            return true;
        }
        //Create an array of all the minute textfields
        TextField[] MPTFArray = {startingPGMP, startingSGMP, startingSFMP,
                startingPFMP, startingCMP, bench1MP, bench2MP, bench3MP, bench4MP,
                bench5MP, bench6MP, bench7MP, bench8MP, bench9MP, bench10MP};

        String[] getMPTFID;
        String playersName, getMPTFStringInfo;
        //Create a foor loop to go through the entire minute played textFields
        for (TextField textField : MPTFArray) {
            //Get the string of the minutes played textField
            getMPTFStringInfo = textField.toString();
            //If they play has not been assigned minutes, find that player object's
            //index and set their stats to 0
            if (textField.getText().strip().equals("")) {
                getMPTFID = getMPTFStringInfo.split("[=,]");
                //Get the minutes played textField name and get the players name that
                //is in the player's name textField
                playersName = useMPTFToReturnPlayerObjectName(getMPTFID[1]);
                //Find the player object name that matches with the player's name to reset
                for (Player value : player)
                    if (playersName.equals(value.getName())) {
                        changePStatsByMPG(0.0, value);
                        value.setFGP("0.0");
                        value.setThreeFGP("0.0");
                        value.setEFGP("0.0");
                        value.setTwoFGP("0.0");
                        value.setFreeThrowP("0.0");
                        break;
                    }
            }
        }


        return false;

    }

    /**
     * Switch from the assigning positions and minutes scene to the player lineup scene
     * @param event Clicked button event
     */
    @FXML
    private void switchToLineUpBtn(ActionEvent event) {
        if(createLineUpToSend())
            return;

        if(checkForTime())
            return;

        //Create the node that is used in the stage below
        Node node = (Node) event.getSource();

        //Gets the current stage to close it
        Stage stage = (Stage) node.getScene().getWindow();    //((Node) event.getSource()).getScene().getWindow();

        //Closes the current stage
        stage.close();

        try {
            createLineUpToSend();
            playerArray.setPlayers(player);
            //Pass the player object to the stage, so it can be used in the next controller
            stage.setUserData(playerArray);
            //Load the fxml file that will be used next
            //Parent root = FXMLLoader.load(getClass().getResource("AssignMinAndPosition.fxml"));
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowCompletedLineup.fxml")));

            //Create the scene and set the stage to the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }

    }

    private boolean createLineUpToSend() {
        if(!obListOfSelectedPlayers.getItems().isEmpty()){
            alertHandler(9);
            return true;
        }
        return false;
    }

    /**
     * This function is ran if the user loads a previously used lineup. This will
     * add the players names and minutes played to the GUI
     */
    private void recreateLineup() {
        //Create an array of the textfields and minutes played, and player's positions
        //These are needed so when the matching player is found, it will load the
        //player's name and minutes played
        TextField[] tfArray = {startingPGTF, startingSGTF, startingSFTF, startingPFTF,
                startingCTF, bench1TF, bench2TF, bench3TF, bench4TF, bench5TF, bench6TF,
                bench7TF, bench8TF, bench9TF, bench10TF};
        TextField[] mpArray = {startingPGMP, startingSGMP, startingSFMP, startingPFMP,
                startingCMP, bench1MP, bench2MP, bench3MP, bench4MP, bench5MP, bench6MP,
                bench7MP, bench8MP, bench9MP, bench10MP};

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
        String [] splitTextFieldString, splitMPString;

        double doubleTotalTeamMinutes;
        //Go through each item in the string array and look for the position that
        //matches the minutes played field
        while (tfIndex != 15) {

            splitTextFieldString = tfArray[tfIndex].toString().split("[=,]");
            stringToCheck = playerPositions[playerIndex] + "TF";
            if (splitTextFieldString[1].equals(stringToCheck)) {
                splitMPString = mpArray[tfIndex].toString().split("[=,]");

                setMPTFPreviousNumberWhenLoadingLineup(splitMPString[1], player[playerIndex].getMinutesPlayed());
                tfArray[tfIndex].setText(player[playerIndex].getName());
                mpArray[tfIndex].setText(String.valueOf(player[playerIndex].getMinutesPlayed()));
                mpArray[tfIndex].setOpacity(0.99);
                Button changeSelect = useTFStringToReturnButtonObject(splitTextFieldString[1]);
                changeSelect.setText("Deselect");
                doubleTotalTeamMinutes = Double.parseDouble(totalTeamMinutes.getText());
                doubleTotalTeamMinutes -= player[playerIndex].getMinutesPlayed();
                totalTeamMinutes.setText(String.valueOf(doubleTotalTeamMinutes));
                tfIndex++;
                playerIndex = 0;
            } else
                playerIndex++;
        }
    }

    /**
     * Used throughout the file to call an alert error if something went wrong
     *
     * @param whichAlert Used to identify the alert to call
     */
    public void alertHandler(int whichAlert) {
        alert = new Alert(Alert.AlertType.ERROR);
        switch (whichAlert) {
            case 1 -> {
                alert.setContentText("No player was selected, please select a player.");
                alert.showAndWait();
            }
            case 2 -> {
                alert.setContentText("Please insert a player into this position before entering their minutes per game.");
                alert.showAndWait();
            }
            case 3 -> {
                alert.setContentText("Invalid entry. Please check your entry and try again..");
                alert.showAndWait();
            }
            case 4 -> {
                alert.setContentText("You have exceeded the total minutes limit."
                        + " You have " + totalTeamMinutes.getText() + " minutes left.");
                alert.showAndWait();
            }
            case 5 -> {
                alert.setContentText("You have exceeded the max minutes a player can play in a game."
                        + "\nA player can only play 48 minutes in a game.");
                alert.showAndWait();
            }
            case 6 -> {
                alert.setContentText("A player cannot play negative minutes. Please give a vaild entry.");
                alert.showAndWait();
            }
            case 7 -> {
                alert.setContentText("Invalid entry. Please check your entry and try again.");
                alert.showAndWait();
            }
            case 8 -> {
                alert.setContentText("You must give out all of the available "
                        + "minutes to the players on your team."
                        + "\nNot every player needs to have minutes assigned to them.");
                alert.showAndWait();
            }
            case 9 -> {
                alert.setContentText("You must give every player that you chose a position"
                        + "on your team."
                        + "\nPlease go back and add the remainging players to your lineup.");
                alert.showAndWait();
            }
        }
    }

    /**
     * This sets the current user entry to the prev... variable. This is used in
     * the event that the user wishes to change the players minutes per game
     * after they have already set it.
     *
     * @param ID Used to find the MP field that was changed
     * @param numToHold This is the number that the prev... variable will hold
     */
    public void setMPTFPreviousNumber(String ID, double numToHold) {
        //Find which button or minutes player TextField string was passed and return the correlated textfield
        switch (ID) {
            case "setPGMP" -> prevStartingPGMP = numToHold;
            case "setSGMP" -> prevStartingSGMP = numToHold;
            case "setSFMP" -> prevStartingSFMP = numToHold;
            case "setPFMP" -> prevStartingPFMP = numToHold;
            case "setCMP" -> prevStartingCMP = numToHold;
            case "setBench1MP" -> prevBench1MP = numToHold;
            case "setBench2MP" -> prevBench2MP = numToHold;
            case "setBench3MP" -> prevBench3MP = numToHold;
            case "setBench4MP" -> prevBench4MP = numToHold;
            case "setBench5MP" -> prevBench5MP = numToHold;
            case "setBench6MP" -> prevBench6MP = numToHold;
            case "setBench7MP" -> prevBench7MP = numToHold;
            case "setBench8MP" -> prevBench8MP = numToHold;
            case "setBench9MP" -> prevBench9MP = numToHold;
            case "setBench10MP" -> prevBench10MP = numToHold;
        }

    }

    /**
     * This sets the current user entry to the prev... variable. This is used in
     * the event that the user loaded a previously made lineup
     *
     * @param ID Used to find the MP field that was changed
     * @param numToHold This is the number that the prev... variable will hold
     */
    public void setMPTFPreviousNumberWhenLoadingLineup(String ID, double numToHold) {
        //Find which button or minutes player TextField string was passed and return the correlated textfield
        switch (ID) {
            case "startingPGMP" -> prevStartingPGMP = numToHold;
            case "startingSGMP" -> prevStartingSGMP = numToHold;
            case "startingSFMP" -> prevStartingSFMP = numToHold;
            case "startingPFMP" -> prevStartingPFMP = numToHold;
            case "startingCMP" -> prevStartingCMP = numToHold;
            case "bench1MP" -> prevBench1MP = numToHold;
            case "bench2MP" -> prevBench2MP = numToHold;
            case "bench3MP" -> prevBench3MP = numToHold;
            case "bench4MP" -> prevBench4MP = numToHold;
            case "bench5MP" -> prevBench5MP = numToHold;
            case "bench6MP" -> prevBench6MP = numToHold;
            case "bench7MP" -> prevBench7MP = numToHold;
            case "bench8MP" -> prevBench8MP = numToHold;
            case "bench9MP" -> prevBench9MP = numToHold;
            case "bench10MP" -> prevBench10MP = numToHold;
        }

    }
    /**
     * Used to set the lineup array with player at the correct position
     * @param playerToSet The players position
     * @param getPosition Set the players position
     */
    public void setPlayerObjectToCorrectPosition(Player playerToSet, String getPosition){
        switch (getPosition) {
            case "setUnSetPG" -> playerToSet.setPositionSetByUser("startingPG");
            case "setUnSetSG" -> playerToSet.setPositionSetByUser("startingSG");
            case "setUnSetSF" -> playerToSet.setPositionSetByUser("startingSF");
            case "setUnSetPF" -> playerToSet.setPositionSetByUser("startingPF");
            case "setUnSetC" -> playerToSet.setPositionSetByUser("startingC");
            case "setUnSetBench1" -> playerToSet.setPositionSetByUser("bench1");
            case "setUnSetBench2" -> playerToSet.setPositionSetByUser("bench2");
            case "setUnSetBench3" -> playerToSet.setPositionSetByUser("bench3");
            case "setUnSetBench4" -> playerToSet.setPositionSetByUser("bench4");
            case "setUnSetBench5" -> playerToSet.setPositionSetByUser("bench5");
            case "setUnSetBench6" -> playerToSet.setPositionSetByUser("bench6");
            case "setUnSetBench7" -> playerToSet.setPositionSetByUser("bench7");
            case "setUnSetBench8" -> playerToSet.setPositionSetByUser("bench8");
            case "setUnSetBench9" -> playerToSet.setPositionSetByUser("bench9");
            case "setUnSetBench10" -> playerToSet.setPositionSetByUser("bench10");
        }

    }

    /**
     * Used to find the TextField that the button is associated with
     *
     * @param ID This is the button's fx:id
     * @return This is the Players textField that is associated with the button
     */
    public TextField findTFByButtonAndReturnTF(String ID) {
        //Find which button or minutes player TextField string was passed and return the correlated textfield
        return switch (ID) {
            case "setUnSetPG" -> startingPGTF;
            case "setUnSetSG" -> startingSGTF;
            case "setUnSetSF" -> startingSFTF;
            case "setUnSetPF" -> startingPFTF;
            case "setUnSetC" -> startingCTF;
            case "setUnSetBench1" -> bench1TF;
            case "setUnSetBench2" -> bench2TF;
            case "setUnSetBench3" -> bench3TF;
            case "setUnSetBench4" -> bench4TF;
            case "setUnSetBench5" -> bench5TF;
            case "setUnSetBench6" -> bench6TF;
            case "setUnSetBench7" -> bench7TF;
            case "setUnSetBench8" -> bench8TF;
            case "setUnSetBench9" -> bench9TF;
            default -> bench10TF;
        };
    }

    /**
     * Used to find the TextField that the MP field is associated with
     *
     * @param ID This is the MP field's fx:id
     * @return This is the Players textField that is associated with the button
     */
    public TextField useMPTFToREturnPlayerNameTF(String ID) {
        return switch (ID) {
            case "setPGMP" -> startingPGTF;
            case "setSGMP" -> startingSGTF;
            case "setSFMP" -> startingSFTF;
            case "setPFMP" -> startingPFTF;
            case "setCMP" -> startingCTF;
            case "setBench1MP" -> bench1TF;
            case "setBench2MP" -> bench2TF;
            case "setBench3MP" -> bench3TF;
            case "setBench4MP" -> bench4TF;
            case "setBench5MP" -> bench5TF;
            case "setBench6MP" -> bench6TF;
            case "setBench7MP" -> bench7TF;
            case "setBench8MP" -> bench8TF;
            case "setBench9MP" -> bench9TF;
            default -> bench10TF;
        };
    }

    /**
     * Used to get the button object that was used
     *
     * @param textFieldForPlayer The string of the players textField
     * @return The button object
     */
    public Button useTFStringToReturnButtonObject(String textFieldForPlayer) {
        //Find which button string was passed and return the button object
        return switch (textFieldForPlayer) {
            case "startingPGTF" -> setUnSetPG;
            case "startingSGTF" -> setUnSetSG;
            case "startingSFTF" -> setUnSetSF;
            case "startingPFTF" -> setUnSetPF;
            case "startingCTF" -> setUnSetC;
            case "bench1TF" -> setUnSetBench1;
            case "bench2TF" -> setUnSetBench2;
            case "bench3TF" -> setUnSetBench3;
            case "bench4TF" -> setUnSetBench4;
            case "bench5TF" -> setUnSetBench5;
            case "bench6TF" -> setUnSetBench6;
            case "bench7TF" -> setUnSetBench7;
            case "bench8TF" -> setUnSetBench8;
            case "bench9TF" -> setUnSetBench9;
            default -> setUnSetBench10;
        };
    }

    /**
     * Used to return the MP field object
     *
     * @param ID String of the MP Field's button fx:id
     * @return MP TextField object
     */
    public TextField useMPStringToReturnMPObject(String ID) {
        return switch (ID) {
            case "setPGMP" -> startingPGMP;
            case "setSGMP" -> startingSGMP;
            case "setSFMP" -> startingSFMP;
            case "setPFMP" -> startingPFMP;
            case "setCMP" -> startingCMP;
            case "setBench1MP" -> bench1MP;
            case "setBench2MP" -> bench2MP;
            case "setBench3MP" -> bench3MP;
            case "setBench4MP" -> bench4MP;
            case "setBench5MP" -> bench5MP;
            case "setBench6MP" -> bench6MP;
            case "setBench7MP" -> bench7MP;
            case "setBench8MP" -> bench8MP;
            case "setBench9MP" -> bench9MP;
            default -> bench10MP;
        };
    }

    /**
     * Used to return the previous user entry for the MP Field
     *
     * @param ID String of the MP Field
     * @return The previous entry for the MP field
     */
    public double returnMPTFPreviousNumber(String ID) {
        return switch (ID) {
            case "setPGMP" -> prevStartingPGMP;
            case "setSGMP" -> prevStartingSGMP;
            case "setSFMP" -> prevStartingSFMP;
            case "setPFMP" -> prevStartingPFMP;
            case "setCMP" -> prevStartingCMP;
            case "setBench1MP" -> prevBench1MP;
            case "setBench2MP" -> prevBench2MP;
            case "setBench3MP" -> prevBench3MP;
            case "setBench4MP" -> prevBench4MP;
            case "setBench5MP" -> prevBench5MP;
            case "setBench6MP" -> prevBench6MP;
            case "setBench7MP" -> prevBench7MP;
            case "setBench8MP" -> prevBench8MP;
            case "setBench9MP" -> prevBench9MP;
            default -> prevBench10MP;
        };
    }

    /**
     * Used to return the MP field object
     *
     * @param ID String of the MP Field's text and match it with the player's name
     * @return MP String player object's name
     */
    public String useMPTFToReturnPlayerObjectName(String ID) {
        return switch (ID) {
            case "startingPGMP" -> startingPGTF.getText();
            case "startingSGMP" -> startingSGTF.getText();
            case "startingSFMP" -> startingSFTF.getText();
            case "startingPFMP" -> startingPFTF.getText();
            case "startingCMP" -> startingCTF.getText();
            case "bench1MP" -> bench1TF.getText();
            case "bench2MP" -> bench2TF.getText();
            case "bench3MP" -> bench3TF.getText();
            case "bench4MP" -> bench4TF.getText();
            case "bench5MP" -> bench5TF.getText();
            case "bench6MP" -> bench6TF.getText();
            case "bench7MP" -> bench7TF.getText();
            case "bench8MP" -> bench8TF.getText();
            case "bench9MP" -> bench9TF.getText();
            case "bench10MP" -> bench10TF.getText();
            default -> "Object not found.";
        };
    }
}


