package com.example.tourapp.Model;

import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DataFetchService {

    public StringBuilder getLatLonJSON(String Location) throws IOException {
        // Get Latitude And Longitude
        String apiKey = "6630ff64aab3d984889404kco48f0d0";
        String urlString = "https://geocode.maps.co/search?q=" + Location + "&api_key=" + apiKey;

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();

        return response;
    }

    private String getImageStringUrl(String lat, String lon){
        String API_KEY = "210e066ea0aa45e99c744e7e7d8a8dd6";
        return "https://maps.geoapify.com/v1/staticmap?style=osm-carto&width=600&height=400&center=lonlat:" + lon + "," + lat + "&zoom=10.3&apiKey=" + API_KEY;
    }

    public Image getImage(String lat, String lon) throws IOException {
        URL url = new URL(getImageStringUrl(lat, lon));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream inputStream = connection.getInputStream();
        Image image = new Image(inputStream);
        inputStream.close();
        connection.disconnect();
        return image;
    }

    private String parseValue(String jsonString, String key) {
        int index = jsonString.indexOf(key);
        if (index == -1) return null;
        int startIndex = index + key.length() + 1;
        int endIndex = jsonString.indexOf(",", startIndex);
        if (endIndex == -1) endIndex = jsonString.indexOf("}", startIndex);
        return jsonString.substring(startIndex, endIndex).replaceAll("\"", "");
    }

    public String getLat(String jsonString){
        return parseValue(jsonString, "\"lat\":");
    }

    public String getLon(String jsonString){
        return parseValue(jsonString, "\"lon\":");
    }

    public StringBuilder getNodesInformation(String fromlat, String fromlon, String tolat, String tolon) throws IOException {
        String urlString = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf624812c1ca3fac6941bc957a4f2488c63d9a&start=" + fromlon + "," + fromlat + "&end=" + tolon + "," + tolat;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        conn.disconnect();
        return response;
    }

    public double parseDistance(String json) {
        int startIndex = json.indexOf("\"distance\":") + "\"distance\":".length();
        int endIndex = json.indexOf(",", startIndex);
        String distanceString = json.substring(startIndex, endIndex);
        return Double.parseDouble(distanceString.trim());
    }

    public double parseDuration(String json) {
        int startIndex = json.indexOf("\"duration\":") + "\"duration\":".length();
        int endIndex = json.indexOf(",", startIndex);
        String durationString = json.substring(startIndex, endIndex);
        return Double.parseDouble(durationString.trim());
    }
}
