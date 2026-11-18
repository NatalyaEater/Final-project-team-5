
public class Animal {
    private String name;
    private int age;
    private double weight;

    public Animal(String name, int age, double weight) {
        this.name = checkName(name);
        this.age = checkAge(age);
        this.weight = checkWeight(weight);
    }

    private String checkName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        // разрешаем только буквы, пробелы и дефисы
        if (!name.matches("[A-Za-zА-Яа-яЁё \\-]+")) {
            throw new IllegalArgumentException("Имя может содержать только буквы, пробелы и дефисы: " + name);
        }
        return name;
    }

    private int checkAge(int age) {
        if (age < 0 || age > 100) {
            throw new IllegalArgumentException("Возраст должен быть от 0 до 100: " + age);
        }
        return age;
    }

    private double checkWeight(double weight) {
        if (weight <= 0 || weight > 1000) {
            throw new IllegalArgumentException("Вес должен быть больше 0 и не больше 1000: " + weight);
        }
        return weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Animal{name='" + name + "', age=" + age + ", weight=" + weight + "}";
    }
}
