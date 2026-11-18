package SortStrategy;

import java.util.Comparator;
import model.MyArrayList;

public class InsertionSort<T> implements SortStrategy<T> {

    @Override
    public MyArrayList<T> sort(
            MyArrayList<T> list,
            Comparator<T> comparator) {

        // Создаём временный массив
        Object[] array = new Object[list.size()];

        // Копируем элементы исходной коллекции
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        // Сортировка вставками
        for (int i = 1; i < array.length; i++) {

            Object current = array[i];
            int j = i - 1;

            // Сдвигаем большие элементы вправо
            while (j >= 0 &&
                    comparator.compare((T) array[j], (T) current) > 0) {

                array[j + 1] = array[j];
                j--;
            }

            // Вставляем элемент на нужную позицию
            array[j + 1] = current;
        }

        // Создаём новую отсортированную коллекцию
        MyArrayList<T> result = new MyArrayList<>();

        for (Object element : array) {
            result.add((T) element);
        }

        return result;
    }
}
