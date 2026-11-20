package SortStrategy;

import AllComparators.AnimalComparators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {

    // Проверка сортировки животных по полю Age
    @Test
    void testSortByAge() {

        // Создаём коллекцию
        MyArrayList<Animal> animals = new MyArrayList<>();

        // Добавляем животных через Builder
        animals.add(new Animal.Builder()
                .setName("Cat1")
                .setAge(3)
                .setWeight(1.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cat2")
                .setAge(5)
                .setWeight(3.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cat3")
                .setAge(1)
                .setWeight(5.0)
                .build());

        // Создаём алгоритм сортировки
        SortStrategy<Animal> strategy = new QuickSort<>();

        // Выполняем сортировку
        List<Animal> result = strategy.sort(animals, AnimalComparators.BY_AGE);

        // Проверяем результат
        assertEquals(1, result.get(0).getAge());
        assertEquals(3, result.get(1).getAge());
        assertEquals(5, result.get(2).getAge());
    }

}

    // Проверка сортировки по имени без учёта регистра
    @Test
    void testSortByName() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(new Animal.Builder()
                .setName("buddy")
                .setAge(2)
                .setWeight(4.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Alfa")
                .setAge(4)
                .setWeight(2.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cesar")
                .setAge(6)
                .setWeight(6.0)
                .build());

        SortStrategy<Animal> strategy = new QuickSort<>();
        List<Animal> result = strategy.sort(animals, AnimalComparators.BY_NAME);

        // Buddy после Alfa, потому что сравнение без учёта регистра
        assertEquals("Alfa", result.get(0).getName());
        assertEquals("buddy", result.get(1).getName());
        assertEquals("Cesar", result.get(2).getName());
    }

    // Проверка сортировки по весу
    @Test
    void testSortByWeight() {

        MyArrayList<Animal> animals = new MyArrayList<>();

        animals.add(new Animal.Builder()
                .setName("Cat1")
                .setAge(3)
                .setWeight(7.5)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cat2")
                .setAge(5)
                .setWeight(2.5)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cat3")
                .setAge(1)
                .setWeight(5.0)
                .build());

        SortStrategy<Animal> strategy = new QuickSort<>();
        List<Animal> result = strategy.sort(animals, AnimalComparators.BY_WEIGHT);

        assertEquals(2.5, result.get(0).getWeight());
        assertEquals(5.0, result.get(1).getWeight());
        assertEquals(7.5, result.get(2).getWeight());
    }

    // Проверка граничного случая: пустая коллекция
    @Test
    void testSortEmptyList() {
        SortStrategy<Animal> strategy = new QuickSort<>();
        MyArrayList<Animal> animals = new MyArrayList<>();

        List<Animal> result = strategy.sort(animals, AnimalComparators.BY_AGE);

        assertEquals(0, result.size());
    }
