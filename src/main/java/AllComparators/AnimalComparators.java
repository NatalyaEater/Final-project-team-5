package AllComparators;

import java.util.Comparator;
import model.Animal;


public class AnimalComparators {

    /**
     * Компараторы для сортировки объектов класса {@code Animal}.
     *
     * <p><b>Как устроен компаратор BY_NAME:</b></p>
     * <ul>
     *     <li>{@code Comparator<Animal>} — тип компаратора,
     *         который сравнивает объекты класса {@code Animal}.</li>
     *     <li>{@code BY_NAME} — название поля, в котором хранится компаратор.</li>
     *     <li>{@code Animal::getName} — ссылка на метод, который
     *         возвращает имя животного для сравнения.</li>
     *     <li>{@code String.CASE_INSENSITIVE_ORDER} — готовый компаратор
     *         строк, который сравнивает имена без учёта регистра букв.</li>
     * </ul>
     */


    public static final Comparator<Animal> BY_NAME =
            Comparator.comparing(
                    Animal::getName,
                    String.CASE_INSENSITIVE_ORDER
            );

    public static final Comparator<Animal> BY_AGE =
            Comparator.comparingInt(Animal::getAge);

    public static final Comparator<Animal> BY_WEIGHT =
            Comparator.comparingDouble(Animal::getWeight);


    public static final Comparator<Animal> BY_ALL_FIELDS =
            BY_NAME.thenComparing(BY_AGE).thenComparing(BY_WEIGHT);
}
