package UI;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ResultPanel extends VBox {

    private final TableView<AnimalRow> table;
    private final Label statusLabel;

    private final Button searchButton;
    private final Button countButton;
    private final Button saveButton;

    public ResultPanel() {

        super(15);

        setPadding(new Insets(15));
        setPrefWidth(520);

        // Заголовок дополнительных операций.
        Label heading =
                new Label("3. Дополнительные операции");

        heading.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold;"
        );

        // Создаём кнопки дополнительных операций.
        searchButton = new Button("Найти элемент");

        countButton = new Button(
                "Подсчитать вхождения (многопоточно)"
        );

        saveButton = new Button(
                "Сохранить результат в файл"
        );

        searchButton.setMaxWidth(Double.MAX_VALUE);
        countButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setMaxWidth(Double.MAX_VALUE);

        VBox actionsBox = new VBox(
                8,
                heading,
                searchButton,
                countButton,
                saveButton
        );

        // Заголовок таблицы.
        Label tableHeading = new Label("4. Результат");

        // Создаём таблицу.
        table = new TableView<>();

        // Первый столбец.
        TableColumn<AnimalRow, String> nameColumn =
                new TableColumn<>("Имя");
        nameColumn.setSortable(false);

        nameColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().name()
                )
        );

        // Второй столбец.
        TableColumn<AnimalRow, Integer> ageColumn =
                new TableColumn<>("Возраст");
        ageColumn.setSortable(false);

        ageColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleIntegerProperty(
                        data.getValue().age()
                ).asObject()
        );

        // Третий столбец — вес животного.
        TableColumn<AnimalRow, Double> weightColumn =
                new TableColumn<>("Вес");

        weightColumn.setCellValueFactory(data ->
                new javafx.beans.property.SimpleDoubleProperty(
                        data.getValue().weight()
                ).asObject()
        );

        weightColumn.setSortable(false);

        // Добавляем столбцы в таблицу.
        table.getColumns().addAll(
                nameColumn,
                ageColumn,
                weightColumn
        );

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY
        );

        // Информационная надпись.
        statusLabel = new Label(
                "Ожидание действий пользователя."
        );

        statusLabel.setWrapText(true);

        // Кнопка выхода.
        Button exitButton = new Button("Выход из программы");

        exitButton.setMaxWidth(Double.MAX_VALUE);

        exitButton.setOnAction(event ->
                javafx.application.Platform.exit()
        );

        getChildren().addAll(
                actionsBox,
                tableHeading,
                table,
                statusLabel,
                exitButton
        );
    }

    // Заполняем таблицу полученными объектами.
    public void setRows(AnimalRow... rows) {

        table.getItems().setAll(rows);
    }

    // Изменяем информационное сообщение.
    public void setStatus(String message) {

        statusLabel.setText(message);
    }

    // Назначаем действие поиска.
    public void setOnSearch(Runnable action) {

        searchButton.setOnAction(event -> action.run());
    }

    // Назначаем действие подсчёта.
    public void setOnCount(Runnable action) {

        countButton.setOnAction(event -> action.run());
    }

    // Назначаем действие сохранения.
    public void setOnSave(Runnable action) {

        saveButton.setOnAction(event -> action.run());
    }

    public void clearRows() {
        table.getItems().clear();
    }
}