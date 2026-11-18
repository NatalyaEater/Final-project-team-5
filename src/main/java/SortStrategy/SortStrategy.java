package SortStrategy;

import java.util.Comparator;

public interface SortStrategy<T> {

    /**
     * Возвращает новую отсортированную коллекцию {@code MyArrayList<T>}.
     * При этом исходная коллекция остаётся без изменений.
     *
     * <p>Тип элементов {@code T} совпадает с типом элементов
     * исходной коллекции.</p>
     *
     * <p><b>Главное преимущество:</b>
     * Мы можем сортировать одни и те же исходные данные разными способами,
     * не теряя их первоначальный порядок.</p>
     *
     * @param list исходная коллекция
     * @param comparator компаратор, определяющий порядок элементов
     */

    MyArrayList<T> sort(
            MyArrayList<T> list,
            Comparator<T> comparator
    );
}

