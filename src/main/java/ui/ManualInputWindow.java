package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public class ManualInputWindow {

    private final Stage window;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField weightField;

    private final Button addButton;
    private final Button finishButton;

    public ManualInputWindow(Stage owner) {

        window = new Stage();

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

        // Кнопки.
        addButton = new Button("Добавить животное");
        finishButton = new Button("Завершить ввод");

        // Устанавливаем размеры кнопок.
        addButton.setPrefSize(160, 40);
        finishButton.setPrefSize(145, 40);

        HBox buttonBox = new HBox(
                10,
                addButton,
                finishButton
        );

        buttonBox.setAlignment(Pos.CENTER);

        // Создаём основную панель.
        VBox root = new VBox(
                12,
                new Label("Имя животного:"),
                nameField,
                new Label("Возраст:"),
                ageField,
                new Label("Вес (кг):"),
                weightField,
                buttonBox
        );

        root.setPadding(new Insets(20));
        root.setPrefWidth(360);

        // Создаём сцену по размерам содержимого.
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);
    }

    // Метод открытия окна.
    public void show() {
        window.showAndWait();
    }
}