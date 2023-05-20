module com.example.miniprojetfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.rayen.miniprojetfx to javafx.fxml;
    exports com.rayen.miniprojetfx;
}