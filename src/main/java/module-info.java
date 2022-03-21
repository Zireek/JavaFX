module com.example.juegojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.juegojavafx to javafx.fxml;
    exports com.example.juegojavafx;
}