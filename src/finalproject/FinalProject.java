/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Ryan
 */
public class FinalProject extends Application{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private static final String apiKey = "698741fd9d862b20bf3811a2fcde86a5";
    private static final String urlRequest = "https://api.openweathermap.org/data/2.5/weather?q=";
    
    
    private TextField searchField;
    
    @Override
    public void start(Stage s) throws Exception {
        
        // openweathermap api key
        // 698741fd9d862b20bf3811a2fcde86a5
        
        BorderPane pane = new BorderPane();
        
        
        searchField = new TextField();
        searchField.setAlignment(Pos.CENTER);
        
        
        Button searchButton = new Button("Search");
        searchButton.setAlignment(Pos.CENTER);
        
        searchButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                String searchValue = searchField.getText();
                
                try {
                    
                    URL url = new URL(urlRequest + searchValue + ",id=524901&APPID=" +apiKey);
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    int responseCode = con.getResponseCode();
                    System.out.println("Response code: " + responseCode);
                    
                    BufferedReader in = new BufferedReader(
                     new InputStreamReader(con.getInputStream()));
                    
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    
                    //System.out.println(response);
                    
                    JSONObject myJSON = new JSONObject(response.toString());
                    
                    System.out.println(myJSON);
                    
                    JSONArray weatherData = myJSON.getJSONArray("weather");
                    
                    System.out.println(weatherData);
                    
              
                    
                    
                } catch (MalformedURLException ex) {
                    
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (IOException ex) {
                    
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                    
                } catch (JSONException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        
        HBox searchBox = new HBox(searchField,searchButton);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setSpacing(10);
        searchBox.setPadding(new Insets(20,0,0,0));
        
        
        pane.setTop(searchBox);
        Scene scene = new Scene(pane, 300, 300);
        
        
        s.setScene(scene);
        s.setTitle("Weather App");
        s.show();
    }
    
}
