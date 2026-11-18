import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            List<Animal> animals = AnimalReader.readAnimals("animals.txt");

            System.out.println("Загружено животных: " + animals.size());
            for (Animal a : animals) {
                System.out.println(a);
            }

            // если нужен именно массив, а не список:
            Animal[] array = animals.toArray(new Animal[0]);
            System.out.println("Размер массива: " + array.length);

        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл: " + e.getMessage());
        }
    }
}
