module com.example.chessapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens mainPackage to javafx.fxml;

    exports mainPackage.running;
    opens mainPackage.running to javafx.fxml;
}