//модель данных одной строки таблицы

package ui;

public record AnimalRow(
        String name,
        int age,
        double weight
) {
}
