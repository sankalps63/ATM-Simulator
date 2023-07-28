package com.example.atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Dash {
    private Stage stage;
    private UserInfo userinfo = new UserInfo();

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Label welcHead;

    @FXML
    public void initialize(){
        welcHead.setText("Welcome " + userinfo.getName());
    }

    @FXML
    public void balanceEnqSwitch() throws IOException {
        System.out.println(userinfo.getName());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BalEnq.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        BalanceEnq cont = fxmlLoader.getController();
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

    @FXML
    public void withBut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Withdrawal cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }

    @FXML
    public void depoBut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deposits.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Deposit cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }

    @FXML
    public void pinCh() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("pinCh.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ChangePin cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }

    @FXML
    public void miniState() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("minStat.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MiniStat cont = fxmlLoader.getController();
        cont.setUserinfo(userinfo);
        cont.setStage(stage);
        cont.initialize();
        stage.setScene(scene);
    }
}
