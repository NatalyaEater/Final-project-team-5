package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class InputPanel extends VBox {

    private final Stage stage;

    private final RadioButton manualRadio;
    private final RadioButton randomRadio;
    private final RadioButton fileRadio;

    private final Spinner<Integer> countSpinner;
    private final TextField filePathField;

    private final VBox dynamicControls;
    private final Button createButton;

    public InputPanel(Stage stage) {

        super(10);

        this.stage = stage;

        setPadding(new Insets(15));
        setPrefWidth(520);

        Label heading = new Label("1. Работа с коллекцией");
        heading.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold;"
        );

        Label methodLabel =
                new Label("Выберите способ заполнения:");

        // Переключатели способов заполнения.
        manualRadio = new RadioButton("Ручной ввод");
        randomRadio = new RadioButton("Случайная генерация");
        fileRadio = new RadioButton("Загрузка из файла");

        ToggleGroup inputGroup = new ToggleGroup();

        manualRadio.setToggleGroup(inputGroup);
        randomRadio.setToggleGroup(inputGroup);
        fileRadio.setToggleGroup(inputGroup);

        // По умолчанию выбран ручной ввод.
        manualRadio.setSelected(true);

        VBox radioBox = new VBox(
                8,
                manualRadio,
                randomRadio,
                fileRadio
        );

        // Элементы управления для случайной генерации.
        Label countLabel =
                new Label("Количество элементов:");

        countSpinner = new Spinner<>(1, 1000, 10);
        countSpinner.setEditable(true);

        HBox countBox = new HBox(
                10,
                countLabel,
                countSpinner
        );

        countBox.setAlignment(Pos.CENTER_LEFT);

        // Элементы управления для загрузки из файла.
        Label pathLabel = new Label("Путь к файлу:");

        filePathField = new TextField();
        filePathField.setPromptText("Выберите файл...");
        filePathField.setEditable(false);

        Button browseButton = new Button("Обзор");

        browseButton.setOnAction(
                event -> openFileChooser()
        );

        HBox fileBox = new HBox(
                8,
                filePathField,
                browseButton
        );

        // Контейнер, содержимое которого будет меняться.
        dynamicControls = new VBox(10);

        // Кнопка создания коллекции.
        createButton = new Button("Создать коллекцию");
        createButton.setMaxWidth(Double.MAX_VALUE);

        getChildren().addAll(
                heading,
                methodLabel,
                radioBox,
                dynamicControls,
                createButton
        );

        setStyle(
                "-fx-border-color: #b5c9e8;" +
                        "-fx-border-radius: 8;" +
                        "-fx-background-radius: 8;"
        );

        // Обновляем элементы при выборе другого способа.
        inputGroup.selectedToggleProperty().addListener(
                (observable, oldValue, newValue) ->
                        updateControls(countBox, pathLabel, fileBox)
        );

        // Показываем элементы для выбранного по умолчанию режима.
        updateControls(countBox, pathLabel, fileBox);
    }

    // Меняет содержимое панели в зависимости от способа заполнения.
    private void updateControls(
            HBox countBox,
            Label pathLabel,
            HBox fileBox
    ) {

        dynamicControls.getChildren().clear();

        if (randomRadio.isSelected()) {

            dynamicControls.getChildren().add(countBox);

        } else if (fileRadio.isSelected()) {

            dynamicControls.getChildren().addAll(
                    pathLabel,
                    fileBox
            );
        }

        // При ручном вводе dynamicControls остаётся пустой.
    }

    // Открывает системный обозреватель файлов.
    private void openFileChooser() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Выберите файл с животными");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Текстовые файлы",
                        "*.txt",
                        "*.csv"
                )
        );

        File selectedFile =
                fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {

            filePathField.setText(
                    selectedFile.getAbsolutePath()
            );
        }
    }

    // Передаёт действие кнопке создания коллекции.
    public void setOnCreate(Runnable action) {

        createButton.setOnAction(
                event -> action.run()
        );
    }

    // Возвращает выбранный способ заполнения.
    public String getInputMethod() {

        if (manualRadio.isSelected()) {
            return "manual";
        }

        if (randomRadio.isSelected()) {
            return "random";
        }

        if (fileRadio.isSelected()) {
            return "file";
        }

        return null;
    }

    // Возвращает количество элементов.
    public int getCount() {
        return countSpinner.getValue();
    }

    // Возвращает путь к файлу.
    public String getFilePath() {
        return filePathField.getText();
    }
}
