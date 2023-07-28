package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class BalanceEnq {
    private Stage stage;
    private UserInfo userinfo = new UserInfo();
    int count = 4;
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private TextField pinField;
    @FXML
    private Label attempt;
    @FXML
    private Label incWarn;
    @FXML
    private Label welcomeText = new Label();
    @FXML
    private Button dashBut;
    @FXML
    private Button helpBut;
    @FXML
    private Button balanceBut;

    @FXML
    public void initialize(){
        welcomeText.setText(userinfo.getName() + ", please enter your 4 digit pin below to get your available account balance.");
    }

    @FXML
    public void showBalance(){
        if(pinField.getText().trim().equals(userinfo.getPin())){
            incWarn.setText(Integer.toString(userinfo.getBalance()));
            incWarn.setStyle("-fx-text-fill: green; -fx-font-size:30px");
            incWarn.setVisible(true);
            pinField.setVisible(false);
        }else {
            count--;
            incWarn.setVisible(true);
            attempt.setText("Attempts Remaining: " + count);
            attempt.setVisible(true);
            if(count == 0){
                balanceBut.setDisable(true);
                dashBut.setDisable(true);
            }
        }
    }
    @FXML
    public void gtDash() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Dash cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }

    @FXML
    public void helpBut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Help cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        stage.setScene(scene);
    }
}
