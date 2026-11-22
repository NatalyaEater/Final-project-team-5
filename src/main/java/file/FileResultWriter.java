package file;

import model.Animal;
import model.MyArrayList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileResultWriter {

    // Запись коллекции Animal в CSV-файл
    public void writeAnimals(
            MyArrayList<Animal> animals,
            String filePath) throws IOException {

        if (animals == null) {
            throw new IllegalArgumentException(
                    "Коллекция не должна быть null"
            );
        }

        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Укажите путь к файлу"
            );
        }

        Path path = Path.of(filePath);

        try (BufferedWriter writer = Files.newBufferedWriter(
                path,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE_NEW,
                StandardOpenOption.WRITE)) {

            writer.write("name,age,weight");
            writer.newLine();

            for (Animal animal : animals) {
                writer.write(
                        animal.getName() + ","
                                + animal.getAge() + ","
                                + animal.getWeight()
                );
                writer.newLine();
            }
        }
    }
}