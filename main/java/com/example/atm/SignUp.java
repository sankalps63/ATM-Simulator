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
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignUp {
    private Stage stage;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label incPass;
    @FXML
    private Label incPin;
    @FXML
    private Label incUser;
    @FXML
    private TextField nameField;
    @FXML
    private TextField passField;
    @FXML
    private TextField pinField;
    @FXML
    private TextField userField;
    @FXML
    private Button SLbut;

    @FXML
    public void signMe() throws IOException {
        if(!SLbut.getText().equals("Login to proceed")){
            sign();
        }else{
            logSwitch();
        }
    }

    public void sign(){
        String name = nameField.getText().trim();
        boolean passCheck = passField.getText().trim().length() >= 8;
        boolean pinCheck = pinField.getText().trim().length() == 4;
        boolean userCheck;
        try{
            Connection conn = DBConnection.getConn();
            PreparedStatement pstmt = conn.prepareStatement("select username from login where username = ?");
            pstmt.setString(1, userField.getText().trim());
            ResultSet set = pstmt.executeQuery();
            userCheck = !set.next();

            incPass.setVisible(!passCheck);
            incPin.setVisible(!pinCheck);
            incUser.setVisible(!userCheck);

            if(passCheck && pinCheck && userCheck){
                incUser.setVisible(false);
                incPin.setVisible(false);
                incPass.setVisible(false);
                PreparedStatement pstmt2 = conn.prepareStatement("insert into login values (?, ?, ?, ?, 0)");
                pstmt2.setString(1, userField.getText().trim());
                pstmt2.setString(2, passField.getText().trim());
                pstmt2.setInt(3, Integer.parseInt(pinField.getText().trim()));
                pstmt2.setString(4, name);
                pstmt2.executeUpdate();
                SLbut.setText("Login to proceed");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void logSwitch() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Login cont = fxmlLoader.getController();
        cont.setStage(stage);
        stage.setScene(scene);
    }
}
