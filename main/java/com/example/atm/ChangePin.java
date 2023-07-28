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

public class ChangePin {
    private Stage stage;
    private UserInfo userinfo = new UserInfo();
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private Label incPass;
    @FXML
    private Label incPin;
    @FXML
    private TextField passField;
    @FXML
    private TextField pinField;
    @FXML
    private Label passLabel;
    @FXML
    private Button pinBut;
    @FXML
    private Label pinLabel;
    @FXML
    private Label welcomeText;


    @FXML
    public void initialize(){
        welcomeText.setText("Welcome " + userinfo.getName() + ", Enter fields below to change transaction pin");
    }

    @FXML
    public void chaPin(){
        String pass = passField.getText().trim();
        String pin = pinField.getText().trim();
        if(!pass.equals(userinfo.getPassword())){
            incPass.setVisible(true);
        }else{
            incPass.setVisible(false);
        }
        if(pin.length() != 4){
            incPin.setVisible(true);
        }else {
            incPin.setVisible(false);
        }
        if(pin.length() == 4 && pass.equals(userinfo.getPassword())){
            userinfo.setPin(pin);
            passLabel.setVisible(false);
            passField.setVisible(false);
            pinLabel.setVisible(false);
            pinField.setVisible(false);
            incPin.setVisible(false);
            incPass.setText("Your pin has been changed to " + pin);
            incPass.setStyle("-fx-text-fill: green; -fx-font-size: 24px");
            incPass.setVisible(true);
            pinBut.setDisable(true);

            try{
                Connection conn = DBConnection.getConn();
                PreparedStatement pstmt = conn.prepareStatement("update login set pin = ? where username = ?");
                pstmt.setInt(1, Integer.parseInt(pin));
                pstmt.setString(2, userinfo.getUsername());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
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
}
