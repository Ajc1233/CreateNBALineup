package com.lineupcreator.demo;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.text.Normalizer;

/**
 *
 * @author alecc
 */
public class ListOfAllPlayersController implements Initializable {

    PlayerArrayHolder playerArray;
    PlayerArrayHolder selectedPlayerArray;
    Player[] player;
    Player[] selectedPA;
    int i = 0;
    Alert alert;
    @FXML
    private TableView<Player> NBAPlayers;
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, String> positionColumn;
    @FXML
    private TableColumn<Player, String> ageColumn;
    @FXML
    private TableColumn<Player, String> teamColumn;
    @FXML
    private TableColumn<Player, String> gpColumn;
    @FXML
    private TableColumn<Player, String> gsColumn;
    @FXML
    private TableColumn<Player, String> MPColumn;
    @FXML
    private TableColumn<Player, String> fgmColumn;
    @FXML
    private TableColumn<Player, String> fgaColumn;
    @FXML
    private TableColumn<Player, String> fgpColumn;
    @FXML
    private TableColumn<Player, String> threefgpColumn;
    @FXML
    private TableColumn<Player, String> threefgaColumn;
    @FXML
    private TableColumn<Player, String> threeFGPColumn;
    @FXML
    private TableColumn<Player, String> twoFGMColumn;
    @FXML
    private TableColumn<Player, String> twoFGAColumn;
    @FXML
    private TableColumn<Player, String> twoFGPColumn;
    @FXML
    private TableColumn<Player, String> effFGPColumn;
    @FXML
    private TableColumn<Player, String> ftMColumn;
    @FXML
    private TableColumn<Player, String> ftAColumn;
    @FXML
    private TableColumn<Player, String> ftPColumn;
    @FXML
    private TableColumn<Player, String> offRBPColumn;
    @FXML
    private TableColumn<Player, String> defRBColumn;
    @FXML
    private TableColumn<Player, String> totRBColumn;
    @FXML
    private TableColumn<Player, String> asstColumn;
    @FXML
    private TableColumn<Player, String> stlColumn;
    @FXML
    private TableColumn<Player, String> blkColumn;
    @FXML
    private TableColumn<Player, String> turnOColumn;
    @FXML
    private TableColumn<Player, String> pfColumn;
    @FXML
    private TableColumn<Player, String> ptsColumn;
    @FXML
    private ListView<String> currentLineUp;
    @FXML
    private TextField playerSearch;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField numberOfPlayersSelected;
    @FXML
    private Label numOfPlayersSelectedTF;
    String numPlayaTF;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Get the playerArray object from the OpeningController file
        playerArray = PlayerArrayHolder.getInstance();
        //Set the Player object to the playerArray, so it can be used in the file
        player = playerArray.getPlayers();
        //Create the array used to hold the selected players from the tableview
        selectedPA = new Player[15];
        //Call the method to build the tableview
        buildTable(0, "");


    }

    /**
     * Used to set the columns of the tableView to the players stats Goes
     * through the player array to do so. Also sets text to center of the column
     */
    public void buildTable(int i, String nameS) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionColumn.setStyle("-fx-alignment: CENTER;");

        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageColumn.setStyle("-fx-alignment: CENTER;");

        teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
        teamColumn.setStyle("-fx-alignment: CENTER;");

        gpColumn.setCellValueFactory(new PropertyValueFactory<>("gamesPlayed"));
        gpColumn.setStyle("-fx-alignment: CENTER;");

        gsColumn.setCellValueFactory(new PropertyValueFactory<>("gamesStarted"));
        gsColumn.setStyle("-fx-alignment: CENTER;");

        MPColumn.setCellValueFactory(new PropertyValueFactory<>("minutesPlayed"));
        MPColumn.setStyle("-fx-alignment: CENTER;");

        fgmColumn.setCellValueFactory(new PropertyValueFactory<>("FGM"));
        fgmColumn.setStyle("-fx-alignment: CENTER;");

        fgaColumn.setCellValueFactory(new PropertyValueFactory<>("FGA"));
        fgaColumn.setStyle("-fx-alignment: CENTER;");

        fgpColumn.setCellValueFactory(new PropertyValueFactory<>("FGP"));
        fgpColumn.setStyle("-fx-alignment: CENTER;");

        threefgpColumn.setCellValueFactory(new PropertyValueFactory<>("threeFGM"));
        threefgpColumn.setStyle("-fx-alignment: CENTER;");

        threefgaColumn.setCellValueFactory(new PropertyValueFactory<>("threeFGA"));
        threefgaColumn.setStyle("-fx-alignment: CENTER;");

        threeFGPColumn.setCellValueFactory(new PropertyValueFactory<>("threeFGP"));
        threeFGPColumn.setStyle("-fx-alignment: CENTER;");

        twoFGMColumn.setCellValueFactory(new PropertyValueFactory<>("twoFGM"));
        twoFGMColumn.setStyle("-fx-alignment: CENTER;");

        twoFGAColumn.setCellValueFactory(new PropertyValueFactory<>("twoFGA"));
        twoFGAColumn.setStyle("-fx-alignment: CENTER;");

        twoFGPColumn.setCellValueFactory(new PropertyValueFactory<>("twoFGP"));
        twoFGPColumn.setStyle("-fx-alignment: CENTER;");

        effFGPColumn.setCellValueFactory(new PropertyValueFactory<>("eFGP"));
        effFGPColumn.setStyle("-fx-alignment: CENTER;");

        ftMColumn.setCellValueFactory(new PropertyValueFactory<>("freeThrowsM"));
        ftMColumn.setStyle("-fx-alignment: CENTER;");

        ftAColumn.setCellValueFactory(new PropertyValueFactory<>("freeThrowsA"));
        ftAColumn.setStyle("-fx-alignment: CENTER;");

        ftPColumn.setCellValueFactory(new PropertyValueFactory<>("freeThrowP"));
        ftPColumn.setStyle("-fx-alignment: CENTER;");

        offRBPColumn.setCellValueFactory(new PropertyValueFactory<>("offRB"));
        offRBPColumn.setStyle("-fx-alignment: CENTER;");

        defRBColumn.setCellValueFactory(new PropertyValueFactory<>("defRB"));
        defRBColumn.setStyle("-fx-alignment: CENTER;");

        totRBColumn.setCellValueFactory(new PropertyValueFactory<>("totRB"));
        totRBColumn.setStyle("-fx-alignment: CENTER;");

        asstColumn.setCellValueFactory(new PropertyValueFactory<>("assists"));
        asstColumn.setStyle("-fx-alignment: CENTER;");

        stlColumn.setCellValueFactory(new PropertyValueFactory<>("steals"));
        stlColumn.setStyle("-fx-alignment: CENTER;");

        blkColumn.setCellValueFactory(new PropertyValueFactory<>("blocks"));
        blkColumn.setStyle("-fx-alignment: CENTER;");

        turnOColumn.setCellValueFactory(new PropertyValueFactory<>("turnOvers"));
        turnOColumn.setStyle("-fx-alignment: CENTER;");

        pfColumn.setCellValueFactory(new PropertyValueFactory<>("fouls"));
        pfColumn.setStyle("-fx-alignment: CENTER;");

        ptsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        ptsColumn.setStyle("-fx-alignment: CENTER;");

        //If calling line has a 1, the user is searching for a player and the table needs to be recreated
        if (i == 1) {
            NBAPlayers.setItems(whereToSearch(nameS));
        } else {
            //uses the getStats method to set the stats for each player
            NBAPlayers.setItems(getStats());
        }
    }

    /**
     * Creates a observableArrayList to hold all the players stats
     *
     * @return returns the build observableList that is used in the table
     */
    public ObservableList<Player> getStats() {
        ObservableList<Player> playa = FXCollections.observableArrayList();
        Collections.addAll(playa, player);
        return playa;
    }

    /**
     * The method for the select player button
     */
    @FXML
    private void SelectThePlayer(ActionEvent event) {
        //Create an alert object to use in the event the user selects the same
        //player twice or goes over the 15 player limit
        alert = new Alert(Alert.AlertType.ERROR);
        //Brings up the alert if more than 15 players are selected
        if (i == 15) {
            alert.setContentText("You have already selected 15 players.");
            alert.showAndWait();
            return;
        }

        //Gets the selection from the tableview to set to the selectedPA array
        if (NBAPlayers.getSelectionModel().getSelectedItem() != null) {
            //Set a player object to the selected player
            Player selectedPlaya = NBAPlayers.getSelectionModel().getSelectedItem();
            //If the player has already been selected, bring up the alert
            if (playerNotAlreadySelected(selectedPA, selectedPlaya)) {
                alert.setContentText("You have already selected that player. "
                        + "Please choose a different player");
                alert.showAndWait();
                return;
            }
            //Set the selected player to the selectedPA array
            selectedPA[i] = selectedPlaya;
            //Use the selectedPA array to populate the listview
            currentLineUp.getItems().addAll(selectedPA[i].getName());
            //Add one to i, so the next player selected goes to the next spot
            //in the selectedPA array
            i++;
            numPlayaTF = String.valueOf(i);
            numOfPlayersSelectedTF.setText("Number of Players Selected: " + numPlayaTF);

            if (i == 15) {
                Alert completeLineup = new Alert(Alert.AlertType.INFORMATION);
                completeLineup.setHeaderText("Lineup Complete.");
                completeLineup.setContentText("You have selected enough players to move on." +
                        "\nClick 'Confirm Team' to continue or click on a player's name in your lineup " +
                        "to remove them and add a different player.");
                completeLineup.showAndWait();
            }
        }
    }

    /**
     * Checks the array to see if the player was already selected
     *
     * @param selectedPA  Passes the array of players already selected
     * @param selectedPlaya Player that the user selected
     * @return controls the alert in the calling method
     */
    private boolean playerNotAlreadySelected(Player[] selectedPA, Player selectedPlaya) {
        //Use for loop to go through each player in the selectedPA array
        for (Player playersInArray : selectedPA) {
            //If the selectedPlaya is equal to any of the players in playersInArray
            //Then return true so the alert pops up in the calling method
            if (playersInArray == selectedPlaya) {
                return true;
            }
        }
        //Return false if none of the players match, so no alert is called
        return false;
    }

    /**
     * Used to deselected players that were already selected. Removes the player
     * from both the selectedPA array and the listview
     *
     * @param event For MouseEvent
     */
    @FXML
    private void deselectPlayer(MouseEvent event) {
        int arrayLen = (selectedPA.length + 1);
        //Create two arrays. The first holds the current selectedPA array
        Player[] holdSelectedPlayerArray = new Player[arrayLen];
        //The second is the updated array that will hold the above array, just
        //without the deselected player
        Player[] holdSelectedPlayerArray1 = new Player[selectedPA.length];
        //Get the index of the player to delete
        int delPlayer = currentLineUp.getSelectionModel().getSelectedIndex();
        if(delPlayer == -1)
            return;

        holdSelectedPlayerArray = selectedPA;
        int x = 0;
        for (int i = 0; i < selectedPA.length; i++) {
            //If the holdSelectedPlayerArray has reached its end, terminate the for loop
            if (holdSelectedPlayerArray[i] == null) {
                break;
            }
            if (x == 15) {
                break;
            }

            //Once the delete player index is equal to i, do one of two things...
            if (delPlayer == i) {
                //If the player to be deleted is at the end of the list
                //skip this if statement and set the two arrays at the
                //delete player's index to equal(line 314)
                if (i == 14) {
                    continue;
                }
                //If the deleted player's index is NOT at the end of the list
                //then move up the index of the array that holds the currently
                //selected players by one spot and set it equal to the updated array
                //at its current spot
                holdSelectedPlayerArray1[i] = holdSelectedPlayerArray[x + 1];
                x += 2;
                continue;
            }
            //If the variable i is not equal to the deleted player's index, just assign the index
            //of the old array holder to the updated array holder
            holdSelectedPlayerArray1[i] = holdSelectedPlayerArray[x];
            x++;
        }
        //Once through the for loop and the updated array is complete, set the
        //updated array to the selectedPA array
        selectedPA = holdSelectedPlayerArray1;
        //Clear the listview of all players and repopulate it
        currentLineUp.getItems().clear();
        //Use the for loop to repopulate the listview
        for (Player sPA : selectedPA) {
            if (sPA == null) {
                break;
            }
            currentLineUp.getItems().addAll(sPA.getName());
        }
        //reduce the variable i by one, so the player can continue to add to the list
        i--;
        numPlayaTF = String.valueOf(i);
        numOfPlayersSelectedTF.setText("Number of Players Selected: " + numPlayaTF);
    }

    @FXML
    private void SearchForPlayer() {
        //Get the text that the user wants to search
        String toSearch = playerSearch.getText();

        if (toSearch.equals("") || toSearch.equals(" ")) {
            buildTable(0, "");
        } else {
            buildTable(1, toSearch);
            NBAPlayers.refresh();
        }
    }

    int charCounter;

    /**
     * This is the search function. The user enters a name to search
     *
     * @param name The string of characters that will be used to search for players
     * @return Return player names that match the search
     */
    private ObservableList<Player> whereToSearch(String name) {
        //Create observableList to hold all players that meet the search criteria
        ObservableList<Player> playerNames = FXCollections.observableArrayList();
        String[] checkSearchNameSize;
        name = name.strip();
        checkSearchNameSize = name.split(" ");
        //Create a char variable that will hold the letters to search and the players to
        //check the search against
        char userCharCounter, countStringToMatch;
        //Create strings to hold the players name, first name, and last name
        String stringToMatch, nameOfPlayerInArrayFirstN, nameOfPlayerInArrayLastN;
        //String array to split up first and last name of the players in the table view
        String[] nameOfPlayerInArray;
        //for loop to go through each player in the array
        for (Player value : player) {
            //If the searched name is only a first or last name, then this if
            //statement will run and check the user entered name for both
            //first name matches and last name matches

            if (checkSearchNameSize.length == 1) {
                //Set stringToMatch equal to the players name
                stringToMatch = value.getName();
                //Split the player object name into first and last name

                stringToMatch = Normalizer.normalize(stringToMatch, Normalizer.Form.NFD);

                nameOfPlayerInArray = stringToMatch.split(" ");
                nameOfPlayerInArrayFirstN = nameOfPlayerInArray[0].toLowerCase().strip();
                try {
                    nameOfPlayerInArrayLastN = nameOfPlayerInArray[1].toLowerCase().strip();
                } catch (IndexOutOfBoundsException e) {
                    nameOfPlayerInArrayLastN = null;
                }

                //reset the charCounter to 0
                charCounter = 0;
                for (int x = 0; x < name.length(); x++) {
                    //Go through each letter of the string that was entered by the user
                    userCharCounter = name.toLowerCase().charAt(x);
                    //Go through each letter of the player's first name
                    countStringToMatch = nameOfPlayerInArrayFirstN.charAt(x);
                    //If the letters match up, increase the charCounter variable
                    if (userCharCounter == countStringToMatch) {
                        charCounter++;
                    } //If any of the letters do not add up, break the loop and go to the
                    //next player's name
                    else {
                        break;
                    }
                    //If the charCounter is equal to the user entered string, then
                    //it is a match. That player's object is added to the observableList
                    if (charCounter == name.length() || charCounter == nameOfPlayerInArrayFirstN.length()) {
                        playerNames.add(value);
                        break;
                    }

                }

                for (int x = 0; x < name.length(); x++) {
                    if (nameOfPlayerInArrayLastN == null)
                        break;
                    //Go through each letter of the string that was entered by the user
                    userCharCounter = name.toLowerCase().charAt(x);
                    //Go through each letter of the player's first name

                    countStringToMatch = nameOfPlayerInArrayLastN.charAt(x);
                    //If the letters match up, increase the charCounter variable
                    if (userCharCounter == countStringToMatch) {
                        charCounter++;
                    } //If any of the letters do not add up, break the loop and go to the
                    //next player's name
                    else {
                        break;
                    }
                    //If the charCounter is equal to the user entered string, then
                    //it is a match. That player's object is added to the observableList
                    if (charCounter == name.length()) {
                        playerNames.add(value);
                    }
                }
            }

            //If the user searched name is a first and last name, then this if
            //statement will run. It will check for the full name entered by the user.
            if (checkSearchNameSize.length == 2) {
                stringToMatch = value.getName();
                //reset the charCounter to 0
                charCounter = 0;
                for (int x = 0; x < name.length(); x++) {
                    //Go through each letter of the string that was entered by the user
                    userCharCounter = name.toLowerCase().strip().charAt(x);
                    //Go through each letter of the player's name
                    countStringToMatch = stringToMatch.toLowerCase().strip().charAt(x);
                    //If the letters match up, increase the charCounter variable
                    if (userCharCounter == countStringToMatch) {
                        charCounter++;
                    } //If any of the letters do not add up, break the loop and go to the
                    //next player's name
                    else {
                        break;
                    }
                    //If the charCounter is equal to the user entered string, then
                    //it is a match. That player's object is added to the observableList
                    if (charCounter == name.length()) {
                        playerNames.add(value);
                    }

                }
            }
        }

        return playerNames;
    }

    /**
     * This method is called when the user is ready to move onto setting positions and minutes for their lineup.
     */
    @FXML
    public void switchToLineUpBtn(ActionEvent event) {

        //Make sure the user has selected 15 players
        if (selectedPA[14] == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select 15 players before you continue to the lineup builder.");
            alert.showAndWait();
            return;

        }
        //Create the node that is used in the stage below
        Node node = (Node) event.getSource();

        //Gets the current stage to close it
        Stage stage = (Stage) node.getScene().getWindow();    //((Node) event.getSource()).getScene().getWindow();

        //Closes the current stage
        stage.close();

        try {
            //Gets the playerArray class and passes the Player array to it
            selectedPlayerArray = PlayerArrayHolder.getInstance();
            selectedPlayerArray.setPlayers(selectedPA);

            //Pass the playerArray object to the stage, so it can be used in the next controller
            stage.setUserData(selectedPlayerArray);
            //Load the fxml file that will be used next
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AssignMinAndPosition.fxml")));
            //Parent root = FXMLLoader.load(getClass().getResource("PlrStats.fxml"));

            //Create the scene and set the stage to the scene
            Scene scene = new Scene(root);
            stage.setScene(scene);

            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }

    }

    /**
     * Allow the user to click the search button or to hit the ENTER key to search for a player
     */
    @FXML
    private void SearchForPlayerByEnter(KeyEvent event) {
        if (event.getCode().toString().equals("ENTER")) {
            SearchForPlayer();
        }
    }
}
