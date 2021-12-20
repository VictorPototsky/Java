package lesson6;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {

        Dog dog1 = new Dog("Артемон", 3, "Чёрный", "Пудель");
        Dog dog2 = new Dog("Ваучер", 2, "Рыжий", "Дворняга");
        Cat cat1 = new Cat("Баюн", 1, "Пёстрый", "Мейн-кун");
        Animal animal1 = new Animal("Балбес", 50, "Человек");
        Animal animal2 = new Cat("Бегемот", 2100, "Чёрный", "Мистическая");

        animal1.swim(200);
        animal1.run(1500);
        cat1.swim(20);
        cat1.run(250);
        dog1.swim(12);
        dog1.run(600);
        dog2.swim(6);
        dog2.run(450);
        if (animal2 instanceof Cat) {
            ((Cat) animal2).swim(50);
            ((Cat) animal2).run(1000);
        }

        System.out.println("Общее количество животных : " + Animal.getAnimalCount());
        System.out.println("Из них количество котов: " + Cat.getCatsCount() + ",количество собак: " + Dog.getDogsCount() + ".");


    }
}

