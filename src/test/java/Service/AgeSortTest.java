package Service;

import SortStrategy.BubbleSort;
import SortStrategy.InsertionSort;
import SortStrategy.SortStrategy;
import model.Animal;
import model.MyArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AgeSortTest {

    private final AgeSort ageSort = new AgeSort();

    // Создаём животное с заданным возрастом
    private Animal createAnimal(String name, int age) {
        return new Animal.Builder()
                .setName(name)
                .setAge(age)
                .setWeight(5.0)
                .build();
    }

    // 1. Сортировка чётных возрастов пузырьком
    @Test
    void testEvenAgeBubbleSort() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(createAnimal("Cat", 8));
        animals.add(createAnimal("Dog", 3));
        animals.add(createAnimal("Bunny", 6));
        animals.add(createAnimal("Leon", 5));
        animals.add(createAnimal("Crow", 2));

        SortStrategy<Animal> strategy = new BubbleSort<Animal>();

        MyArrayList<Animal> result =
                ageSort.sort(animals, strategy);

        assertEquals(2, result.get(0).getAge());
        assertEquals(3, result.get(1).getAge());
        assertEquals(6, result.get(2).getAge());
        assertEquals(5, result.get(3).getAge());
        assertEquals(8, result.get(4).getAge());

        // Нечётные животные остались на своих местах
        assertSame(animals.get(1), result.get(1));
        assertSame(animals.get(3), result.get(3));
    }

    // 2. Сортировка чётных возрастов вставками
    @Test
    void testEvenAgeInsertionSort() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(createAnimal("Cat", 10));
        animals.add(createAnimal("Dog", 7));
        animals.add(createAnimal("Bunny", 4));
        animals.add(createAnimal("Leon", 3));
        animals.add(createAnimal("Crow", 2));

        SortStrategy<Animal> strategy = new InsertionSort<Animal>();

        MyArrayList<Animal> result =
                ageSort.sort(animals, strategy);

        assertEquals(2, result.get(0).getAge());
        assertEquals(7, result.get(1).getAge());
        assertEquals(4, result.get(2).getAge());
        assertEquals(3, result.get(3).getAge());
        assertEquals(10, result.get(4).getAge());
    }

    // 3. Проверяем, что исходная коллекция не изменяется
    @Test
    void testOriginalCollectionUnchanged() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(createAnimal("Cat", 8));
        animals.add(createAnimal("Dog", 3));
        animals.add(createAnimal("Bunny", 2));

        MyArrayList<Animal> result =
                ageSort.sort(animals, new BubbleSort<Animal>());

        assertEquals(8, animals.get(0).getAge());
        assertEquals(3, animals.get(1).getAge());
        assertEquals(2, animals.get(2).getAge());

        assertEquals(2, result.get(0).getAge());
        assertEquals(3, result.get(1).getAge());
        assertEquals(8, result.get(2).getAge());

        assertNotSame(animals, result);
    }

    // 4. Проверяем коллекцию только с нечётными возрастами
    @Test
    void testOnlyOddAges() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(createAnimal("Cat", 7));
        animals.add(createAnimal("Dog", 3));
        animals.add(createAnimal("Bunny", 5));

        MyArrayList<Animal> result =
                ageSort.sort(animals, new BubbleSort<Animal>());

        for (int i = 0; i < animals.size(); i++) {
            assertSame(animals.get(i), result.get(i));
        }

        assertNotSame(animals, result);
    }

    // 5. Проверяем пустую коллекцию
    @Test
    void testEmptyCollection() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        MyArrayList<Animal> result =
                ageSort.sort(animals, new InsertionSort<Animal>());

        assertEquals(0, result.size());
    }
}