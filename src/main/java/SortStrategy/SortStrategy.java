package SortStrategy;

import java.util.Comparator;
import model.MyArrayList;

public interface SortStrategy<T> {

    /**
     * Возвращает новую отсортированную коллекцию {@code MyArrayList<T>}.
     * При этом исходная коллекция остаётся без изменений.
     *
     * @param list исходная коллекция
     * @param comparator компаратор, определяющий порядок элементов
     */

    MyArrayList<T> sort(
            MyArrayList<T> list,
            Comparator<T> comparator
    );
}

