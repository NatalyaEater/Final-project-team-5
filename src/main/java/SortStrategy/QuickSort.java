package SortStrategy;

import java.util.Comparator;
import java.util.List;

public class QuickSort<T> implements SortStrategy<T> {

    /**
     * Быстрая сортировка объектов типа {@code T}.
     *
     * <p><b>Выбор порядка сортировки:</b></p>
     * <ul>
     *     <li> {@code QuickSort<T>} может сортировать {@code Animal} по имени, возрасту, весу или всем трём полям.</li>
     *     <li>Объекты других классов можно сортировать, передав подходящий {@code Comparator}.</li>
     * </ul>
     *
     * <p>Сам алгоритм не знает, что такое имя, возраст или вес.
     * Он сравнивает элементы с помощью переданного компаратора.</p>
     */

    @Override
    public List<T> sort(List<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        // Выбираем средний элемент в качестве опорного
        int pivotIndex = list.size() / 2;
        T pivot = list.get(pivotIndex);

        // Разделяем элементы относительно опоры
        List<T> less = new MyLinkedList<>();
        List<T> greater = new MyLinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (comparator.compare(element, pivot) <= 0) {
                less.add(element);
            } else {
                greater.add(element);
            }
        }

        // Рекурсивно сортируем подсписки
        List<T> sortedLess = sort(less, comparator);
        List<T> sortedGreater = sort(greater, comparator);

        // Объединяем отсортированные части
        sortedLess.addAll(sortedGreater);
        return sortedLess;
    }

    // Пример реализации MyLinkedList (упрощенно)
    static class MyLinkedList<T> implements List<T> {
        private Node<T> head;
        private Node<T> tail;

        @Override
        public void add(T element) {
            Node<T> newNode = new Node<>(element);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        @Override
        public int size() {
            int count = 0;
            Node<T> temp = head;
            while (temp != null) {
                count++;
                temp = temp.next;
            }
            return count;
        }

        @Override
        public T get(int index) {
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.data;
        }

        private static class Node<T> {
            T data;
            Node<T> next;

            Node(T data) {
                this.data = data;
            }
        }
    }
}
