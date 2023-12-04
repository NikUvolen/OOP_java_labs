package AbstractClasses;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Human extends Creature {
    int happiness;

    public Human(String name, Home home, JTextArea logs) {
        super(name, home, logs);
        this.happiness = 100;
    }

    @Override
    public String getStatus() {
        return String.format("Я %s, моя сытость %d ед., моё счастье %d ед.",
                this.getName(), this.getFullness(), this.getHappiness());
    }

    public void eat(int numberSpacing) {
        this.logs.append(String.format("%s решает поесть", this.getName()));
        int randomEd = numberSpacing;
        if (this.getHome().getEat() >= randomEd) {
            this.addFullness(randomEd);
            this.getHome().reduceEat(randomEd);

            this.getHome().addEatenFood(randomEd);
            this.logs.append("\n");
        }
        else {
            this.logs.append(", но дома нет еды.\n");
        }
    }

    void PetCat() {
        logs.append("кота\n");
        this.getHome().pettedCat += 1;
    }

    void PetDog() {
        this.logs.append("собаку\n");
        this.getHome().pettedDog += 1;
    }

    public void PetPet() {
        int random = ThreadLocalRandom.current().nextInt(0, 100+1);
        this.logs.append(String.format("%s решает погладить ", this.getName()));
        if (this.getHome().isHaveCat() && !this.getHome().isHaveDog()) {
            PetCat();
        }
        else if (!this.getHome().isHaveCat() && this.getHome().isHaveDog()) {
            PetDog();
        }
        else {
            if (random > 50) {
                PetCat();
            }
            else {
                PetDog();
            }
        }
        this.happiness += 10;
    }

    public int getHappiness() {return this.happiness;}
    public void reduceHappiness(int num) {this.happiness -= num;}
    public void addHappiness(int num) {this.happiness += num;}
}