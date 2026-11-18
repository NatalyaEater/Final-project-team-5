package SortStrategy;

import java.util.Comparator;
import model.MyArrayList;


public class BubbleSort<T> implements SortStrategy<T> {

    /**
     * Сортирует элементы коллекции пузырьковым методом.
     * @param list исходная коллекция
     * @param comparator компаратор для определения порядка
     * @return новая отсортированная коллекция
     */

    @Override
    public MyArrayList<T> sort(
            MyArrayList<T> list,
            Comparator<T> comparator
    ) {

        // Создаём копию исходной коллекции
        // for (Тип переменная : источник)
        MyArrayList<T> sortedList = new MyArrayList<>();

        for (T element : list) {
            sortedList.add(element);
        }


        // Пузырьковая сортировка
        for (int i = 0; i < sortedList.size() - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < sortedList.size() - 1 - i; j++) {

                T first = sortedList.get(j);
                T second = sortedList.get(j + 1);

                if (comparator.compare(first, second) > 0) {

                    sortedList.set(j, second);
                    sortedList.set(j + 1, first);
                    swapped = true;
                }
            }

            // Если перестановок не было, сортировка завершена
            if (!swapped) {
                break;
            }
        }

        return sortedList;
    }
}