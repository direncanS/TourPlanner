module com.example.tourapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires jakarta.persistence;
    requires jasperreports;
    requires org.apache.logging.log4j;


    opens com.example.tourapp to javafx.fxml;
    exports com.example.tourapp;
    exports com.example.tourapp.ViewModel;
    opens com.example.tourapp.ViewModel to javafx.fxml;

    exports com.example.tourapp.Model;
}