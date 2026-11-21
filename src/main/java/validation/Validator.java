package validation;

public class Validator {

    // Проверка числовых данных при ручном вводе
    public static String validateAnimal(String age, String weight) {

        // Проверка возраста
        if (age == null || age.trim().isEmpty()) {
            return "Введите возраст животного.";
        }

        if (!age.trim().matches("\\d+")) {
            return "Возраст должен быть целым числом.";
        }

        try {
            Integer.parseInt(age.trim());
        } catch (NumberFormatException e) {
            return "Некорректный возраст.";
        }

        // Проверка веса
        if (weight == null || weight.trim().isEmpty()) {
            return "Введите вес животного.";
        }

        try {
            double weightValue = Double.parseDouble(weight.trim());

            if (!Double.isFinite(weightValue)) {
                return "Вес должен быть конечным числом.";
            }

        } catch (NumberFormatException e) {
            return "Вес должен быть числом.";
        }

        return null;
    }


    // Проверка длины массива
    public static String validateArrayLength(String length) {

        if (length == null || length.trim().isEmpty()) {
            return "Введите длину массива.";
        }

        if (!length.trim().matches("\\d+")) {
            return "Длина массива должна быть целым числом.";
        }

        try {
            int value = Integer.parseInt(length.trim());

            if (value < 1 || value > 1000) {
                return "Длина массива должна быть от 1 до 1000.";
            }

        } catch (NumberFormatException e) {
            return "Некорректная длина массива.";
        }

        return null;
    }


    // Проверка формата файла
    public static String validateFileFormat(String fileName) {

        if (fileName == null || fileName.trim().isEmpty()) {
            return "Файл не выбран.";
        }

        if (!fileName.trim().toLowerCase().endsWith(".csv")) {
            return "Поддерживаются только CSV-файлы.";
        }

        return null;
    }
}