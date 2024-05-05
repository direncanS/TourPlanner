package com.example.tourapp.ViewModel;

import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class createTour {

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField name;

    @FXML
    private TextArea description;

    @FXML
    private TextField from;

    @FXML
    private TextField to;

    @FXML
    private ChoiceBox transportType;

    @FXML
    private TextField tourDistance;

    @FXML
    private TextField estimatedTime;

    public void initialize() {
        transportType.setItems(FXCollections.observableArrayList("Bus", "Car"));
    }

    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/tourList.fxml"));
            Parent summaryRoot = loader.load();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1080 , 620);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void add(){
        String response = SingletonSystem.getInstance().addTour(
                name.getText(),
                description.getText(),
                from.getText(),
                to.getText(),
                (String) transportType.getValue(),
                tourDistance.getText(),
                estimatedTime.getText()
        );

        if (response.equals("Success")){
            try {
                FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/tourList.fxml"));
                Parent summaryRoot = loader.load();
                Stage stage = (Stage) rootPane.getScene().getWindow();
                Scene scene = new Scene(summaryRoot, 1080 , 620);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(response);
            alert.showAndWait();
        }


    }
}
