package Service;

import model.Animal;
import model.MyArrayList;

public class AnimalService {

    private final MyArrayList<Animal> animals;

    public AnimalService(MyArrayList<Animal> animals) {
        this.animals = animals;
    }

    // Создание животного через Builder
    public Animal createAnimal(
            String name,
            int age,
            double weight
    ) {
        return new Animal.Builder()
                .setName(name)
                .setAge(age)
                .setWeight(weight)
                .build();
    }

    // Добавление животного в общую коллекцию
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    // Получение общей коллекции
    public MyArrayList<Animal> getAnimals() {
        return animals;
    }
}
