package com.example.tourapp.ViewModel;

import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.TourLog;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZoneId;

public class editTourLog {

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField difficulty;

    @FXML
    private ChoiceBox tour;

    @FXML
    private TextField totalTime;

    @FXML
    private TextField totalDistance;

    @FXML
    private DatePicker dateTime;

    @FXML
    private ChoiceBox rating;

    @FXML
    private TextArea comment;

    private int id;

    private SingletonSystem system;
    public void initialize() {
        system = SingletonSystem.getInstance();
        tour.setItems(FXCollections.observableArrayList(system.getTourNames()));
        rating.setItems(FXCollections.observableArrayList(1.0, 2.0, 3.0, 4.0, 5.0));
    }

    public void setId(int id) {
        this.id = id;

        TourLog t = SingletonSystem.getInstance().getTourLog(id);
        difficulty.setText(t.getDifficulty());
        tour.setValue(SingletonSystem.getInstance().getTour(t.getTourId()).getName());
        totalTime.setText(t.getTotalTime());
        totalDistance.setText(String.valueOf(t.getTotalDistance()));
        dateTime.setValue(t.getDatetime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        rating.setValue(String.valueOf(t.getRating()));
        comment.setText(t.getComment());
    }

    public void back(){
        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/tourLogList.fxml"));
            Parent summaryRoot = loader.load();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1080 , 620);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveChanges(){
        String response = SingletonSystem.getInstance().updateTourLog(
                id,
                tour.getValue().toString(),
                difficulty.getText(),
                totalTime.getText(),
                totalDistance.getText(),
                dateTime.getValue(),
                rating.getValue().toString(),
                comment.getText()
        );

        if (response.equals("Success")){
            try {
                FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/tourLogList.fxml"));
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
