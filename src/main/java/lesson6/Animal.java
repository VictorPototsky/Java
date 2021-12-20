package lesson6;

public class Animal {
    protected static int animalCount = 0;

    String name;
    int age;
    String kind;

    public static int getAnimalCount() {
        return animalCount;
    }

    public Animal(String name, int age, String kind) {
        this.name = name;
        this.age = age;
        this.kind = kind;

        animalCount++;
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Animal(String name, String kind) {
        this.name = name;
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getKind() {
        return kind;
    }

    public void run(int distance) {
        String str = "пробежало " + distance + " метров.";
        System.out.println(this + str);
    }

    public void swim(int distance) {
        String str = "проплыло " + distance + " метров.";
        System.out.println(this + str);
    }

    @Override
    public String toString() {
        String str = ((this.age == 1) ? "года" : "лет");
        return String.format("Животное по кличке %s (%s), %d %s от роду ", getName(), getKind(), getAge(), str);
    }

}