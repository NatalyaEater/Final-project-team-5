package input;

import validation.Validator;
import model.Animal;
import model.MyArrayList;

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

        // Преобразуем строки в числа
        int animalAge = Integer.parseInt(age.trim());
        double animalWeight = Double.parseDouble(weight.trim());

        try {
            // Создаём животное и проверяем его через Builder
            Animal animal = new Animal.Builder()
                    .setName(name)
                    .setAge(animalAge)
                    .setWeight(animalWeight)
                    .build();

            animals.add(animal);

            return null;

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}