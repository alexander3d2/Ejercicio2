package com.Ejercicio2.Airlines;


import com.Ejercicio2.Airlines.Service.FlightService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlightApp extends Application {

    private TextArea flightInfoArea;
    private TextField airlineCodeField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Control de Programación de Vuelos");

        airlineCodeField = new TextField();
        airlineCodeField.setPromptText("Ingrese el código de la aerolínea");

        Button fetchScheduleButton = new Button("Obtener Programación de Vuelos");
        flightInfoArea = new TextArea();
        flightInfoArea.setEditable(false);

        fetchScheduleButton.setOnAction(event -> fetchFlightSchedule());

        VBox vbox = new VBox(10, airlineCodeField, fetchScheduleButton, flightInfoArea);
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void fetchFlightSchedule() {
        String airlineCode = airlineCodeField.getText();
        if (airlineCode.isEmpty()) {
            flightInfoArea.setText("Por favor ingrese un código de aerolínea.");
            return;
        }

        FlightService flightService = new FlightService();
        String schedule = flightService.getFlightSchedule(airlineCode);
        flightInfoArea.setText(schedule);
    }
}
