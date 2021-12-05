package lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class HomeWorkApp3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = {2, 4, 5, 3, 8, 5, 1};
        // Task 1
        invertBinaryArray();
        // Task 2
        fillArray();
        // Task 3
        multiplyArrayItems();
        // Task 4
        squareArray(5);
        // Task 5
        createArray(10, 7);
        // Task 6
        extrArrayItems();
        // Task 7
        System.out.println(compareSumArrayItems(arr));
        // Task 8
        System.out.println("Начальный массив :");
        System.out.println(Arrays.toString(arr));
        System.out.print("Введите число сдвигов элементов массива : ");
        int count = scan.nextInt();
        System.out.println("Массив после сдвига:");
        shiftIntoArray(arr, count);
        System.out.println(Arrays.toString(arr));
        scan.close();

    }

    private static void invertBinaryArray() {
        int[] data = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0};
        System.out.println("Заданный массив : " + Arrays.toString(data));
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i] == 0 ? 1 : 0;
            /* switch (data[i]) {
                case 0:
                    data[i] = 1;
                    break;
                case 1:
                    data[i] = 0;
                    break;
            } */
        }
        System.out.println("Преобразованный массив : " + Arrays.toString(data));
        }

    private static void fillArray() {
        int[] data = new int[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1;
        }
        System.out.println(Arrays.toString(data));
    }

    private static void multiplyArrayItems() {
        int[] data = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        int[] data1 = data;
        System.out.println("Исходный массив : " + Arrays.toString(data));
        for (int i = 0; i < data1.length; i++) {
            if (data1[i] < 6) {
                data1[i] *= 2;
            }
        }
        System.out.println("Преобразованный массив : " + Arrays.toString(data1));
    }

    private static void squareArray(int len) {
        int[][] sqArr = new int[len][len];
        for (int i = 0; i < sqArr.length; i++) {
            for (int j = 0; j < sqArr.length; j++) {
                if (i == j || i == sqArr.length - j - 1) {
                    sqArr[i][j] = 1;
                }
                System.out.print(sqArr[i][j] + "  ");
            }
            System.out.println();

        }

    }

    private static int[] createArray(int len, int initialValue) {
        int[] data = new int[len];
        for (int i = 0; i < data.length; i++) {
            data[i] = initialValue;
        }
        System.out.println("Полученный массив из " + len + " элементов со значением " + initialValue);
        System.out.println(Arrays.toString(data));
        return data;
    }

    private static void extrArrayItems() {
        int[] data = {2, 5, 1, 7, -5, 21, 94, -3, 43, -35};
        int[] sortArr = data;
        System.out.println("Начальный массив");
        System.out.println(Arrays.toString(data));
        Arrays.sort(sortArr);
        System.out.println("Минимальный элемент массива :" + sortArr[0]);
        System.out.println("Максимальный элемент массива :" + sortArr[sortArr.length - 1]);
        System.out.println("Отсортированный по возрастанию массив");
        System.out.println(Arrays.toString(sortArr));
    }


    private static boolean compareSumArrayItems(int[] arr) {
        int sumPrev = 0;
        int sumPost = 0;
        for (int i = 0; i < arr.length; i++) {
            sumPrev += arr[i];
            sumPost = 0;
            for (int j = i + 1; j < arr.length; j++) {
                sumPost += arr[j];
            }
            if (sumPrev == sumPost) {
                System.out.println("В массиве есть место, в котором сумма левой и правой части массива равны:");
                int[] leftArr = new int[i + 1];
                int[] rightArr = new int[arr.length - leftArr.length];
                for (int k = 0; k <= i; k++) {
                    leftArr[k] = arr[k];
                }
                for (int j = i; j < arr.length - 1; j++) {
                    rightArr[j - i] = arr[j + 1];
                }
                System.out.println(Arrays.toString(leftArr) + " ||| " + Arrays.toString(rightArr));
                return true;
            }
        }
        System.out.println("В массиве нет места, в котором сумма левой и правой части массива равны.");
        return false;
    }

    private static void shiftIntoArray(int[] arr, int n) {
        if (n < 0) {
            for (int i = n; i < 0; i++) {
                oneStepLeftShift(arr);
            }
        } else {
            for (int i = 0; i < n; i++) {
                oneStepRightShift(arr);
            }
        }
    }


    private static void oneStepRightShift(int[] arr) {
        int a = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = a;
    }

    private static void oneStepLeftShift(int[] arr) {
        int a = arr[0];
        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = a;
    }
}
