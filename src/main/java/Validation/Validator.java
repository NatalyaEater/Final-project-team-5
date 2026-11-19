package Validation;

public class Validator {

    public static String validateAnimal(
            String name,
            String age,
            String weight
    ) {
        if (name == null || name.trim().isEmpty()) {
            return "Введите имя животного.";
        }

        if (!name.trim().matches("[a-zA-Zа-яА-ЯёЁ\\s-]+")) {
            return "Имя может содержать только буквы, пробелы и дефисы.";
        }

        if (age == null || !age.trim().matches("\\d+")) {
            return "Возраст должен быть целым числом.";
        }

        try {
            int ageValue = Integer.parseInt(age.trim());

            if (ageValue < 0 || ageValue > 100) {
                return "Возраст должен быть от 0 до 100.";
            }
        } catch (NumberFormatException e) {
            return "Некорректный возраст.";
        }

        if (weight == null || weight.trim().isEmpty()) {
            return "Введите вес животного.";
        }

        try {
            double weightValue = Double.parseDouble(weight.trim());

            if (!Double.isFinite(weightValue)
                    || weightValue <= 0
                    || weightValue > 10000) {
                return "Вес должен быть больше 0 и не превышать 10000 кг.";
            }
        } catch (NumberFormatException e) {
            return "Вес должен быть числом.";
        }

        return null;
    }
}