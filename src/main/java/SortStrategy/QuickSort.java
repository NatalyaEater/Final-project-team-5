package SortStrategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements SortStrategy<T> {

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        // Сохраняем поведение исходного кода для null
        if (list == null) {
            return null;
        }

        // Возвращаем новую изменяемую копию
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        // Выбираем элемент в середине списка
        T pivot = list.get(list.size() / 2);

        List<T> less = new ArrayList<>();
        List<T> equal = new ArrayList<>();
        List<T> greater = new ArrayList<>();

        // Распределяем элементы по трём группам
        for (T element : list) {
            int comparison = comparator.compare(element, pivot);

            if (comparison < 0) {
                less.add(element);
            } else if (comparison > 0) {
                greater.add(element);
            } else {
                equal.add(element);
            }
        }

        // Сортируем меньшие и большие элементы
        List<T> sortedLess = sort(less, comparator);
        List<T> sortedGreater = sort(greater, comparator);

        // Собираем новый итоговый список
        List<T> result = new ArrayList<>(list.size());
        result.addAll(sortedLess);
        result.addAll(equal);
        result.addAll(sortedGreater);

        return result;
    }
}