package input;

import model.Animal;
import model.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualInputTest {

    private MyArrayList<Animal> animals;
    private ManualInput manualInput;

    @BeforeEach
    void setUp() {
        animals = new MyArrayList<>();
        manualInput = new ManualInput(animals);
    }

    @Test
    void shouldAddValidAnimal() {
        String result = manualInput.addAnimal("Барсик", "5", "12.5");

        assertNull(result);
        assertEquals(1, animals.size());

        Animal animal = animals.get(0);

        assertEquals("Барсик", animal.getName());
        assertEquals(5, animal.getAge());
        assertEquals(12.5, animal.getWeight(), 0.0001);
    }

    @Test
    void shouldNotAddAnimalWithInvalidName() {
        String result = manualInput.addAnimal("Барсик123", "5", "12.5");

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldNotAddAnimalWithInvalidAge() {
        String result = manualInput.addAnimal("Барсик", "abc", "12.5");

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldNotAddAnimalWithInvalidWeight() {
        String result = manualInput.addAnimal("Барсик", "5", "-12.5");

        assertNotNull(result);
        assertEquals(0, animals.size());
    }

    @Test
    void shouldAddSeveralAnimals() {
        assertNull(manualInput.addAnimal("Барсик", "5", "12.5"));
        assertNull(manualInput.addAnimal("Мурка", "3", "8.2"));

        assertEquals(2, animals.size());
    }

    @Test
    void shouldNotChangeCollectionAfterInvalidInput() {
        manualInput.addAnimal("Барсик", "5", "12.5");

        String result = manualInput.addAnimal("123", "abc", "-5");

        assertNotNull(result);
        assertEquals(1, animals.size());
    }
}