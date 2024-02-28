module com.example.lab2aditi {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.lab2aditi to javafx.fxml;
    exports com.example.lab2aditi;
}