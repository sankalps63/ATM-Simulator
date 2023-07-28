package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Deposit {
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
    private TextField amountField;

    @FXML
    private Label amountLabel;

    @FXML
    private Label attempt;

    @FXML
    private Button avBalBut;

    @FXML
    private Button dashBut;

    @FXML
    private Button depBut;

    @FXML
    private Button helpBut;

    @FXML
    private Label hundredsLabel;

    @FXML
    private Label incWarn;

    @FXML
    private TextField pinField;

    @FXML
    private Label pinLabel;

    @FXML
    private Label welcomeText;
    @FXML
    public void initialize(){
        welcomeText.setText(userinfo.getName() + ", please enter amount in multiple of Hundreds to Deposit");
    }

    @FXML
    public void DepositRun(){
        int value = Integer.parseInt(amountField.getText().trim());
        boolean HunCheck = value%100 == 0 && value>0;
        if(!HunCheck){
            hundredsLabel.setVisible(true);
            hundredsLabel.setStyle("-fx-text-fill: red");
        }else{
            userinfo.setBalance(userinfo.getBalance() + value);
            hundredsLabel.setText("You have successfully deposited " + value + " Rs.");
            hundredsLabel.setStyle("-fx-text-fill: green; -fx-font-size:24px");
            hundredsLabel.setVisible(true);
            amountLabel.setVisible(false);
            depBut.setVisible(false);
            amountField.setVisible(false);
            pinLabel.setVisible(true);
            pinField.setVisible(true);
            avBalBut.setVisible(true);

            try{
                Connection conn = DBConnection.getConn();
                PreparedStatement pstmt = conn.prepareStatement("update login set Balance = ? where username = ?");
                pstmt.setInt(1, userinfo.getBalance());
                pstmt.setString(2, userinfo.getUsername());
                pstmt.executeUpdate();

                PreparedStatement pstmt2 = conn.prepareStatement("insert into statement (username, amount, action, avl_bal) values (?, ?, 'Credited', ?)");
                pstmt2.setString(1, userinfo.getUsername());
                pstmt2.setInt(2, value);
                pstmt2.setInt(3, userinfo.getBalance());
                pstmt2.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void showAvl(){
        String pin = pinField.getText().trim();
        if(!pin.equals(userinfo.getPin())){
            count--;
            incWarn.setVisible(true);
            attempt.setText("Attempts Available: " + count);
            attempt.setVisible(true);
        }else {
            incWarn.setText("Your available balance is " + userinfo.getBalance() + " Rs.");
            attempt.setVisible(false);
            incWarn.setStyle("-fx-text-fill: green; -fx-font-size:24px");
            incWarn.setVisible(true);
            avBalBut.setVisible(false);
        }
        if(count == 0){
            avBalBut.setDisable(true);
        }
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
}
