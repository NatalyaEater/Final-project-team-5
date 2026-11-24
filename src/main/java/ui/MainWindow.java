//собирает всё приложение воедино

package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import model.Animal;
import model.MyArrayList;

import java.util.ArrayList;
import java.util.List;

import fill.FileLoader;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import fill.RandomFiller;

import Service.SortService;

import concurrent.MultiThreadTask;

import java.util.Optional;

import file.FileResultWriter;
import javafx.stage.FileChooser;

import AllComparators.AnimalComparators;
import search.BinarySearch;

import java.io.File;

public class MainWindow {

    private final Stage stage;

    private final InputPanel inputPanel;
    private final SortingPanel sortingPanel;
    private final ResultPanel resultPanel;

    private MyArrayList<Animal> animals = new MyArrayList<>();
    private final FileLoader fileLoader = new FileLoader();
    private final RandomFiller randomFiller = new RandomFiller();
    private final FileResultWriter fileWriter = new FileResultWriter();

    private final SortService sortService = new SortService();

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

                animals = new MyArrayList<>();
                for (Animal animal : manualWindow.getAnimals()) {
                    animals.add(animal);
                }
                showAnimals();

            } else if (inputPanel.getInputMethod().equals("file")) {
                loadFromFile();
            } else if (inputPanel.getInputMethod().equals("random")) {
                generateRandom();
            }
        });

        // Сортировка коллекции.
        sortingPanel.setOnSort(() -> sortCollection());

        // Дополнительные операции.
        resultPanel.setOnSearch(() -> findAnimal()
        );

        resultPanel.setOnCount(() -> countAnimals()
        );

        resultPanel.setOnSave(() -> saveAnimals()
        );
    }

    // Подготовка пустой коллекции для заполнения.
    private void createCollection() {

        resultPanel.clearRows();

        resultPanel.setStatus(
                "Коллекция подготовлена. Добавьте животных."
        );
    }

    // Показывает коллекцию в таблице.
    private void showAnimals() {

        List<AnimalRow> rows = new ArrayList<>();

        for (Animal animal : animals) {
            rows.add(new AnimalRow(
                    animal.getName(),
                    animal.getAge(),
                    animal.getWeight()
            ));
        }

        resultPanel.setRows(rows);
        resultPanel.setStatus(
                "В коллекции животных: " + animals.size()
        );
    }

    // Загружает коллекцию из выбранного файла.
    private void loadFromFile() {

        try {
            animals = fileLoader.load(inputPanel.getFilePath());
            showAnimals();

        } catch (MalformedInputException e) {
            showError("Файл должен быть в кодировке UTF-8.");

        } catch (IOException e) {
            showError("Не удалось прочитать файл: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    //Заполняем коллекцию случайным животным
    private void generateRandom() {

        try {
            int count = inputPanel.getCount();

            MyArrayList<Animal> generated = new MyArrayList<>();
            randomFiller.fill(generated, count);
            animals = generated;
            showAnimals();
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    // Сохраняет коллекцию в CSV-файл.
    private void saveAnimals() {

        if (animals.size() == 0) {
            showError("Нет животных для сохранения.");
            return;
        }

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Сохранить коллекцию");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV-файлы", "*.csv")
        );
        fileChooser.setInitialFileName("animals.csv");

        File selectedFile = fileChooser.showSaveDialog(stage);

        // Диалог закрыли без выбора файла.
        if (selectedFile == null) {
            return;
        }

        String fileName = selectedFile.getName();

        // Если расширения нет, добавляем .csv.
        if (!fileName.contains(".")) {
            selectedFile = new File(selectedFile.getParentFile(), fileName + ".csv");

        } else if (!fileName.toLowerCase().endsWith(".csv")) {
            showError("Можно сохранять только CSV-файлы.");
            return;
        }

        try {
            fileWriter.writeAnimals(animals, selectedFile.getAbsolutePath());
            resultPanel.setStatus("Коллекция сохранена: " + selectedFile.getName());

        } catch (IOException | IllegalArgumentException e) {
            showError("Ошибка сохранения: " + e.getMessage());
        }
    }

    // Показывает окно с сообщением об ошибке.
    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // Временная демонстрация сортировки.
    private void sortCollection() {

        if (animals.size() == 0) {
            showError("Коллекция пуста. Сначала создайте коллекцию");
            return;
        }

        try {
            int fieldChoice = getFieldChoice();
            int algorithmChoice = getAlgorithmChoice();
            animals = sortService.sort(animals, fieldChoice, algorithmChoice);

            showAnimals();
            resultPanel.setStatus(
                    "Сортировка выполнена. Алгоритм: " + sortingPanel.getAlgorithm() +
                            ". Поле: " + sortingPanel.getField() + "."
            );
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

        // Преобразует выбранное поле в номер для SortService
        private int getFieldChoice() {
            return switch (sortingPanel.getField()) {
                case "Имя" -> 1;
                case "Возраст" -> 2;
                case "Вес" -> 3;
                case "Все поля" -> 4;

                default ->  throw new IllegalArgumentException(
                        "Неизвестное поле сортировки"
                );
            };
        }
    // Считает, сколько раз введённое животное встречается в коллекции.
    private void countAnimals() {

        if (animals.size() == 0) {
            showError("Коллекция пуста. Сначала создайте коллекцию.");
            return;
        }

        AnimalQueryWindow queryWindow =
                new AnimalQueryWindow(stage, "Подсчёт вхождений", "Подсчитать");

        Optional<Animal> target = queryWindow.showAndGet();

        // Окно закрыли без подтверждения.
        if (target.isEmpty()) {
            return;
        }

        try {
            int count = MultiThreadTask.countOccurrences(animals, target.get());

            Animal animal = target.get();
            resultPanel.setStatus(
                    "Вхождений животного «" + animal.getName() + ", "
                            + animal.getAge() + ", " + animal.getWeight()
                            + " кг»: " + count + "."
            );

        } catch (IllegalStateException e) {
            showError(e.getMessage());
        }
    }

    // Ищет введённое животное бинарным поиском.
    private void findAnimal() {

        if (animals.size() == 0) {
            showError("Коллекция пуста. Сначала создайте коллекцию.");
            return;
        }

        if (!BinarySearch.isSorted(animals, AnimalComparators.BY_ALL_FIELDS)) {
            showError(
                    "Для поиска коллекция должна быть отсортирована по всем полям. "
                            + "Выполните сортировку по полю «Все поля»."
            );
            return;
        }

        AnimalQueryWindow queryWindow =
                new AnimalQueryWindow(stage, "Поиск элемента", "Найти");

        Optional<Animal> target = queryWindow.showAndGet();

        // Окно закрыли без подтверждения.
        if (target.isEmpty()) {
            return;
        }

        Animal animal = target.get();

        int index = BinarySearch.find(
                animals,
                animal,
                AnimalComparators.BY_ALL_FIELDS
        );

        String description = "«" + animal.getName() + ", "
                + animal.getAge() + ", " + animal.getWeight() + " кг»";

        if (index == -1) {
            resultPanel.setStatus("Животное " + description + " не найдено.");

        } else {
            resultPanel.selectRow(index);
            resultPanel.setStatus(
                    "Животное " + description + " найдено, позиция: " + (index + 1) + "."
            );
        }
    }
// Преобразует выбранный алгоритм в номер для SortService.
        private int getAlgorithmChoice() {

            String algorithm = sortingPanel.getAlgorithm();
            return switch (algorithm) {
                case "Пузырьковая сортировка" -> 1;
                case "Сортировка вставками" -> 2;
                default -> throw new IllegalArgumentException(
                        "Алгоритм «" + algorithm + "» ещё не реализован."
                );
            };
        }

    // Показывает главное окно.
    public void show() {
        stage.show();
    }
}