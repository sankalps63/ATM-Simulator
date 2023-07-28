package com.example.atm;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MiniStat {

    @FXML
    private TableView<UserStat> tv;

    @FXML
    private TableColumn<UserStat, String> act;

    @FXML
    private TableColumn<UserStat, Integer> amo;

    @FXML
    private TableColumn<UserStat, Integer> avlBal;

    @FXML
    private TableColumn<UserStat, String> dat;

    @FXML
    private Label incWarn;
    @FXML
    private TextField passField;
    private Stage stage;
    private UserInfo userinfo = new UserInfo();
    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    ArrayList<UserStat> tableRows = new ArrayList<>();

    @FXML
    public void initialize(){
        act.setCellValueFactory(new PropertyValueFactory<>("Action"));
        amo.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        avlBal.setCellValueFactory(new PropertyValueFactory<>("AvlBal"));
        dat.setCellValueFactory(new PropertyValueFactory<>("DAT"));
        tv.setItems(FXCollections.observableArrayList(tableRows));
    }

    @FXML
    public void view(){
        if(passField.getText().trim().equals(userinfo.getPassword())){
            incWarn.setVisible(false);
            try{
                Connection conn = DBConnection.getConn();
                PreparedStatement pstmt = conn.prepareStatement("select date_time, amount, action, avl_bal from statement where username = ? order by date_time desc");
                pstmt.setString(1, userinfo.getUsername());
                ResultSet set = pstmt.executeQuery();
                while(set.next()){
                    UserStat u = new UserStat(set.getString("date_time"), set.getInt("amount"), set.getString("action"), set.getInt("avl_bal"));
                    tableRows.add(u);
                    tv.getItems().add(u);
                    tv.refresh();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            incWarn.setVisible(true);
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
