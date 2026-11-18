import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.example.Animal;

public class AnimalTest {
    @Test
    void testValidAnimalCreationAndGetters() {
        Animal cat = new Animal.Builder()
                .setName("Кот-Баюн")
                .setAge(5)
                .setWeight(6.2)
                .build();

        assertEquals("Кот-Баюн", cat.getName());
        assertEquals(5, cat.getAge());
        assertEquals(6.2, cat.getWeight());
    }

    @Test
    void testInvalidNameValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal.Builder().setName("   ").setAge(2).setWeight(3.0).build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Animal.Builder().setName("Барсик123!").setAge(2).setWeight(3.0).build();
        });
    }

    @Test
    void testInvalidAgeAndWeightValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal.Builder().setName("Шарик").setAge(-1).setWeight(10.0).build();
        });

        // Нулевой или отрицательный вес
        assertThrows(IllegalArgumentException.class, () -> {
            new Animal.Builder().setName("Кеша").setAge(1).setWeight(0.0).build();
        });
    }

    @Test
    void testEqualsAndHashCode() {
        Animal a1 = new Animal.Builder().setName("Рекс").setAge(4).setWeight(18.5).build();
        Animal a2 = new Animal.Builder().setName("Рекс").setAge(4).setWeight(18.5).build();
        Animal a3 = new Animal.Builder().setName("Бобик").setAge(4).setWeight(18.5).build();

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
        assertNotEquals(a1, a3);
    }
}
