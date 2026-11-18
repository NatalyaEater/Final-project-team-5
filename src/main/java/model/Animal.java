package model;
import java.util.Objects;
public class Animal implements Comparable<Animal> {
    private final String name;
    private final int age;
    private final double weight;

    private Animal(Builder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.weight = builder.weight;
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
    public int compareTo(Animal other){
        int nameCompare = this.name.compareToIgnoreCase(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        int ageCompare = Integer.compare(this.age, other.age);
        if (ageCompare != 0) {
            return ageCompare;
        }
        return Double.compare(this.weight, other.weight);
    }

    @Override 
    public boolean equals(Object o){
        if(this==o){
            return true;
        }

        if(o==null || getClass()!=o.getClass()){
            return false;
        }

        Animal animal=(Animal) o;

        return age==animal.age && Double.compare(animal.weight, weight)==0 && Objects.equals(name,animal.name);
    }

    @Override
    public String toString(){
        return String.format("Animal{name='%s', age=%d, weight=%.2f}",name,age,weight);
    }
    
    @Override 
    public int hashCode(){
        return java.util.Objects.hash(name, age, weight);
    }
    public static class Builder{
        private String name;
        private int age;
        private double weight;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public Animal build(){
            validate();
            return new Animal(this);
        }
        private void validate(){
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Имя животного не может быть пустым.");
            }

            if (!name.matches("[a-zA-Zа-яА-ЯёЁ\\s-]+")) {
                throw new IllegalArgumentException("Имя может содержать только буквы, пробелы и дефисы.");
            }

            if (age < 0 || age > 100) {
                throw new IllegalArgumentException("Возраст должен быть в диапазоне от 0 до 100 лет.");
            }

            if (weight <= 0.0 || weight > 10000.0) {
                throw new IllegalArgumentException("Вес должен быть положительным и не превышать 10 000 кг.");
            }
        }
    }
}
