package Humans;
import AbstractClasses.Human;
import AbstractClasses.Home;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Child extends Human {
    public Child(String name, Home home, JTextArea logs) {
        super(name, home, logs);
    }

    @Override
    public void act() {
        if (this.getFullness() <= 0 || this.getHappiness() <= 10) {
            this.logs.append(String.format("%s умер...\n", this.getName()));
            return;
        }

        if (this.getFullness() < 20)
            this.eat(20);

        int dice = ThreadLocalRandom.current().nextInt(0, 1+1);
        if (dice == 0)
            this.sleep();
        else
            this.lazinessAllDay();
    }

    private void sleep() {
        this.logs.append(String.format("%s лег спать.\n", this.getName()));
        this.reduceFullness(5);
    }
}