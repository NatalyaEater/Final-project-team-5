package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Animal;

import java.util.Optional;

// Модальное окно для ввода одного животного (для поиска и подсчёта).
public class AnimalQueryWindow {

    private final Stage window;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField weightField;
    private final Label messageLabel;

    // Введённое животное; null, если окно закрыли без подтверждения.
    private Animal result;

    public AnimalQueryWindow(Stage owner, String title, String okText) {

        window = new Stage();
        window.initOwner(owner);
        window.initModality(Modality.WINDOW_MODAL);
        window.setTitle(title);

        nameField = new TextField();
        nameField.setPromptText("Введите имя животного");

        ageField = new TextField();
        ageField.setPromptText("Введите возраст");

        weightField = new TextField();
        weightField.setPromptText("Введите вес (кг)");

        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMinHeight(32);
        messageLabel.setStyle("-fx-text-fill: red;");

        Button okButton = new Button(okText);
        Button cancelButton = new Button("Отмена");

        okButton.setPrefSize(160, 40);
        cancelButton.setPrefSize(145, 40);

        okButton.setDefaultButton(true);
        cancelButton.setCancelButton(true);

        okButton.setOnAction(event -> onOkClicked());
        cancelButton.setOnAction(event -> window.close());

        HBox buttonBox = new HBox(10, okButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(
                12,
                new Label("Имя животного:"),
                nameField,
                new Label("Возраст:"),
                ageField,
                new Label("Вес (кг):"),
                weightField,
                messageLabel,
                buttonBox
        );

        root.setPadding(new Insets(20));
        root.setPrefWidth(360);

        window.setScene(new Scene(root));
        window.sizeToScene();
        window.setResizable(false);
    }

    // Проверяет ввод и создаёт животное.
    private void onOkClicked() {

        try {
            int age = Integer.parseInt(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim().replace(',', '.'));

            result = new Animal.Builder()
                    .setName(nameField.getText().trim())
                    .setAge(age)
                    .setWeight(weight)
                    .build();

            window.close();

        } catch (NumberFormatException e) {
            messageLabel.setText("Возраст должен быть целым числом, вес - числом.");

        } catch (IllegalArgumentException e) {
            messageLabel.setText(e.getMessage());
        }
    }

    // Открывает окно и возвращает введённое животное (пусто, если нажали «Отмена»).
    public Optional<Animal> showAndGet() {

        result = null;
        window.showAndWait();

        return Optional.ofNullable(result);
    }
}