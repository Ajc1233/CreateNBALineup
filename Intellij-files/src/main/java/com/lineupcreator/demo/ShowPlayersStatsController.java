package com.lineupcreator.demo;

import javafx.fxml.Initializable;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ShowPlayersStatsController implements Initializable {

    Player player;
    PlayerArrayHolder playerArray;

    @FXML
    private Label showStatPos;
    @FXML
    private Label showStatGP;
    @FXML
    private Label showStatGS;
    @FXML
    private Label showStatMP;
    @FXML
    private Label showStatFGM;
    @FXML
    private Label showStatFGA;
    @FXML
    private Label showStatFGPer;
    @FXML
    private Label showStat3PM;
    @FXML
    private Label showStat3PA;
    @FXML
    private Label showStat3PPer;
    @FXML
    private Label showStat2PM;
    @FXML
    private Label showStat2PA;
    @FXML
    private Label showStat2PPer;
    @FXML
    private Label showStatEFG;
    @FXML
    private Label showStatFTM;
    @FXML
    private Label showStatFTA;
    @FXML
    private Label showStatFTPer;
    @FXML
    private Label showStatORB;
    @FXML
    private Label showStatDRB;
    @FXML
    private Label showStatTRB;
    @FXML
    private Label showStatAST;
    @FXML
    private Label showStatSTL;
    @FXML
    private Label showStatBLK;
    @FXML
    private Label showStatTOV;
    @FXML
    private Label showStatPF;
    @FXML
    private Label showStatPTS;
    @FXML
    private Label playerLabelName;
    @FXML
    private Label playerLabelAge;
    @FXML
    private Label playerLabelTeam;
    @FXML
    private Label playerStatsLabelGP;
    @FXML
    private Label playerStatsLabelFTPer;
    @FXML
    private Label playerStatsLabelGS;
    @FXML
    private Label playerStatsLabelMP;
    @FXML
    private Label playerStatsLabelFGM;
    @FXML
    private Label playerStatsLabelFGA;
    @FXML
    private Label playerStatsLabelFGPer;
    @FXML
    private Label playerStatsLabel3PM;
    @FXML
    private Label playerStatsLabel3PA;
    @FXML
    private Label playerStatsLabel3PPer;
    @FXML
    private Label playerStatsLabel2PM;
    @FXML
    private Label playerStatsLabel2PA;
    @FXML
    private Label playerStatsLabel2PPer;
    @FXML
    private Label playerStatsLabelEFGPer;
    @FXML
    private Label playerStatsLabelFTM;
    @FXML
    private Label playerStatsLabelFTA;
    @FXML
    private Label playerStatsLabelORB;
    @FXML
    private Label playerStatsLabelDRB;
    @FXML
    private Label playerStatsLabelTRB;
    @FXML
    private Label playerStatsLabelAST;
    @FXML
    private Label playerStatsLabelSTL;
    @FXML
    private Label playerStatsLabelBLK;
    @FXML
    private Label playerStatsLabelTOV;
    @FXML
    private Label playerStatsLabelPF;
    @FXML
    private Label playerStatsLabelPTS;
    @FXML
    private Label showThePlayersFTPer;
    @FXML
    private Label explainGP;
    @FXML
    private Label explainGS;
    @FXML
    private Label explainMP;
    @FXML
    private Label explainFGM;
    @FXML
    private Label explainFGA;
    @FXML
    private Label explainFGPer;
    @FXML
    private Label explain3PM;
    @FXML
    private Label explain3PA;
    @FXML
    private Label explain3PPer;
    @FXML
    private Label explain2PM;
    @FXML
    private Label explain2PA;
    @FXML
    private Label explain2PPer;
    @FXML
    private Label explainEFG;
    @FXML
    private Label explainFTM;
    @FXML
    private Label explainFTA;
    @FXML
    private Label explainFTPer;
    @FXML
    private Label explainORB;
    @FXML
    private Label explainDRB;
    @FXML
    private Label explainTRB;
    @FXML
    private Label explainAST;
    @FXML
    private Label explainSTL;
    @FXML
    private Label explainBLK;
    @FXML
    private Label explainTOV;
    @FXML
    private Label explainPF;
    @FXML
    private Label explainPTS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Get the playerArray object from the OpeningController file
        playerArray = PlayerArrayHolder.getInstance();
        //Set the Player object to the playerArray, so it can be used in the file
        player = playerArray.getPlayer();
        //Create the array used to hold the selected players from the tableview

        playerLabelName.setText("Name: " + player.getName());
        playerLabelAge.setText("Age: " + player.getAge());
        playerLabelTeam.setText("Team: " + player.getTeam());
        showStatPos.setText("Pos: " + player.getPosition());
        playerStatsLabelGP.setText(String.valueOf(player.getGamesPlayed()));
        playerStatsLabelGS.setText(String.valueOf(player.getGamesStarted()));
        playerStatsLabelMP.setText(String.valueOf(player.getMinutesPlayed()));
        playerStatsLabelFGM.setText(String.valueOf(player.getFGM()));
        playerStatsLabelFGA.setText(String.valueOf(player.getFGA()));
        playerStatsLabelFGPer.setText(String.valueOf(player.getFGP()));
        playerStatsLabel3PM.setText(String.valueOf(player.getThreeFGM()));
        playerStatsLabel3PA.setText(String.valueOf(player.getThreeFGA()));
        playerStatsLabel3PPer.setText(String.valueOf(player.getThreeFGP()));
        playerStatsLabel2PM.setText(String.valueOf(player.getTwoFGM()));
        playerStatsLabel2PA.setText(String.valueOf(player.getTwoFGA()));
        playerStatsLabel2PPer.setText(String.valueOf(player.getTwoFGP()));
        playerStatsLabelEFGPer.setText(String.valueOf(player.getEFGP()));
        playerStatsLabelFTM.setText(String.valueOf(player.getFreeThrowsM()));
        playerStatsLabelFTA.setText(String.valueOf(player.getFreeThrowsA()));
        playerStatsLabelFTPer.setText(String.valueOf(player.getFreeThrowP()));
        playerStatsLabelORB.setText(String.valueOf(player.getOffRB()));
        playerStatsLabelDRB.setText(String.valueOf(player.getDefRB()));
        playerStatsLabelTRB.setText(String.valueOf(player.getTotRB()));
        playerStatsLabelAST.setText(String.valueOf(player.getAssists()));
        playerStatsLabelSTL.setText(String.valueOf(player.getSteals()));
        playerStatsLabelBLK.setText(String.valueOf(player.getBlocks()));
        playerStatsLabelTOV.setText(String.valueOf(player.getTurnOvers()));
        playerStatsLabelPF.setText(String.valueOf(player.getFouls()));
        playerStatsLabelPTS.setText(String.valueOf(player.getPoints()));

    }

    @FXML
    public void switchToLineUp(ActionEvent event) throws IOException {

        //Changes the FXMLLoader to the fxml file that will be used for the new stage
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowCompletedLineup.fxml")));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void showGPStat(MouseEvent event) {
        explainGP.setVisible(true);
    }

    @FXML
    private void hideGPStat(MouseEvent event) {
        explainGP.setVisible(false);
    }

    @FXML
    private void showGSStat(MouseEvent event) {
        explainGS.setVisible(true);
    }

    @FXML
    private void hideGSStat(MouseEvent event) {
        explainGS.setVisible(false);
    }

    @FXML
    private void showMPStat(MouseEvent event) {
        explainMP.setVisible(true);
    }

    @FXML
    private void hideMPStat(MouseEvent event) {
        explainMP.setVisible(false);
    }

    @FXML
    private void showFGMStat(MouseEvent event) {
        explainFGM.setVisible(true);
    }

    @FXML
    private void hideFGMStat(MouseEvent event) {
        explainFGM.setVisible(false);
    }

    @FXML
    private void showFGAStat(MouseEvent event) {
        explainFGA.setVisible(true);
    }

    @FXML
    private void hideFGAStat(MouseEvent event) {
        explainFGA.setVisible(false);
    }

    @FXML
    private void showFGPerStat(MouseEvent event) {
        explainFGPer.setVisible(true);
    }

    @FXML
    private void hideFGPerStat(MouseEvent event) {
        explainFGPer.setVisible(false);
    }

    @FXML
    private void show3PMStat(MouseEvent event) {
        explain3PM.setVisible(true);
    }

    @FXML
    private void hide3PMStat(MouseEvent event) {
        explain3PM.setVisible(false);
    }

    @FXML
    private void show3PAStat(MouseEvent event) {
        explain3PA.setVisible(true);
    }

    @FXML
    private void hide3PAStat(MouseEvent event) {
        explain3PA.setVisible(false);
    }

    @FXML
    private void show3PPerStat(MouseEvent event) {
        explain3PPer.setVisible(true);
    }

    @FXML
    private void hide3PPerStat(MouseEvent event) {
        explain3PPer.setVisible(false);
    }

    @FXML
    private void show2PMStat(MouseEvent event) {
        explain2PM.setVisible(true);
    }

    @FXML
    private void hide2PMStat(MouseEvent event) {
        explain2PM.setVisible(false);
    }

    @FXML
    private void show2PAStat(MouseEvent event) {
        explain2PA.setVisible(true);
    }

    @FXML
    private void hide2PAStat(MouseEvent event) {
        explain2PA.setVisible(false);
    }

    @FXML
    private void show2PPerStat(MouseEvent event) {
        explain2PPer.setVisible(true);
    }

    @FXML
    private void hide2PPerStat(MouseEvent event) {
        explain2PPer.setVisible(false);
    }

    @FXML
    private void showEFGStat(MouseEvent event) {
        explainEFG.setVisible(true);
    }

    @FXML
    private void hideEFGStat(MouseEvent event) {
        explainEFG.setVisible(false);
    }

    @FXML
    private void showFTMStat(MouseEvent event) {
        explainFTM.setVisible(true);
    }

    @FXML
    private void hideFTMStat(MouseEvent event) {
        explainFTM.setVisible(false);
    }

    @FXML
    private void showFTAStat(MouseEvent event) {
        explainFTA.setVisible(true);
    }

    @FXML
    private void hideFTAStat(MouseEvent event) {
        explainFTA.setVisible(false);
    }

    @FXML
    private void showFTPerStat(MouseEvent event) {
        explainFTPer.setVisible(true);
    }

    @FXML
    private void hideFTPerStat(MouseEvent event) {
        explainFTPer.setVisible(false);
    }

    @FXML
    private void showORBStat(MouseEvent event) {
        explainORB.setVisible(true);
    }

    @FXML
    private void hideORBStat(MouseEvent event) {
        explainORB.setVisible(false);
    }

    @FXML
    private void showDRBStat(MouseEvent event) {
        explainDRB.setVisible(true);
    }

    @FXML
    private void hideDRBStat(MouseEvent event) {
        explainDRB.setVisible(false);
    }

    @FXML
    private void showTRBStat(MouseEvent event) {
        explainTRB.setVisible(true);
    }

    @FXML
    private void hideTRBStat(MouseEvent event) {
        explainTRB.setVisible(false);
    }

    @FXML
    private void showASTStat(MouseEvent event) {
        explainAST.setVisible(true);
    }

    @FXML
    private void hideASTStat(MouseEvent event) {
        explainAST.setVisible(false);
    }

    @FXML
    private void showSTLStat(MouseEvent event) {
        explainSTL.setVisible(true);
    }

    @FXML
    private void hideSTLStat(MouseEvent event) {
        explainSTL.setVisible(false);
    }

    @FXML
    private void showBLKStat(MouseEvent event) {
        explainBLK.setVisible(true);
    }

    @FXML
    private void hideBLKStat(MouseEvent event) {
        explainBLK.setVisible(false);
    }

    @FXML
    private void showTOVStat(MouseEvent event) {
        explainTOV.setVisible(true);
    }

    @FXML
    private void hideTOVStat(MouseEvent event) {
        explainTOV.setVisible(false);
    }

    @FXML
    private void showPFStat(MouseEvent event) {
        explainPF.setVisible(true);
    }

    @FXML
    private void hidePFStat(MouseEvent event) {
        explainPF.setVisible(false);
    }

    @FXML
    private void showPTSStat(MouseEvent event) {
        explainPTS.setVisible(true);
    }

    @FXML
    private void hidePTSStat(MouseEvent event) {
        explainPTS.setVisible(false);
    }

}
