package Humans;
import AbstractClasses.Human;
import AbstractClasses.Home;

import java.util.concurrent.ThreadLocalRandom;

public class Husband extends Human {
    int bonusesReceived = 0;
    int playedOnPc = 0;

    public Husband(String name, Home home) {
        super(name, home);
    }

    @Override
    public void act() {
        if (this.getFullness() <= 0 || this.getHappiness() <= 10) {
            System.out.printf("%s умер...\n", this.getName());
            return;
        }
        else if (this.getFullness() <= 25) {
            this.eat(ThreadLocalRandom.current().nextInt(15, 30+1));
        }

        this.getHome().addDirt(4);

        if (getHome().getDirt() >= 90)
            this.reduceHappiness(10);

        int dice = ThreadLocalRandom.current().nextInt(0, 2+1);
        if (getHome().getMoney() < 400)
            this.work();
        else if (dice == 0)
            this.gaming();
        else if (dice == 1)
            this.PetPet();
        else
            this.lazinessAllDay();
    }


    private void work() {
        System.out.printf("В доме мало денег, поэтому %s пошёл на работу.", this.getName());
        this.getHome().addMoney(150);
        this.reduceHappiness(10);
        this.reduceFullness(10);

        int random = ThreadLocalRandom.current().nextInt(0, 100+1);
        if (random <= 10) {
            System.out.print(" На работе выдали премию!");
            this.getHome().addMoney(500);
            this.addHappiness(35);
            this.bonusesReceived += 1;
        }

        System.out.print("\n");
    }

    private void gaming() {
        System.out.printf("%s сел в свой т-34 и поехал играть в WoT.\n", this.getName());
        this.reduceFullness(10);
        this.addHappiness(20);
        this.playedOnPc += 1;
    }

    public int getBonusesReceived() {return this.bonusesReceived;}
    public int getPlayedOnPc() {return this.playedOnPc;}
}