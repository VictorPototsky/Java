package lesson5;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Employee[] empAr = new Employee[5];
        empAr[0] = new Employee("Петров Александр", "Директор", "boss@mail.ru", "111-11-11", 300, 50);
        empAr[1] = new Employee("Михалкова Надежда", "Главный бухгалтер", "gross@mail.ru", "222-22-22", 250, 45);
        empAr[2] = new Employee("Антонова Светлана", "Секретарь", "office@mail.ru", "100-10-10", 100, 19);
        empAr[3] = new Employee("Борисов Дмитрий", "Водитель", "drive@mail.ru", "213-23-23", 170, 42);
        empAr[4] = new Employee("Ильин Владимир", "Начальник отдела", "chief@mail.ru", "310-10-20", 210, 38);

        for (int i = 0; i < empAr.length; i++) {
            if (empAr[i].age > 40) {
                empAr[i].printData();
            }
        }
    }
}