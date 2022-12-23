package Pets;
import AbstractClasses.Home;
import AbstractClasses.Pet;

import java.util.concurrent.ThreadLocalRandom;

public class Cat extends Pet {
    public Cat(String name, String type, Home home) {
        super(name, type, home);
    }

    @Override
    public void act() {
        if (this.getFullness() <= 0) {
            System.out.printf("%s %s умер...\n", this.getType(), this.getName());
            return;
        }
        else if (this.getFullness() <= 25) {
            this.eat(ThreadLocalRandom.current().nextInt(15, 30+1));
        }

        int dice = ThreadLocalRandom.current().nextInt(0, 2+1);
        if (dice == 0)
            this.eat(ThreadLocalRandom.current().nextInt(8, 15+1));
        else if (dice == 1)
            this.sleep();
        else
            this.soil();
    }
}