package Input;

import Service.AnimalService;
import model.Animal;
import model.MyArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualInputTest {

    private MyArrayList<Animal> animals;
    private AnimalService animalService;
    private ManualInput manualInput;

    @BeforeEach
    void setUp() {
        animals = new MyArrayList<>();
        animalService = new AnimalService(animals);
        manualInput = new ManualInput(animalService);
    }

    @Test
    void shouldAddValidAnimal() {
        String result = manualInput.addAnimal(
                "Барсик",
                "5",
                "12.5"
        );

        assertNull(result);
        assertEquals(1, animals.size());

        Animal animal = animals.get(0);

        assertEquals("Барсик", animal.getName());
        assertEquals(5, animal.getAge());
        assertEquals(12.5, animal.getWeight(), 0.0001);
    }

    @Test
    void shouldNotAddAnimalWithInvalidName() {
        String result = manualInput.addAnimal(
                "Барсик123",
                "5",
                "12.5"
        );

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldNotAddAnimalWithInvalidAge() {
        String result = manualInput.addAnimal(
                "Барсик",
                "150",
                "12.5"
        );

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldNotAddAnimalWithInvalidWeight() {
        String result = manualInput.addAnimal(
                "Барсик",
                "5",
                "-12.5"
        );

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldAddSeveralAnimals() {
        manualInput.addAnimal("Барсик", "5", "12.5");
        manualInput.addAnimal("Мурзик", "3", "8.0");
        manualInput.addAnimal("Рекс", "7", "25.0");

        assertEquals(3, animals.size());
        assertEquals("Барсик", animals.get(0).getName());
        assertEquals("Мурзик", animals.get(1).getName());
        assertEquals("Рекс", animals.get(2).getName());
    }

    @Test
    void shouldNotChangeCollectionAfterInvalidInput() {
        manualInput.addAnimal("Барсик", "5", "12.5");

        String result = manualInput.addAnimal(
                "Мурзик",
                "abc",
                "8.0"
        );

        assertNotNull(result);
        assertEquals(1, animals.size());
        assertEquals("Барсик", animals.get(0).getName());
    }
}