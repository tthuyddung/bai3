module com.example.bai3_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.bai3_java to javafx.fxml;
    exports com.example.bai3_java;
}