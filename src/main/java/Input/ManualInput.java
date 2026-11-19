package Input;

import Service.AnimalService;
import Validation.Validator;
import model.Animal;

public class ManualInput {

    private final AnimalService animalService;

    public ManualInput(AnimalService animalService) {
        this.animalService = animalService;
    }

    public String addAnimal(
            String name,
            String age,
            String weight
    ) {

        // Проверяем введённые данные
        String error = Validator.validateAnimal(
                name,
                age,
                weight
        );

        if (error != null) {
            return error;
        }

        // Преобразуем строки в числа
        int animalAge = Integer.parseInt(age.trim());
        double animalWeight = Double.parseDouble(weight.trim());

        // Создаём животное
        Animal animal = animalService.createAnimal(
                name.trim(),
                animalAge,
                animalWeight
        );

        // Добавляем его в общую коллекцию
        animalService.addAnimal(animal);

        return null;
    }
}