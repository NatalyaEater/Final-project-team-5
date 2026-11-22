package file;

import model.Animal;
import model.MyArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileResultWriterTest {

    @TempDir
    Path tempDir;

    private final FileResultWriter writer = new FileResultWriter();

    // Проверка успешного сохранения одного животного
    @Test
    void shouldWriteAnimalsToCsv() throws IOException {
        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(new Animal.Builder()
                .setName("Барсик")
                .setAge(5)
                .setWeight(12.5)
                .build());

        Path file = tempDir.resolve("animals.csv");

        writer.writeAnimals(animals, file.toString());

        assertTrue(Files.exists(file));

        String content = Files.readString(file, StandardCharsets.UTF_8);

        assertTrue(content.contains("name,age,weight"));
        assertTrue(content.contains("Барсик,5,12.5"));
    }

    // Проверка сохранения нескольких животных
    @Test
    void shouldWriteSeveralAnimals() throws IOException {
        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(new Animal.Builder()
                .setName("Барсик")
                .setAge(5)
                .setWeight(12.5)
                .build());

        animals.add(new Animal.Builder()
                .setName("Мурка")
                .setAge(3)
                .setWeight(8.2)
                .build());

        Path file = tempDir.resolve("animals.csv");

        writer.writeAnimals(animals, file.toString());

        String content = Files.readString(file, StandardCharsets.UTF_8);

        assertTrue(content.contains("Барсик,5,12.5"));
        assertTrue(content.contains("Мурка,3,8.2"));
    }

    // Проверка сохранения пустой коллекции
    @Test
    void shouldWriteEmptyCollection() throws IOException {
        MyArrayList<Animal> animals = new MyArrayList<>();

        Path file = tempDir.resolve("empty.csv");

        writer.writeAnimals(animals, file.toString());

        String content = Files.readString(file, StandardCharsets.UTF_8);

        assertEquals("name,age,weight" + System.lineSeparator(), content);
    }

    // Проверка передачи null вместо коллекции
    @Test
    void shouldRejectNullCollection() {
        Path file = tempDir.resolve("animals.csv");

        assertThrows(
                IllegalArgumentException.class,
                () -> writer.writeAnimals(null, file.toString())
        );
    }

    // Проверка пустого пути
    @Test
    void shouldRejectEmptyFilePath() {
        MyArrayList<Animal> animals = new MyArrayList<>();

        assertThrows(
                IllegalArgumentException.class,
                () -> writer.writeAnimals(animals, "")
        );
    }

    // Проверка запрета перезаписи существующего файла
    @Test
    void shouldNotOverwriteExistingFile() throws IOException {
        MyArrayList<Animal> animals = new MyArrayList<>();

        Path file = tempDir.resolve("animals.csv");

        Files.writeString(file, "Original content");

        assertThrows(
                IOException.class,
                () -> writer.writeAnimals(animals, file.toString())
        );

        String content = Files.readString(file);

        assertEquals("Original content", content);
    }
}