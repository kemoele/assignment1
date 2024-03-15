module com.example.kemzaphotos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kemzaphotos to javafx.fxml;
    exports com.example.kemzaphotos;
}