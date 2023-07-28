package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    Stage stage;

    public UserInfo userCred;

    public void setStage(Stage stage){
        this.stage = stage;
    }
    @FXML
    private Label incorrect;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void loginButton(){
        String userInput = usernameField.getText().trim();
        String passInput = passwordField.getText().trim();

        try{
            Connection conn = DBConnection.getConn();
            PreparedStatement pstmt = conn.prepareStatement("select * from login where username = ? and password = ?");
            pstmt.setString(1,userInput);
            pstmt.setString(2,passInput);
            ResultSet set = pstmt.executeQuery();
            if(!set.next()){
                System.out.println("No Match Found");
                incorrect.setVisible(true);
            }else{
                System.out.println("Logged In!");
                incorrect.setText("Logged In!");
                incorrect.setStyle("-fx-text-fill: green;");
                incorrect.setVisible(true);
                userCred = new UserInfo(set.getString("username"), set.getString("password"), set.getString("pin"),
                        set.getString("Name"), set.getInt("balance"));
                System.out.println(set.getString("balance"));
                //Changing view if login credentials are correct↔️
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Dash cont = fxmlLoader.getController();
                cont.setUserinfo(userCred);
                cont.setStage(stage);
                cont.initialize();
                stage.setScene(scene);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void signButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SignUp cont = fxmlLoader.getController();
        cont.setStage(stage);
        stage.setScene(scene);
    }
}
