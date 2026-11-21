package validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    // Проверка корректных числовых данных
    @Test
    void shouldAcceptValidAnimalData() {
        assertNull(Validator.validateAnimal("5", "12.5"));
    }

    // Проверка пустого возраста
    @Test
    void shouldRejectEmptyAge() {
        assertNotNull(Validator.validateAnimal("", "12.5"));
        assertNotNull(Validator.validateAnimal("   ", "12.5"));
    }

    // Проверка некорректного возраста
    @Test
    void shouldRejectInvalidAge() {
        assertNotNull(Validator.validateAnimal("abc", "12.5"));
        assertNotNull(Validator.validateAnimal("5.5", "12.5"));
        assertNotNull(Validator.validateAnimal(null, "12.5"));
    }

    // Проверка пустого веса
    @Test
    void shouldRejectEmptyWeight() {
        assertNotNull(Validator.validateAnimal("5", ""));
        assertNotNull(Validator.validateAnimal("5", "   "));
        assertNotNull(Validator.validateAnimal("5", null));
    }

    // Проверка некорректного веса
    @Test
    void shouldRejectInvalidWeight() {
        assertNotNull(Validator.validateAnimal("5", "abc"));
        assertNotNull(Validator.validateAnimal("5", "12,5"));
        assertNotNull(Validator.validateAnimal("5", "NaN"));
        assertNotNull(Validator.validateAnimal("5", "Infinity"));
    }

    // Проверка корректной длины массива
    @Test
    void shouldAcceptValidArrayLength() {
        assertNull(Validator.validateArrayLength("1"));
        assertNull(Validator.validateArrayLength("100"));
        assertNull(Validator.validateArrayLength("1000"));
    }

    // Проверка пустой длины массива
    @Test
    void shouldRejectEmptyArrayLength() {
        assertNotNull(Validator.validateArrayLength(""));
        assertNotNull(Validator.validateArrayLength("   "));
        assertNotNull(Validator.validateArrayLength(null));
    }

    // Проверка некорректной длины массива
    @Test
    void shouldRejectInvalidArrayLength() {
        assertNotNull(Validator.validateArrayLength("abc"));
        assertNotNull(Validator.validateArrayLength("1.5"));
        assertNotNull(Validator.validateArrayLength("0"));
        assertNotNull(Validator.validateArrayLength("1001"));
        assertNotNull(Validator.validateArrayLength("999999999999"));
    }

    // Проверка корректного формата файла
    @Test
    void shouldAcceptValidFileFormat() {
        assertNull(Validator.validateFileFormat("animals.csv"));
        assertNull(Validator.validateFileFormat("animals.CSV"));
    }

    // Проверка некорректного формата файла
    @Test
    void shouldRejectInvalidFileFormat() {
        assertNotNull(Validator.validateFileFormat("animals.txt"));
        assertNotNull(Validator.validateFileFormat("animals.xlsx"));
        assertNotNull(Validator.validateFileFormat(""));
        assertNotNull(Validator.validateFileFormat("   "));
        assertNotNull(Validator.validateFileFormat(null));
    }
}