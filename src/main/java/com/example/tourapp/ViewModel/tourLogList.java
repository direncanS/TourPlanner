package com.example.tourapp.ViewModel;

import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.TourLog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class tourLogList {

    @FXML
    private Pane outerPane;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField search;

    @FXML
    private Button searchBtn;

    private boolean isSearched;

    private SingletonSystem system;

    private ArrayList<TourLog> tourLogs;

    private void updateList(){
        outerPane.getChildren().clear();
        int i = 0;
        for (TourLog t: tourLogs){
            Pane innerPane = new Pane();
            innerPane.setLayoutX(10);
            innerPane.setLayoutY(10 + i * 71);
            innerPane.setPrefSize(1053, 61);
            innerPane.setStyle("-fx-background-color: white; -fx-border-color: #190981; -fx-border-radius: 10px; -fx-background-radius: 10px;");

            Label nameLabel = new Label("Log Of " + system.getTour(t.getTourId()).getName());
            nameLabel.setLayoutX(20);
            nameLabel.setLayoutY(17);
            nameLabel.setTextFill(Color.web("#190981"));
            nameLabel.setFont(new Font(18));

            Button deleteButton = new Button("Delete");
            deleteButton.setLayoutX(943);
            deleteButton.setLayoutY(18);
            deleteButton.setPrefSize(87, 27);
            deleteButton.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-background-radius: 10px; -fx-border-radius: 10px;");
            deleteButton.setTextFill(Color.RED);
            deleteButton.setOnMouseEntered(e->{
                deleteButton.setStyle("-fx-background-color: red; -fx-border-color: red; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                deleteButton.setTextFill(Color.WHITE);
            });
            deleteButton.setOnMouseExited(e->{
                deleteButton.setStyle("-fx-background-color: white; -fx-border-color: red; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                deleteButton.setTextFill(Color.RED);
            });
            deleteButton.setOnMouseClicked(e->{
                system.deleteTourLog(t.getTourLogId());
                updateList();
            });

            Button editButton = new Button("Edit");
            editButton.setLayoutX(861);
            editButton.setLayoutY(18);
            editButton.setPrefSize(77, 27);
            editButton.setStyle("-fx-background-color: white; -fx-border-color: #2c763f; -fx-background-radius: 10px; -fx-border-radius: 10px;");
            editButton.setTextFill(Color.web("#2c763f"));
            editButton.setOnMouseEntered(e->{
                editButton.setStyle("-fx-background-color: #2c763f; -fx-border-color: #2c763f; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                editButton.setTextFill(Color.WHITE);
            });
            editButton.setOnMouseExited(e->{
                editButton.setStyle("-fx-background-color: white; -fx-border-color: #2c763f; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                editButton.setTextFill(Color.web("#2c763f"));
            });
            editButton.setOnMouseClicked(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/editTourLog.fxml"));
                    Parent summaryRoot = loader.load();
                    editTourLog controller = loader.getController();
                    controller.setId(t.getTourLogId());
                    Stage stage = (Stage) rootPane.getScene().getWindow();
                    Scene scene = new Scene(summaryRoot, 1080 , 620);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            Button viewButton = new Button("View");
            viewButton.setLayoutX(769);
            viewButton.setLayoutY(18);
            viewButton.setPrefSize(86, 27);
            viewButton.setStyle("-fx-background-color: white; -fx-border-color: #190981; -fx-background-radius: 10px; -fx-border-radius: 10px;");
            viewButton.setTextFill(Color.web("#190981"));
            viewButton.setOnMouseEntered(e->{
                viewButton.setStyle("-fx-background-color: #190981; -fx-border-color: #190981; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                viewButton.setTextFill(Color.WHITE);
            });
            viewButton.setOnMouseExited(e->{
                viewButton.setStyle("-fx-background-color: white; -fx-border-color: #190981; -fx-background-radius: 10px; -fx-border-radius: 10px;");
                viewButton.setTextFill(Color.web("#190981"));
            });
            viewButton.setOnMouseClicked(e->{
                try {
                    FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/viewTourLog.fxml"));
                    Parent summaryRoot = loader.load();
                    viewTourLog controller = loader.getController();
                    controller.setId(t.getTourLogId()); // Set the id variable
                    Stage stage = (Stage) rootPane.getScene().getWindow();
                    Scene scene = new Scene(summaryRoot, 1080 , 620);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            innerPane.getChildren().addAll(nameLabel, deleteButton, editButton, viewButton);
            this.outerPane.getChildren().addAll(innerPane);
            i++;
        }
    }
    public void initialize() {
        system = SingletonSystem.getInstance();
        isSearched = false;
        tourLogs = system.getAllTourLogs();
        updateList();
    }

    public void filterResults(){
        if (isSearched){
            tourLogs = system.getAllTourLogs();
            isSearched = false;
            searchBtn.setText("Search");
            updateList();
        }else{
            if (!(search.getText().isEmpty())){
                tourLogs = system.getTourLogs(search.getText());
                isSearched = true;
                searchBtn.setText("Cancel");
                updateList();
            }
        }
    }

    public void showTours(){
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

    public void addTourLog(){
        try {
            FXMLLoader loader = new FXMLLoader(TourApp.class.getResource("View/createTourLog.fxml"));
            Parent summaryRoot = loader.load();
            Stage stage = (Stage) rootPane.getScene().getWindow();
            Scene scene = new Scene(summaryRoot, 1080, 620);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
