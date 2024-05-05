package com.example.tourapp.ViewModel;

import com.example.tourapp.Model.DataFetchService;
import com.example.tourapp.TourApp;
import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.Tour;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class viewTour {

    @FXML
    private BorderPane rootPane;

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label from;

    @FXML
    private Label to;

    @FXML
    private Label transportType;

    @FXML
    private Label tourDistance;

    @FXML
    private Label estimatedTime;

    @FXML
    private ImageView frommapImage;

    @FXML
    private ImageView tomapImage;

    private int id;

    public void initialize() {

    }



    public void loadImage(String from, String to){
        try {
            DataFetchService dfs = new DataFetchService();

            String jsonString = dfs.getLatLonJSON(from).toString();
            String fromlat = dfs.getLat(jsonString);
            String fromlon = dfs.getLon(jsonString);

            jsonString = dfs.getLatLonJSON(to).toString();
            String tolat = dfs.getLat(jsonString);
            String tolon = dfs.getLon(jsonString);

            Image image = dfs.getImage(fromlat, fromlon);
            frommapImage.setImage(image);

            Image image1 = dfs.getImage(tolat, tolon);
            tomapImage.setImage(image1);

            String respose = dfs.getNodesInformation(fromlat, fromlon, tolat, tolon).toString();
            double distance = dfs.parseDistance(respose);
            double duration = dfs.parseDuration(respose);

            tourDistance.setText(String.valueOf(distance));
            estimatedTime.setText(String.valueOf(duration));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setId(int id) {
        this.id = id;
        Tour t = SingletonSystem.getInstance().getTour(id);
        name.setText(t.getName());
        description.setText(t.getDescription());
        from.setText(t.getFrom());
        to.setText(t.getTo());
        transportType.setText(t.getTransportType());

        estimatedTime.setText(t.getEstimatedTime());

        loadImage(t.getFrom(), t.getTo());
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

    public void showReport(){
        try {
            List<Tour> dataList = new ArrayList<>();
            Tour t = SingletonSystem.getInstance().getTour(id);
            dataList.add(t);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
            JasperPrint jasperPrint = JasperFillManager.fillReport(TourApp.class.getResourceAsStream("View/tourReport.jasper"), null, dataSource);
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
