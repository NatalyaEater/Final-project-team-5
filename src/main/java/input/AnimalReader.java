import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AnimalReader {

    /** Разделитель полей в CSV-файле. */
    private static final String DELIMITER = ",";

    public static Animal[] readAnimals(String fileName) throws IOException {
        int count = 0;
        String line;

        // Проход 1: считаем валидные строки, чтобы узнать размер массива
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank() && isParsable(line)) {
                    count++;
                }
            }
        }

        // Проход 2: заполняем массив
        Animal[] animals = new Animal[count];
        int index = 0;
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))) {
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue; // пропускаем пустые строки
                }
                try {
                    animals[index++] = parseAnimal(line);
                } catch (NumberFormatException e) {
                    index--; // строка не добавлена — откатываем индекс
                    System.err.println("Пропущена строка \"" + line + "\": " + e.getMessage());
                }
            }
        }
        return animals;
    }

    private static boolean isParsable(String line) {
        try {
            parseAnimal(line);
            return true;
        } catch (NumberFormatException | IllegalArgumentException e) {
            return false;
        }
    }

    private static Animal parseAnimal(String line) {
        String[] parts = line.split(DELIMITER);
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки: " + line);
        }
        return new Animal(
                parts[0].trim(),
                Integer.parseInt(parts[1].trim()),
                Double.parseDouble(parts[2].trim()));
    }
}
