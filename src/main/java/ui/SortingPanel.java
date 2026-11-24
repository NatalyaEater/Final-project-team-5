//Операции с коллекцией

package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SortingPanel extends VBox {

    private final ComboBox<String> algorithmComboBox;
    private final ComboBox<String> fieldComboBox;

    private final Button sortButton;

    public SortingPanel() {

        super(10);

        setPadding(new Insets(15));
        setPrefWidth(520);

        Label heading =
                new Label("2. Операции с коллекцией");

        heading.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold;"
        );

        // Выбор алгоритма сортировки.
        Label algorithmLabel =
                new Label("Алгоритм сортировки:");

        algorithmComboBox = new ComboBox<>();

        algorithmComboBox.getItems().addAll(
                "Пузырьковая сортировка",
                "Сортировка выбором",
                "Сортировка вставками"
        );

        algorithmComboBox.setValue(
                "Пузырьковая сортировка"
        );

        algorithmComboBox.setMaxWidth(Double.MAX_VALUE);

        // Выбор поля сортировки.
        Label fieldLabel =
                new Label("Поле сортировки:");

        fieldComboBox = new ComboBox<>();

        fieldComboBox.getItems().addAll(
                "Все поля",
                "Имя",
                "Возраст",
                "Вес",
                "Четная сортировка по возрасту"
        );

        fieldComboBox.setValue("Все поля");

        fieldComboBox.setMaxWidth(Double.MAX_VALUE);

        // Кнопка запуска сортировки.
        sortButton = new Button("Выполнить сортировку");
        sortButton.setMaxWidth(Double.MAX_VALUE);

        getChildren().addAll(
                heading,
                algorithmLabel,
                algorithmComboBox,
                fieldLabel,
                fieldComboBox,
                sortButton
        );

        setStyle(
                "-fx-border-color: #b5c9e8;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
        );
    }

    // Передаём действие кнопке сортировки.
    public void setOnSort(Runnable action) {
        sortButton.setOnAction(event -> action.run());
    }

    // Возвращаем выбранный алгоритм.
    public String getAlgorithm() {
        return algorithmComboBox.getValue();
    }

    // Возвращаем выбранное поле.
    public String getField() {
        return fieldComboBox.getValue();
    }
}
