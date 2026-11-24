package fill;

import model.Animal;
import model.MyArrayList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// Загрузка животных из текстового файла (.csv или .txt).
public class FileLoader {

    private static final String HEADER = "name,age,weight";

    public MyArrayList<Animal> load(String filePath) throws IOException {

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Файл не выбран.");
        }

        List<String> lines = Files.readAllLines(Path.of(filePath), StandardCharsets.UTF_8);
        MyArrayList<Animal> animals = new MyArrayList<>();

        for (int i = 0; i < lines.size(); i++) {

            // \uFEFF - невидимый маркер, который Excel иногда ставит в начале файла.
            String line = lines.get(i).replace("\uFEFF", "").trim();

            if (line.isEmpty()) {
                continue;
            }

            // Пропускаем строку-заголовок.
            if (i == 0 && line.equalsIgnoreCase(HEADER)) {
                continue;
            }

            try {
                animals.add(parseLine(line));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Строка " + (i + 1) + ": " + e.getMessage());
            }
        }

        if (animals.size() == 0) {
            throw new IllegalArgumentException("В файле нет животных.");
        }

        return animals;
    }

    // Разбирает одну строку формата: имя,возраст,вес
    private Animal parseLine(String line) {

        String separator = line.contains(";") ? ";" : ",";
        String[] parts = line.split(separator, -1);

        if (parts.length != 3) {
            throw new IllegalArgumentException("Ожидается формат: имя,возраст,вес.");
        }

        int age;
        try {
            age = Integer.parseInt(parts[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Возраст должен быть целым числом.");
        }

        double weight;
        try {
            weight = Double.parseDouble(parts[2].trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Вес должен быть числом.");
        }

        return new Animal.Builder()
                .setName(parts[0].trim())
                .setAge(age)
                .setWeight(weight)
                .build();
    }
}