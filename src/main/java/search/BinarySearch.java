package search;

import model.MyArrayList;

import java.util.Comparator;

// Бинарный поиск по отсортированной коллекции.
public class BinarySearch {

    // Проверяет, что коллекция упорядочена по возрастанию согласно компаратору.
    public static <T> boolean isSorted(MyArrayList<T> list, Comparator<T> comparator) {

        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

    // Возвращает индекс первого найденного элемента или -1, если его нет.
    // Коллекция должна быть отсортирована тем же компаратором.
    public static <T> int find(MyArrayList<T> list, T target, Comparator<T> comparator) {

        int left = 0;
        int right = list.size() - 1;
        int found = -1;

        while (left <= right) {

            int middle = left + (right - left) / 2;
            int result = comparator.compare(list.get(middle), target);

            if (result == 0) {
                // Запоминаем и ищем левее, чтобы вернуть первое вхождение.
                found = middle;
                right = middle - 1;

            } else if (result < 0) {
                left = middle + 1;

            } else {
                right = middle - 1;
            }
        }

        return found;
    }
}