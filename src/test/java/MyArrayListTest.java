import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import model.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class MyArrayListTest {
    private MyArrayList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
    }

    @Test
    void testAddAndGet() {
        list.add("Первый");
        list.add("Второй");

        assertEquals(2, list.size());
        assertEquals("Первый", list.get(0));
        assertEquals("Второй", list.get(1));
    }

    @Test
    void testSetElement() {
        list.add("Старое значение");
        list.set(0, "Новое значение");

        assertEquals(1, list.size());
        assertEquals("Новое значение", list.get(0));
    }

    @Test
    void testGrowCapacity() {
        MyArrayList<Integer> numbers = new MyArrayList<>(2); 
        for (int i = 0; i < 25; i++) {
            numbers.add(i); 
        }

        assertEquals(25, numbers.size());
        assertEquals(0, numbers.get(0));
        assertEquals(24, numbers.get(24));
    }

    @Test
    void testOutOfBoundsExceptions() {
        list.add("Элемент");

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "Ошибка"));
    }

    @Test
    void testIterator() {
        list.add("A");
        list.add("B");

        StringBuilder result = new StringBuilder();
        for (String item : list) {
            result.append(item);
        }

        assertEquals("AB", result.toString());
    }
}
