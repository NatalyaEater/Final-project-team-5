import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class AnimalReader {

    public static List<Animal> readAnimals(String fileName) throws IOException {
        List<Animal> animals = new ArrayList<>();

        List<String> lines = Files.readAllLines(Path.of(fileName));
        for (String line : lines) {
            if (line.isBlank()) {
                continue; // пропускаем пустые строки
            }
            try {
                animals.add(parseAnimal(line));
            } catch (IllegalArgumentException | NumberFormatException e) {
                System.err.println("Пропущена строка \"" + line + "\": " + e.getMessage());
            }
        }
        return animals;
    }

    private static Animal parseAnimal(String line) {
        String[] parts = line.split(";");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки: " + line);
        }
        return new Animal(
                parts[0].trim(),
                Integer.parseInt(parts[1].trim()),
                Double.parseDouble(parts[2].trim()));
    }
}
