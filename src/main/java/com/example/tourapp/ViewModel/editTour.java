package com.example.tourapp.ViewModel;

import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.Tour;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class editTour {

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

    private int id;

    public void initialize() {
        transportType.setItems(FXCollections.observableArrayList("Bus", "Airline", "Yacht", "Walk", "Car"));
    }

    public void setId(int id) {
        this.id = id;
        Tour t = SingletonSystem.getInstance().getTour(id);
        name.setText(t.getName());
        description.setText(t.getDescription());
        from.setText(t.getFrom());
        to.setText(t.getTo());
        transportType.setValue(t.getTransportType());
        tourDistance.setText(String.valueOf(t.getTourDistance()));
        estimatedTime.setText(t.getEstimatedTime());
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

    public void save(){
        String response = SingletonSystem.getInstance().updateTour(
                id,
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
