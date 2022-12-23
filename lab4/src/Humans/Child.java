package Humans;
import AbstractClasses.Human;
import AbstractClasses.Home;

import java.util.concurrent.ThreadLocalRandom;

public class Child extends Human {
    public Child(String name, Home home) {
        super(name, home);
    }

    @Override
    public void act() {
        if (this.getFullness() < 20)
            this.eat(20);

        int dice = ThreadLocalRandom.current().nextInt(0, 1+1);
        if (dice == 0)
            this.sleep();
        else
            this.lazinessAllDay();
    }

    private void sleep() {
        System.out.printf("%s лег спать.", this.getName());
        this.reduceFullness(5);
    }
}