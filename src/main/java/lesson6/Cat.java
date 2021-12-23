package lesson6;

public class Cat extends Animal {
    final int LIMIT_OF_RUN = 200;

    private static int catsCount = 0;

    protected String color;
    protected String breed;


    public Cat(String name, int age, String color, String breed) {
        super(name, age, "Котяра");
        this.color = color;
        this.breed = breed;

        catsCount++;
    }

    public static int getCatsCount() {
        return catsCount;
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
    public void swim(int distance) {
        System.out.println(this + "может и хотел бы проплыть " + distance + " метров, но плавать не УМЕЕТ!!! \n Утонул котейка... 'Котов душили-душили...(copyright не мой)' ");
    }

    @Override
    public void run(int distance) {
        if (distance <= LIMIT_OF_RUN) {
            System.out.println(this + "пробежал " + distance + " метров.");
        } else {
            System.out.println(this + " пробежал " + LIMIT_OF_RUN + " метров из " + distance + ". \n ФСЁ, дальше сам беги!!!");
        }
        ;
    }

    @Override
    public String toString() {
        String str = (getAge() == 1) ? "год" : "лет";
        return String.format(getKind() + " по кличке %s, %d %s от роду, окрас %s, порода '%s' ", getName(), getAge(), str, getColor(), getBreed());
    }
}
