module com.example.miniprojetfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.rayen.miniprojetfx to javafx.fxml;
    exports com.rayen.miniprojetfx;
}