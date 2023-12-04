package AbstractClasses;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Pet extends Creature {
    String type;

    public Pet(String name, String type, Home home, JTextArea logs) {
        super(name, home, logs);
        this.type = type;
    }

    public void eat(int numberSpacing) {
        logs.append(String.format("%s %s решает поесть", this.getType(), this.getName()));
        int randomEd = numberSpacing;
        if (this.getHome().getPetFood() >= randomEd) {
            this.addFullness(randomEd);
            this.getHome().reducePetFood(randomEd);

            this.getHome().addEatenPetFood(randomEd);
            logs.append("\n");
        }
        else {
            logs.append(", но дома нет еды.\n");
        }
    }

    public void sleep() {
        logs.append(String.format("%s %s проспал весь день.\n", this.getType(), this.getName()));
        this.reduceFullness(10);
    }

    public void soil() {
        logs.append(String.format("%s %s попортил ", this.getType(), this.getName()));
        int dice = ThreadLocalRandom.current().nextInt(0, 1+1);
        if (dice == 0)
            logs.append("обои");
        else
            logs.append("мебель");
        logs.append(" в доме. Вот проказник!\n");
        this.reduceFullness(10);
        this.getHome().addDirt(5);
        this.getHome().addDamagedFurniture(1);
    }

    public String getType() {return this.type;}
    @Override
    public String getStatus() {return this.type + ": " + super.getStatus();}
}