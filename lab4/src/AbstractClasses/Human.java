package AbstractClasses;

import java.util.concurrent.ThreadLocalRandom;

abstract public class Human extends Creature {
    int happiness;

    public Human(String name, Home home) {
        super(name, home);
        this.happiness = 100;
    }

    @Override
    public String getStr() {
        return String.format("Я %s, моя сытость %d ед., моё счастье %d ед.",
                this.getName(), this.getFullness(), this.getHappiness());
    }

    public void eat(int numberSpacing) {
        System.out.printf("%s решает поесть", this.getName());
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

    public void PetPet() {
        int random = ThreadLocalRandom.current().nextInt(0, 100+1);
        System.out.printf("%s решает погладить ", this.getName());
        if (random > 50) {
            System.out.println("кота");
            this.home.pettedCat += 1;
        }
        else {
            System.out.println("собаку");
            this.home.pettedDog += 1;
        }
        this.happiness += 10;
    }

    public int getHappiness() {return this.happiness;}
    public void reduceHappiness(int num) {this.happiness -= num;}
    public void addHappiness(int num) {this.happiness += num;}
}