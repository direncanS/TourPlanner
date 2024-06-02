package com.example.tourapp.ViewModel;

import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.TourLog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class viewTourLog {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label difficulty;

    @FXML
    private Label tour;

    @FXML
    private Label totalTime;

    @FXML
    private Label totalDistance;

    @FXML
    private Label dateTime;

    @FXML
    private Label rating;

    @FXML
    private Label comment;

    public void initialize() {
    }

    public void setId(int id) {
        TourLog t = SingletonSystem.getInstance().getTourLog(id);
        difficulty.setText(t.getDifficulty());
        tour.setText(SingletonSystem.getInstance().getTour(t.getTourId()).getName());
        totalTime.setText(t.getTotalTime());
        totalDistance.setText(String.valueOf(t.getTotalDistance()));
        dateTime.setText(t.getDatetime().toString());
        rating.setText(String.valueOf(t.getRating()));
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




}
