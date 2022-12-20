package AbstractClasses;

import java.util.concurrent.ThreadLocalRandom;

abstract public class Human {
    String name;
    int fullness;
    int happiness;
    Home home;

    public Human(String name, Home home) {
        this.name = name;
        this.fullness = 30;
        this.happiness = 100;
        this.home = home;
    }

    public String getStr() {
        return String.format("Я %s, моя сытость %d ед., моё счастье %d ед.",
                this.name, this.fullness, this.happiness);
    }

    public void act() {}

    public void eat(int numberSpacing) {
        System.out.printf("%s решает поесть", this.name);
        int randomEd = numberSpacing;
        if (this.getHome().getEat() >= randomEd) {
            this.addFullness(randomEd);
            this.getHome().reduceEat(randomEd);

            this.getHome().addEatenFood(randomEd);
            System.out.print("\n");
        }
        else {
            System.out.println(", но дома нет еды.");
        }
    }

    public void lazinessAllDay() {
        System.out.printf("%s решает ничего сегодня не делать.\n", this.name);
        this.fullness -= 10;
    }

    public void PetPet() {
        int random = ThreadLocalRandom.current().nextInt(0, 100+1);
        System.out.printf("%s решает погладить ", this.name);
        if (random > 50) {
            System.out.println("кота");
            this.home.pettedCat += 1;
        }
        else {
            System.out.println("собаку");
            this.home.pettedDog += 1;
        }
        this.happiness += 5;
    }

    public String getName() {return this.name;}
    public int getHappiness() {return this.happiness;}
    public void reduceHappiness(int num) {this.happiness -= num;}
    public void addHappiness(int num) {this.happiness += num;}
    public int getFullness() {return this.fullness;}
    public void reduceFullness(int num) {this.fullness -= num;}
    public void addFullness(int num) {this.fullness += num;}
    public Home getHome() {return this.home;}
}