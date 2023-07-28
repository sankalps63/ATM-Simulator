module com.example.atm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.atm to javafx.fxml;
    exports com.example.atm;
}