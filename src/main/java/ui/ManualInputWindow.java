//модальное окно для ручного ввода животных

package ui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.util.ArrayList;
import java.util.List;

import model.Animal;

public class ManualInputWindow {

    private final Stage window;

    private final TextField nameField;
    private final TextField ageField;
    private final TextField weightField;

    private final Button addButton;
    private final Button finishButton;

    private final List<Animal> animals = new ArrayList<>();

    private final Label messageLabel;

    public ManualInputWindow(Stage owner) {

        window = new Stage();

        // Делаем окно модальным.
        window.initOwner(owner);
        window.initModality(Modality.WINDOW_MODAL);  //блокирует только окно-владельца

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

        // Метка для ошибок и сообщений об успешном добавлении.
        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMinHeight(32);

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

        //обработчик кнопки "добавить животное"
        addButton.setOnAction(event -> onAddClicked());

        //"Завершить ввод"
        finishButton.setOnAction(event -> window.close());

        // Создаём основную панель.
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

        // Создаём сцену по размерам содержимого.
        Scene scene = new Scene(root);

        window.setScene(scene);
        window.sizeToScene();
        window.setResizable(false);

    }

    //действие при нажатии "добавить животное"
    private void onAddClicked() {
        try {
            int age = Integer.parseInt(ageField.getText().trim());
            double weight = Double.parseDouble(weightField.getText().trim().replace(',', '.'));

            Animal animal = new Animal.Builder()
                    .setName(nameField.getText().trim())
                    .setAge(age)
                    .setWeight(weight)
                    .build();

            animals.add(animal);

            //очистка полей для ввода след данных
            nameField.clear();
            ageField.clear();
            weightField.clear();
            nameField.requestFocus();

            showMessage("Добавлено: " + animal.getName() + ". Всего: " + animals.size(), false);

        } catch (NumberFormatException e) {
            showMessage("Возраст должен быть целым числом, вес - числом.", true);
        } catch (IllegalArgumentException e) {
            showMessage(e.getMessage(), true);
        }
    }

    private void showMessage(String text, boolean isError) {
        messageLabel.setStyle(isError ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
        messageLabel.setText(text);
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    // Метод открытия окна.
    public void show() {
        window.showAndWait();
    }
}