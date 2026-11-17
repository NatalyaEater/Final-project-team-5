package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow {

    private final Stage stage;

    private final InputPanel inputPanel;
    private final SortingPanel sortingPanel;
    private final ResultPanel resultPanel;

    public MainWindow(Stage stage) {

        this.stage = stage;

        // Создаём отдельные панели интерфейса.
        inputPanel = new InputPanel(stage);
        sortingPanel = new SortingPanel();
        resultPanel = new ResultPanel();

        // Собираем главное окно.
        BorderPane root = new BorderPane();

        // Левая часть окна: ввод и сортировка.
        javafx.scene.layout.VBox leftPanel =
                new javafx.scene.layout.VBox(15);

        leftPanel.getChildren().addAll(
                inputPanel,
                sortingPanel
        );

        // Правая часть окна: дополнительные операции и таблица.
        root.setLeft(leftPanel);
        root.setRight(resultPanel);

        BorderPane.setMargin(leftPanel, new Insets(15));
        BorderPane.setMargin(resultPanel, new Insets(15));

        // Создаём сцену.
        Scene scene = new Scene(root, 1100, 720);

        stage.setTitle("Animal Sorter");
        stage.setScene(scene);

        stage.setMinWidth(900);
        stage.setMinHeight(600);

        // Подключаем обработчики действий пользователя.
        configureActions();
    }

    // Связывает кнопки интерфейса с действиями программы.
    private void configureActions() {

        // Создание коллекции.
        inputPanel.setOnCreate(() -> {
            if (inputPanel.getInputMethod().equals("manual")) {
                ManualInputWindow manualWindow =
                        new ManualInputWindow(stage);

                manualWindow.show();
            }
        });

        // Сортировка коллекции.
        sortingPanel.setOnSort(() -> sortCollection());

        // Дополнительные операции.
        resultPanel.setOnSearch(() ->
                resultPanel.setStatus("Поиск пока не подключён.")
        );

        resultPanel.setOnCount(() ->
                resultPanel.setStatus("Многопоточный подсчёт пока не подключён.")
        );

        resultPanel.setOnSave(() ->
                resultPanel.setStatus("Сохранение пока не подключено.")
        );
    }

    // Подготовка пустой коллекции для заполнения.
    private void createCollection() {

        resultPanel.clearRows();

        resultPanel.setStatus(
                "Коллекция подготовлена. Добавьте животных."
        );
    }

    // Временная демонстрация сортировки.
    private void sortCollection() {

        String algorithm = sortingPanel.getAlgorithm();
        String field = sortingPanel.getField();

        resultPanel.setStatus(
                "Алгоритм: " + algorithm +
                        ". Поле: " + field +
                        ". Сортировка пока не подключена."
        );
    }

    // Показывает главное окно.
    public void show() {
        stage.show();
    }
}