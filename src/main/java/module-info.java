module com.prosta_apka {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.prosta_apka to javafx.fxml;
    exports com.prosta_apka;
}