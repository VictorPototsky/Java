package lesson2;

public class HomeWorkApp2 {
    public static void main(String[] args) {
        System.out.println(isSumBetween(-1, 12));
        isInputNumberPositive(-50);
        System.out.println(isNumberPositive(55));
        printText("Какой хороший день!", 3);
        System.out.println(isLeapYear(1948));
    }

    public static boolean isSumBetween(int a, int b) {
        return (a + b >= 10 && a + b <= 20);
//        return (a + b >= 10 && a + b <= 20 ? true : false);
    }

    public static void isInputNumberPositive(int a) {
        System.out.println("Число " + a + (a >= 0 ? " положительное" : " отрицательное"));
    }

    public static boolean isNumberPositive(int inputNumber) {
        return (inputNumber >= 0 ? true : false);
    }

    public static void printText(String inputStr, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(inputStr);
        }
    }

    public static boolean isLeapYear(int whatYear) {
        if (whatYear % 4 != 0 || (whatYear % 100 == 0 && whatYear % 400 != 0)) {
            System.out.println(whatYear + " не високосный");
            return false;
        } else {
            System.out.println(whatYear + " високосный");
            return true;
        }
    }
}

