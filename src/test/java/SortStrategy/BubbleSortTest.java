package SortStrategy;

import AllComparators.AnimalComparators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSortTest {

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
        SortStrategy<Animal> strategy = new BubbleSort<>();

        // Выполняем сортировку
        MyArrayList<Animal> result = strategy.sort(animals, AnimalComparators.BY_AGE);

        // Проверяем результат
        assertEquals(2, result.get(0).getAge());
        assertEquals(5, result.get(1).getAge());
        assertEquals(8, result.get(2).getAge());
    }
}
