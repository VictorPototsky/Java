package lesson7;


import java.util.Random;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        final int QUONTITY_OF_PLATES = 3;
        final int HOW_MANY_CATS = 4;

        boolean isContinue = false;

        int[] tmpArr = new int[Math.max(QUONTITY_OF_PLATES, HOW_MANY_CATS)];


        Plate[] plates = new Plate[QUONTITY_OF_PLATES];

        plates[0] = new Plate("Разбитое корыто", 1, 0);
        plates[1] = new Plate("Блюдечко с голубой каёмочкой", 4, 0);
        plates[2] = new Plate("Бездонная бочка", 10, 0);

        Cat[] cats = new Cat[HOW_MANY_CATS];


        cats[0] = new Cat("Леопольд", 4, 0);
        cats[1] = new Cat("Гарфилд", 5, 0);
        cats[2] = new Cat("Чеширский кот", 7, 0);
        cats[3] = new Cat("Котёнок Гав", 1, 0);


        initPlates(plates);
        System.out.println("   Feed my Frankenstein\n" + "   Кис-кис-кис...\n");
        System.out.println("Выстраиваем котов в очередь для принятия пищи! И коты начинают есть в следующем порядке...\n");
        templateQueue(HOW_MANY_CATS, tmpArr);
        cats=doQueueCats(cats, tmpArr);
        for (Cat cat:cats) {
            System.out.println(cat.getName());
        }
        System.out.println("\n Коты поели, результат : \n ");
         for (Cat kitty : cats) {
            kitty.eat(plates);
            kitty.catInfo();
            if (kitty.isCatHungry()) {
                isContinue = true;
            }
        }
        System.out.println();
        for (Plate p : plates) {
            p.plateInfo();
        }

    }

    public static void addFoodToPlates(Plate[] plates) {
        int howMuchIsTheFood = 0;
        howMuchIsTheFood = new Random().nextInt(7,10);
        System.out.println("Добавляем корм. Общее количество: " + howMuchIsTheFood);
        for (int i = 0; i < plates.length; i++) {
            plates[i].fillThePlate(howMuchIsTheFood);
            if (howMuchIsTheFood <= 0) {
                break;
            } else {
                howMuchIsTheFood -= plates[i].getFood();
            }
        }
        return;
    }

    public static void initPlates(Plate[] plates) {
        addFoodToPlates(plates);
        System.out.println("Состояние тарелок :");
        for (Plate plate : plates) {
            plate.plateInfo();
        }
    }

    public static void templateQueue(int n, int[] tmpArr) {
        fillArray(tmpArr);
        int pos = 0;
        int value = 0;
        for (int i = 0; i < tmpArr.length; i++) {
            value = pickValue(n);
            while (isInside(value, tmpArr)) {
                value = pickValue(n);
            }
            tmpArr[i] = value;
        }
    }

    private static boolean isInside(int value, int[] tmpArr) {
        for (int i = 0; i < tmpArr.length; i++) {
            if (tmpArr[i] == value) {
                return true;
            }
        }
        return false;
    }

    private static int pickValue(int n) {
        return new Random().nextInt(n);
    }

    private static void fillArray(int[] tmpArr) {
        for (int i = 0; i < tmpArr.length; i++) {
            tmpArr[i] = -1;
        }
    }

    private static Cat[] doQueueCats(Cat[] cats, int[] tmpArr) {
        Cat m = new Cat();
        for (int i = 0; i < cats.length; i++) {
            m = cats[i];
            cats[i] = cats[tmpArr[i]];
            cats[tmpArr[i]]=m;
        }
        return cats;
    }
}


