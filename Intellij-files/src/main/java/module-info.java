module com.lineupcreator.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lineupcreator.demo to javafx.fxml;
    exports com.lineupcreator.demo;
}