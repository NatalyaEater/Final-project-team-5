package Service;

import AllComparators.AnimalComparators;

import SortStrategy.SortStrategy;
import SortStrategy.BubbleSort;
import SortStrategy.InsertionSort;

import java.util.Comparator;

/**
 * Задача SortService — получить выбор пользователя,
 * подобрать нужный алгоритм и компаратор, выполнить сортировку и вернуть результат.
 */
public class SortService {

    // Обычная сортировка
    public MyArrayList<Animal> sort(MyArrayList<Animal> animals, int fieldChoice, int algorithmChoice) {

        // Выбираем поле сортировки
        Comparator<Animal> comparator = switch (fieldChoice) {
            case 1 -> AnimalComparators.BY_NAME;
            case 2 -> AnimalComparators.BY_AGE;
            case 3 -> AnimalComparators.BY_WEIGHT;
            case 4 -> AnimalComparators.BY_ALL_FIELDS;

            default -> throw new IllegalArgumentException(
                    "Некорректное поле сортировки"
            );
        };

        // Выбираем алгоритм сортировки
        SortStrategy<Animal> strategy = getStrategy(algorithmChoice);
        // Выполняем сортировку
        return strategy.sort(animals, comparator);
    }


    // Сортировать животных только с чётным возрастом, оставляя животных с нечётным возрастом на их исходных позициях.

    public MyArrayList<Animal> sortEvenAge(MyArrayList<Animal> animals, int algorithmChoice) {

        SortStrategy<Animal> strategy = getStrategy(algorithmChoice);

        // Создаём специальную сортировку
        AgeSort ageSort = new AgeSort();

        return ageSort.sort(animals, strategy);
    }

    // Общий метод выбора стратегии

    //TODO : добавить в case метод Николая
    private SortStrategy<Animal> getStrategy(int algorithmChoice) {

        return switch (algorithmChoice) {
            case 1 -> new BubbleSort<>();
            case 2 -> new InsertionSort<>();

            default -> throw new IllegalArgumentException(
                    "Некорректный алгоритм сортировки"
            );
        };
    }
}