package SortStrategy;

import AllComparators.AnimalComparators;

import java.util.Comparator;

public class QuickSort<T> implements SortStrategy<T> {

    /**
     * Быстрая сортировка объектов типа {@code T}.
     *
     * <p><b>Выбор порядка сортировки:</b></p>
     * <ul>
     *     <li>Для {@code Animal} есть специализированный метод {@link #sortAnimals(MyList)},
     *         который внутри использует готовые компараторы
     *         из {@code AllComparators.AnimalComparators}
     *         (по умолчанию — {@code BY_ALL_FIELDS}).</li>
     *     <li>Объекты других классов можно сортировать, передав подходящий {@code Comparator}.</li>
     * </ul>
     *
     * <p>Базовый метод сортировки не знает, что такое имя, возраст или вес:
     * он сравнивает элементы с помощью переданного компаратора.</p>
     */

    @Override
    public MyList<T> sort(MyList<T> list, Comparator<T> comparator) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        T pivot = list.get(list.size() / 2);

        MyList<T> less    = new MyLinkedList<>();
        MyList<T> equal   = new MyLinkedList<>();
        MyList<T> greater = new MyLinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            int cmp = comparator.compare(element, pivot);
            if (cmp < 0) {
                less.add(element);
            } else if (cmp > 0) {
                greater.add(element);
            } else {
                equal.add(element);
            }
        }

        MyList<T> result = sort(less, comparator);
        result.addAll(equal);
        result.addAll(sort(greater, comparator));
        return result;
    }

    /**
     * Сортировка списка животных с использованием готовых компараторов
     * из {@code AnimalComparators}. По умолчанию — {@code BY_ALL_FIELDS}.
     * Вызваться может только для списков типа {@code Animal}.
     */
    public static MyList<Animal> sortAnimals(MyList<Animal> animals) {
        return new QuickSort<Animal>().sort(animals, AnimalComparators.BY_ALL_FIELDS);
    }

    /**
     * Сортировка животных с явно выбранным компаратором из {@code AnimalComparators}.
     */
    public static MyList<Animal> sortAnimals(MyList<Animal> animals,
                                             Comparator<Animal> comparator) {
        return new QuickSort<Animal>().sort(animals, comparator);
    }
}

interface MyList<T> {

    /** Добавляет элемент в конец списка. */
    void add(T element);

    /** Возвращает элемент по индексу. */
    T get(int index);

    /** Возвращает количество элементов в списке. */
    int size();

    /** Добавляет все элементы другого списка в конец текущего. */
    void addAll(MyList<T> other);
}

class MyLinkedList<T> implements MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Индекс " + index + " вне границ, размер: " + size);
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(MyList<T> other) {
        for (int i = 0; i < other.size(); i++) {
            add(other.get(i));
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
