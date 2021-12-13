package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final int SIZE = 5;
    private static final int WIN_SIZE_VALUE = 3;

    private static final char DOT_EMPTY = '•';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = 'O';

    private static final char[][] MAP = new char[SIZE][SIZE];
    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();

    private static final String HEADER_FIRST_SYMBOL = "♥";
    private static final String SPACE_MAP = " ";

    private static int turnsCount;
    private static int rowNumber;
    private static int columnNumber;

    public static void turnGame() {
        do {
            System.out.println("\n\nИгра начинается!");
            init();
            printMap();
            playGame();
        } while (isContinueGame());
        endGame();
    }

    private static void init() {
        turnsCount = 0;
        initMap();
    }

    private static void initMap() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        printHeaderMap();
        printBodyMap();
    }

    private static void printHeaderMap() {
        System.out.print(HEADER_FIRST_SYMBOL + SPACE_MAP);
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
        }
        System.out.println();
    }

    private static void printMapNumber(int i) {
        System.out.print(i + 1 + SPACE_MAP);
    }

    private static void printBodyMap() {
        for (int i = 0; i < SIZE; i++) {
            printMapNumber(i);
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + SPACE_MAP);
            }
            System.out.println();
        }
    }


    private static void playGame() {

        while (true) {
            turnHuman();
            printMap();
            if (checkEnd(DOT_HUMAN)) {
                break;
            }

            turnAI();
            printMap();
            if (checkEnd(DOT_AI)) {
                break;
            }
        }

    }

    private static void turnHuman() {
        System.out.println("ХОД ЧЕЛОВЕКА");
//        int rowNumber, columnNumber;

        while (true) {
            rowNumber = getValidNumberFromUser() - 1;
            columnNumber = getValidNumberFromUser() - 1;
            if (isCellFree(rowNumber, columnNumber)) {
                break;
            }
            System.out.println("\nВы выбрали занятую ячейку!");
        }

        MAP[rowNumber][columnNumber] = DOT_HUMAN;
        turnsCount++;
    }

    private static int getValidNumberFromUser() {
        while (true) {
            System.out.print("Введите координату(1-" + SIZE + "): ");
            if (in.hasNextInt()) {
                int n = in.nextInt();
                if (isNumberValid(n)) {
                    return n;
                }
                System.out.println("\nПроверьте значение координаты. Должно быть от 1 до " + SIZE);
            } else {
                in.next();
                System.out.println("\nВвод допускает лишь целые числа!");
            }
        }
    }

    private static boolean isNumberValid(int n) {
        return n >= 1 && n <= SIZE;
    }

    private static boolean isCellFree(int rowNumber, int columnNumber) {
        return MAP[rowNumber][columnNumber] == DOT_EMPTY;
    }

    private static void turnAI() {
        System.out.println("ХОД КОМУКТЕРА");
//        int rowNumber, columnNumber;

        do {
            rowNumber = random.nextInt(SIZE);
            columnNumber = random.nextInt(SIZE);

        } while (!isCellFree(rowNumber, columnNumber));

        MAP[rowNumber][columnNumber] = DOT_AI;
        turnsCount++;
    }

    private static boolean checkEnd(char symbol) {
        if (checkWin(symbol)) {
            if (symbol == DOT_HUMAN) {
                System.out.println("\nУраааааааааааааа!! Вы победили!");
            } else {
                System.out.println("\nВосстание близко... ИИ победил");
            }
            return true;
        }

        if (checkDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    private static boolean checkDraw() {
        return turnsCount >= SIZE * SIZE;

    }

    private static boolean checkWin(char symbol) {
        if ((turnsCount + 1) / 2 >= WIN_SIZE_VALUE) {
            if (checkRows(rowNumber, symbol)) {
                return true;
            }
            if (checkColumns(columnNumber, symbol)) {
                return true;
            }
            if (checkDiagonals(rowNumber, columnNumber, symbol)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isCheckContinue(int index, int counter) {
        return ((MAP.length - index) >= (WIN_SIZE_VALUE - counter));
    }

    private static boolean checkRows(int index, char symbol) {
        return checkMethod(index, 1, symbol);
//        int winCounter = 0;
//        for (int i = 0; i < MAP.length; i++) {
//            if (checkDimension(i, winCounter)) {
//                if (MAP[index][i] == symbol) {
//                    winCounter++;
//                    if (winCounter == WIN_SIZE_VALUE) {
//                        return true;
//                    }
//                } else {
//                    winCounter = 0;
//                }
//            } else {
//                break;
//            }
//        }
//        return false;
    }


    private static boolean checkColumns(int index, char symbol) {
        return checkMethod(index, 2, symbol);
//        int winCounter = 0;
//        for (int i = 0; i < MAP.length; i++) {
//            if (checkDimension(i, winCounter)) {
//                if (MAP[i][index] == symbol) {
//                    winCounter++;
//                    if (winCounter == WIN_SIZE_VALUE) {
//                        return true;
//                    }
//                } else {
//                    winCounter = 0;
//                }
//            } else {
//                break;
//            }
//        }
//        return false;
    }

    private static boolean checkDiagonals(int rowNumber, int columnNumber, char symbol) {
        int winCounter = 0;
        int delta = rowNumber - columnNumber;
        int sumIndex = rowNumber + columnNumber;

        if (delta >= 0) {
//            return checkMethod(0, 3, symbol);
            for (int i = 0; i < MAP.length - delta; i++) {
                if (checkDimension(i, winCounter)) {
                    if (MAP[i + delta][i] == symbol) {
                        winCounter++;
                        if (winCounter == WIN_SIZE_VALUE) {
                            return true;
                        }
                    } else {
                        winCounter = 0;
                    }
                } else {
                    break;
                }
            }
            if (checkOverlap(sumIndex, symbol)) {
                return true;
            }
        } else {
//            return checkMethod(0, 4, symbol);
        }
        for (int i = 0; i < MAP.length + delta; i++) {
            if (MAP[i][i - delta] == symbol) {
                winCounter++;
                if (winCounter == WIN_SIZE_VALUE) {
                    return true;
                }
            } else {
                winCounter = 0;
            }
        }


        if (checkOverlap(sumIndex, symbol)) {
            return true;
        }
        return false;
    }


    private static boolean checkMethod(int index, int flag, char symbol) {
        int winCounter = 0;
        char c = '?';
        int endCycle = MAP.length;
        int delta = rowNumber - columnNumber;
        int sumIndex = rowNumber + columnNumber;
        switch (flag) {
            case 3: {
                endCycle = MAP.length - delta;
                break;
            }
            case 4: {
                endCycle = MAP.length + delta;
                break;
            }
        }
        for (int i = 0; i < endCycle; i++) {
            switch (flag) {
                case 1: {
                    c = MAP[index][i];
                    break;
                }
                case 2: {
                    c = MAP[i][index];
                    break;
                }
                case 3: {
                    c = MAP[i + delta][i];
                    break;
                }
                case 4: {
                    c = MAP[i][i - delta];
                    break;
                }
            }
            if (checkDimension(i, winCounter)) {
                if (c == symbol) {
                    winCounter++;
                    if (winCounter == WIN_SIZE_VALUE) {
                        return true;
                    }
                } else {
                    winCounter = 0;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean shiftReverseTop(int j, char symbol) {
        int winCounter = 0;
        for (int i = 0; i <= j; i++) {
            if (MAP[i][j - i] == symbol) {
                winCounter++;
                if (winCounter == WIN_SIZE_VALUE) {
                    return true;
                }
            } else {
                winCounter = 0;
            }
        }
        return false;
    }

    private static boolean checkDimension(int i, int winCounter) {
        return (MAP.length - i >= WIN_SIZE_VALUE - winCounter);
    }

    private static boolean checkOverlap(int sumIndex, char symbol) {

        int winCounter = 0;
        if (sumIndex <= MAP.length - 1) {
            if (shiftReverseTop(sumIndex, symbol)) {
                return true;
            }
        } else {
            int k = 2 * (MAP.length - 1) - sumIndex;
            for (int i = 0; i <= k; i++) {
                if (MAP[sumIndex - (MAP.length - 1) + i][MAP.length - 1 - i] == symbol) {
                    winCounter++;
                    if (winCounter == WIN_SIZE_VALUE) {
                        return true;
                    }
                } else {
                    winCounter = 0;
                }
            }
        }
        return false;
    }

    private static boolean isContinueGame() {
        System.out.println("Хотите продолжить? y\\n");
        return switch (in.next()) {
            case "y", "yes", "+", "да", "конечно" -> true;
            default -> false;
        };
    }

    private static void endGame() {
        in.close();
        System.out.println("Ты заходи, если что");
    }
}

