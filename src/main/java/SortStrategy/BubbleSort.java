package SortStrategy;

import java.util.Comparator;

public class BubbleSort<T> implements SortStrategy<T> {

    /**
     * Пузырьковая сортировка объектов типа {@code T}.
     *
     * <p><b>Выбор порядка сортировки:</b></p>
     * <ul>
     *     <li> {@code BubbleSort<T>} может сортировать {@code Animal}  по имени,
     *         возрасту, весу или всем трём полям.</li>
     *     <li>Объекты других классов можно сортировать,
     *         передав подходящий {@code Comparator}.</li>
     * </ul>
     *
     * <p>Сам алгоритм не знает, что такое имя, возраст или вес.
     * Он сравнивает элементы с помощью переданного компаратора.</p>
     *
     * <p><b>Принцип работы {@code BubbleSort} :</b></p>
     * <ol>
     *     <li>Сравнить два соседних элемента.</li>
     *     <li>Если они расположены в неправильном порядке —
     *         поменять их местами.</li>
     *     <li>Перейти к следующей паре соседей.</li>
     *     <li>Повторять проходы, пока коллекция не будет отсортирована.</li>
     * </ol>
     *
     * <p><b>Назначение циклов:</b></p>
     * <ul>
     *     <li>Внешний цикл с переменной {@code i} управляет проходами.</li>
     *     <li>Внутренний цикл с переменной {@code j} сравнивает
     *         соседние элементы и при необходимости меняет их местами.</li>
     * </ul>
     *
     * <p>После каждого полного прохода наибольший по компаратору
     * элемент неотсортированной части занимает своё место в её конце.
     * Поэтому для коллекции из {@code n} элементов достаточно
     * максимум {@code n - 1} проходов.</p>
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

            for (int j = 0; j < sortedList.size() - 1 - i; j++) {

                T first = sortedList.get(j);
                T second = sortedList.get(j + 1);

                if (comparator.compare(first, second) > 0) {

                    sortedList.set(j, second);
                    sortedList.set(j + 1, first);
                }
            }
        }

        return sortedList;
    }
}