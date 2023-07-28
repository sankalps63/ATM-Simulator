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
import java.sql.SQLException;
import java.sql.Statement;

public class Withdrawal {
    private Stage stage;
    private UserInfo userinfo = new UserInfo();
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private TextField amount;

    @FXML
    private Label attempt;
    int count = 4;
    @FXML
    private Button dashBut;

    @FXML
    private Button helpBut;

    @FXML
    private Label hundreds;

    @FXML
    private Label incWarn;

    @FXML
    private TextField pinField;

    @FXML
    private Label welcomeText;

    @FXML
    private Button withdrawBut;

    @FXML
    public void initialize(){
        welcomeText.setText(userinfo.getName() + ", please enter amount in multiple of Hundreds to withdraw");
    }

    @FXML
    public void withdraw(){
        int value = Integer.parseInt(amount.getText().trim());
        boolean checkHun = value%100 == 0 && value > 0;
        String pin = pinField.getText().trim();
        if(!pin.equals(userinfo.getPin())){
            count--;
            incWarn.setVisible(true);
            attempt.setText("Attempts Remaining: " + count);
            attempt.setVisible(true);
        }else{
            incWarn.setVisible(false);
            attempt.setVisible(false);
            count = 4;
        }
        if(value <= userinfo.getBalance()){
            if(!checkHun){
                hundreds.setText("Please enter amount in the multiple of Hundreds.");
                hundreds.setVisible(true);
            }else {
                hundreds.setVisible(false);
            }

            if(checkHun && pin.equals(userinfo.getPin())){
                userinfo.setBalance(userinfo.getBalance() - value);
                hundreds.setText("You have successfully withdrawn " + value + " Rs.");
                hundreds.setStyle("-fx-text-fill: green; -fx-font-size:24px");
                hundreds.setVisible(true);
                pinField.setVisible(false);
                amount.setVisible(false);
                incWarn.setText("Your available balance is " + userinfo.getBalance() + " Rs.");
                incWarn.setStyle("-fx-text-fill: green;  -fx-font-size:24px");
                incWarn.setVisible(true);
                withdrawBut.setDisable(true);

                try{
                    Connection conn = DBConnection.getConn();
                    PreparedStatement pstmt = conn.prepareStatement("update login set Balance = ? where username = ?");
                    pstmt.setInt(1, userinfo.getBalance());
                    pstmt.setString(2, userinfo.getUsername());
                    pstmt.executeUpdate();

                    PreparedStatement pstmt2 = conn.prepareStatement("insert into statement (username, amount, action, avl_bal) values (?, ?, 'Debited', ?)");
                    pstmt2.setString(1, userinfo.getUsername());
                    pstmt2.setInt(2, value);
                    pstmt2.setInt(3, userinfo.getBalance());
                    pstmt2.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }else{
            hundreds.setText("Amount greater than available balance in your account");
            hundreds.setStyle("-fx-text-fill: red");
            hundreds.setVisible(true);
        }
        if(count == 0){
            dashBut.setDisable(true);
            withdrawBut.setDisable(true);
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
