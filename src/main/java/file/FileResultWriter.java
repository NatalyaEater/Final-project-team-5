package file;


import model.Animal;
import model.MyArrayList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Сохранение коллекции животных в CSV-файл.
public class FileResultWriter {

    public void writeAnimals(MyArrayList<Animal> animals, String filePath) throws IOException {

        if (animals == null || animals.size() == 0) {
            throw new IllegalArgumentException("Нет животных для сохранения.");
        }

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("Путь к файлу не указан.");
        }

        List<String> lines = new ArrayList<>();
        lines.add("name,age,weight");

        for (Animal animal : animals) {
            lines.add(animal.getName() + "," + animal.getAge() + "," + animal.getWeight());
        }

        Files.write(Path.of(filePath), lines, StandardCharsets.UTF_8);
    }
}