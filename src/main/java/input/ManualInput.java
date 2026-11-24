package input;

import validation.Validator;
import model.Animal;
import model.MyArrayList;

import java.util.stream.Stream;

public class ManualInput {

    private final MyArrayList<Animal> animals;

    public ManualInput(MyArrayList<Animal> animals) {
        this.animals = animals;
    }

    public String addAnimal(String name, String age, String weight) {

        // Проверяем формат числовых данных
        String error = Validator.validateAnimal(age, weight);

        if (error != null) {
            return error;
        }

        try {
            // Создаём поток и преобразуем имя в объект Animal
            Stream.of(name)
                    .map(animalName -> new Animal.Builder()
                            .setName(animalName)
                            .setAge(Integer.parseInt(age.trim()))
                            .setWeight(Double.parseDouble(weight.trim()))
                            .build())
                    .forEach(animals::add);

            return null;

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}