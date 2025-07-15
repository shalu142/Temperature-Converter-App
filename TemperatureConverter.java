import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TemperatureConverter extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("🌡 Temperature Converter");

        // ✅ Set App Icon (Top-left of window)
        Image appIcon = new Image("file:icon.png"); // Icon file must be in same folder
        primaryStage.getIcons().add(appIcon);

        // Input label and field
        Label tempLabel = new Label("Enter Temperature:");
        TextField tempField = new TextField();

        // Radio buttons for conversion type
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton cToF = new RadioButton("Celsius to Fahrenheit");
        cToF.setToggleGroup(toggleGroup);
        cToF.setSelected(true);

        RadioButton fToC = new RadioButton("Fahrenheit to Celsius");
        fToC.setToggleGroup(toggleGroup);

        // Convert button
        Button convertButton = new Button("Convert");

        // Icon image next to result
        ImageView iconView = new ImageView(appIcon);
        iconView.setFitWidth(32);
        iconView.setFitHeight(32);

        // Result label
        Label resultLabel = new Label();

        // Handle button click
        convertButton.setOnAction(e -> {
            try {
                double inputTemp = Double.parseDouble(tempField.getText());
                double result;
                if (cToF.isSelected()) {
                    result = (inputTemp * 9 / 5) + 32;
                    resultLabel.setText(String.format("%.2f °C = %.2f °F", inputTemp, result));
                } else {
                    result = (inputTemp - 32) * 5 / 9;
                    resultLabel.setText(String.format("%.2f °F = %.2f °C", inputTemp, result));
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("❌ Please enter a valid number.");
            }
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(tempLabel, tempField, cToF, fToC, convertButton, iconView, resultLabel);

        // Scene and stage
        Scene scene = new Scene(layout, 350, 280);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
