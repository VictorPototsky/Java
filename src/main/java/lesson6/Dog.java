package lesson6;

public class Dog extends Animal {
    final int LIMIT_OF_SWIM = 10;
    final int LIMIT_OF_RUN = 500;

    private static int dogsCount = 0;

    private String color;
    private String breed;


    public Dog(String name, int age, String color, String breed) {
        super(name, age, "Пёс");
        this.color = color;
        this.breed = breed;

        dogsCount++;
    }

    public static int getDogsCount() {
        return dogsCount;
    }

    public String getColor() {
        return color;
    }

//    public void setColor(String color) {
//        this.color = color;
//    }

    public String getBreed() {
        return breed;
    }

//    public void setBreed(String breed) {
//        this.breed = breed;
//    }

    @Override
    public String toString() {
        String str = (getAge() == 1) ? "год" : "лет";
        return String.format(getKind() + " по кличке %s, %d %s от роду, окрас %s, порода '%s' ", getName(), getAge(), str, getColor(), getBreed());
    }

    @Override
    public void swim(int distance) {
        if (distance <= LIMIT_OF_SWIM) {
            System.out.println(this + "проплыл " + distance + " метров.");
        } else {
            System.out.println(this + " проплыл " + LIMIT_OF_SWIM + " метров из " + distance + ", выбился из сил и дальше плыть не может!!! \n SOS!!! Собака может утонуть!");
        }
    }

    @Override
    public void run(int distance) {
        if (distance <= LIMIT_OF_RUN) {
            System.out.println(this + "пробежал " + distance + " метров.");
        } else {
            System.out.println(this + " пробежал " + LIMIT_OF_RUN + " метров из " + distance + ",\n  выбился из сил и дальше бежать не может!!!");
        }
    }
}