package Service;

import model.Animal;
import model.MyArrayList;
import AllComparators.AnimalComparators;
import SortStrategy.SortStrategy;

import java.util.stream.IntStream;

public class AgeSort {

    public MyArrayList<Animal> sort(MyArrayList<Animal> animals, SortStrategy<Animal> strategy) {

        // 1. Выбираем только животных с чётным возрастом
        MyArrayList<Animal> evenAnimals = animalsToEven(animals);

        // 2. Сортируем их выбранным алгоритмом
        MyArrayList<Animal> sortedEvenAnimals =
                strategy.sort(
                        evenAnimals,
                        AnimalComparators.BY_AGE
                );

        // 3. Возвращаем отсортированные элементы
        // на позиции чётных, сохраняя нечётных
        return mergeAnimals(animals, sortedEvenAnimals);
    }


    private MyArrayList<Animal> animalsToEven(
            MyArrayList<Animal> animals) {

        //Создаём пустую коллекцию
        MyArrayList<Animal> result = new MyArrayList<>();

        IntStream.range(0, animals.size())
                .mapToObj(animals::get)
                .filter(animal -> animal.getAge() % 2 == 0)
                .forEach(result::add);

        return result;
    }

    // Формирование результирующей коллекции
    private MyArrayList<Animal> mergeAnimals(MyArrayList<Animal> animals, MyArrayList<Animal> sortedEvenAnimals
    ) {

        MyArrayList<Animal> result = new MyArrayList<>();

        int evenIndex = 0;

        for (Animal animal : animals) {

            if (animal.getAge() % 2 == 0) {
                result.add(sortedEvenAnimals.get(evenIndex));
                evenIndex++;
            } else {
                result.add(animal);
            }
        }

        return result;
    }
}