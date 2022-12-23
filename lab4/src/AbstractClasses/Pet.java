package AbstractClasses;

import java.util.concurrent.ThreadLocalRandom;

abstract public class Pet extends Creature {
    String type;

    public Pet(String name, String type, Home home) {
        super(name, home);
        this.type = type;
    }

    public void eat(int numberSpacing) {
        System.out.printf("%s %s решает поесть", this.getType(), this.getName());
        int randomEd = numberSpacing;
        if (this.getHome().getPetFood() >= randomEd) {
            this.addFullness(randomEd);
            this.getHome().reducePetFood(randomEd);

            this.getHome().addEatenPetFood(randomEd);
            System.out.print("\n");
        }
        else {
            System.out.println(", но дома нет еды.");
        }
    }

    public void sleep() {
        System.out.printf("%s %s проспал весь день.\n", this.getType(), this.getName());
        this.reduceFullness(10);
    }

    public void soil() {
        System.out.printf("%s %s попортил ", this.getType(), this.getName());
        int dice = ThreadLocalRandom.current().nextInt(0, 1+1);
        if (dice == 0)
            System.out.print("обои");
        else
            System.out.print("мебель");
        System.out.print(" в доме. Вот проказник!\n");
        this.reduceFullness(10);
        this.getHome().addDirt(5);
        this.getHome().addDamagedFurniture(1);
    }

    public String getType() {return this.type;}
    @Override
    public String getStr() {return this.type + ": " + super.getStr();}
}