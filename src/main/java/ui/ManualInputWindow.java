package ui;

import input.ManualInput;
import model.Animal;
import model.MyArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.Modality;

public class ManualInputWindow {

    private final Stage window;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField weightField;

    private final Button addButton;
    private final Button finishButton;

    private final Label messageLabel;

    private final ManualInput manualInput;

    public ManualInputWindow(Stage owner, MyArrayList<Animal> animals) {

        window = new Stage();

        // Создаём обработчик ручного добавления.
        manualInput = new ManualInput(animals);

        // Делаем окно модальным.
        window.initOwner(owner);
        window.initModality(Modality.WINDOW_MODAL);

        window.setTitle("Добавление животных");

        // Поле имени.
        nameField = new TextField();
        nameField.setPromptText("Введите имя животного");

        // Поле возраста.
        ageField = new TextField();
        ageField.setPromptText("Введите возраст");

        // Поле веса.
        weightField = new TextField();
        weightField.setPromptText("Введите вес (кг)");

        // Метка для сообщений.
        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMinHeight(32);

        // Кнопки.
        addButton = new Button("Добавить животное");
        finishButton = new Button("Завершить ввод");

        addButton.setPrefSize(160, 40);
        finishButton.setPrefSize(145, 40);

        HBox buttonBox = new HBox(
                10,
                addButton,
                finishButton
        );

        buttonBox.setAlignment(Pos.CENTER);

        // Обработчики кнопок.
        addButton.setOnAction(event -> onAddClicked());
        finishButton.setOnAction(event -> window.close());

        // Основная панель.
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

        Scene scene = new Scene(root);

        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);
    }

    // Действие при нажатии кнопки добавления.
    private void onAddClicked() {

        String error = manualInput.addAnimal(
                nameField.getText().trim(),
                ageField.getText().trim(),
                weightField.getText().trim().replace(',', '.')
        );

        if (error != null) {
            showMessage(error, true);
            return;
        }

        showMessage("Животное успешно добавлено.", false);

        // Очищаем поля для следующего ввода.
        nameField.clear();
        ageField.clear();
        weightField.clear();

        nameField.requestFocus();
    }

    private void showMessage(String text, boolean isError) {
        messageLabel.setStyle(
                isError
                        ? "-fx-text-fill: red;"
                        : "-fx-text-fill: green;"
        );

        messageLabel.setText(text);
    }

    // Открытие окна.
    public void show() {
        window.showAndWait();
    }
}