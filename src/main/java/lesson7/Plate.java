package lesson7;

public class Plate {
    String brand;
    int capacity = 0;
    int food = 0;

    public Plate(String brand, int capacity, int food) {
        this.brand = brand;
        this.capacity = capacity;
        this.food = food;
    }

    public void plateInfo() {
       System.out.println(toString());
    }

    public int decreaseFood(int quontity) {
        int stillHungry=quontity-food;
        if (stillHungry>0){
            food = 0;
            return stillHungry;
        } else{
            food -= quontity;
            return 0;
        }

    }

    public void fillThePlate(int quontity) {
        int overFood = quontity - (getCapacity() - getFood());
        if (overFood > 0) {
            setFood(getCapacity());
          } else {
            setFood(getFood()+quontity);
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public String getBrand() {
        return brand;
    }

    public void setName(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return String.format("В тарелке '%s' в настоящий момент количество корма %d из %d.", getBrand(),getFood(), getCapacity());
    }
}
