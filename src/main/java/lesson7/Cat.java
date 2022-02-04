package lesson7;

public class Cat {
    private String name;
    private int appetite = 0;
    private int satiety = 0;
    private boolean catHungry = true;

    public Cat(String name, int appetite, int satiety) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = satiety;
        this.catHungry = true;
    }

    public Cat() {

    }


    public void eat(Plate[] plates) {
        int stillHungry = 0;
        while (catHungry){
        for (int i = 0;  i < plates.length; i++) {
            stillHungry = plates[i].decreaseFood(appetite-satiety);
            if (stillHungry == 0) {
                satiety = appetite;
                catHungry = false;
                break;
            } else {
                satiety = appetite - stillHungry;
                catHungry = true;
            }
        }
        break;
    }
    }

        public void catInfo () {
            System.out.println(toString());
        }

        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public int getAppetite () {
            return appetite;
        }

        public void setAppetite ( int appetite){
            this.appetite = appetite;
        }

        public int getSatiety () {
            return satiety;
        }

        public void setSatiety ( int satiety){
            this.satiety = satiety;
        }
//
//


    public boolean isCatHungry() {
        return catHungry;
    }

    public void setCatHungry(boolean catHungry) {
        this.catHungry = catHungry;
    }

    @Override
    public String toString() {
        String str = catHungry ? "всё ещё голоден!!!" : "наелся.";
        return String.format ("%s %s Сытость %d из %d ",getName(),str,getSatiety(),getAppetite());
    }

}