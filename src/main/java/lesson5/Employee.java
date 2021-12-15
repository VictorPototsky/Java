package lesson5;

public class Employee {
    String name;
    String position;
    String email;
    String phone;
    int salary;
    int age;

    public Employee(String name, String position, String email, String phone, int salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String toString() {
        return String.format("ФИО : %s, Должность : %s, E-mail : %s, Номер телефона : %s, Заработная плата : %d, Возраст : %d", name, position, email, phone, salary, age);
    }

    public void printData() {
        System.out.println(this);
    }


}
