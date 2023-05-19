module com.example.miniprojetfx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.rayen.miniprojetfx to javafx.fxml;
    exports com.rayen.miniprojetfx;
}