package SortStrategy;

import AllComparators.AnimalComparators;
import model.MyArrayList;
import model.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class BubbleSortTest {

    // Проверка сортировки животных по полю Age
    @Test
    void testSortByAge() {

        // Создаём коллекцию
        MyArrayList<Animal> animals = new MyArrayList<>();

        // Добавляем животных через Builder
        animals.add(new Animal.Builder()
                .setName("Catone")
                .setAge(3)
                .setWeight(1.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Cattwo")
                .setAge(5)
                .setWeight(3.0)
                .build());

        animals.add(new Animal.Builder()
                .setName("Catthree")
                .setAge(1)
                .setWeight(5.0)
                .build());

        System.out.println("Проверка сортировки животных по полю Age");

        // Создаём алгоритм сортировки
        SortStrategy<Animal> strategy = new BubbleSort<Animal>();

        // Выводим исходную коллекцию
        System.out.println("До сортировки:");

        for (Animal animal : animals) {
            System.out.println(
                    animal.getName() + " - возраст: " + animal.getAge()
            );
        }

        // Выполняем сортировку
        MyArrayList<Animal> result = strategy.sort(animals, AnimalComparators.BY_AGE);

        // Выводим результат
        System.out.println("\nПосле сортировки:");

        for (Animal animal : result) {
            System.out.println(
                    animal.getName() + " - возраст: " + animal.getAge()
            );
        }


        // Проверяем результат сортировки
        assertEquals(1, result.get(0).getAge());
        assertEquals(3, result.get(1).getAge());
        assertEquals(5, result.get(2).getAge());

        // Проверяем, что исходная коллекция не изменилась
        assertEquals(3, animals.get(0).getAge());
        assertEquals(5, animals.get(1).getAge());
        assertEquals(1, animals.get(2).getAge());

        // Проверяем, что создана новая коллекция

        System.out.println("\nРазные объекты коллекций: " + (animals != result));
        assertNotSame(animals, result);
    }
}
