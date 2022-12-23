package Humans;

import AbstractClasses.Human;
import AbstractClasses.Home;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class Wife extends Human{
    int shoppingTrips = 0;
    int totalCleanings = 0;

    public Wife(String name, Home home, JTextArea logs) {super(name, home, logs);}

    @Override
    public void act() {
        if (this.getFullness() <= 0 || this.getHappiness() <= 10) {
            this.logs.append(String.format("%s умерла...\n", this.getName()));
            return;
        }
        else if (this.getFullness() <= 25) {
            this.eat(ThreadLocalRandom.current().nextInt(15, 30+1));
        }

        this.getHome().addDirt(2);

        if (getHome().getDirt() >= 90)
            this.reduceHappiness(10);

        int dice = ThreadLocalRandom.current().nextInt(0, 2+1);
        if (this.getHome().getEat() < 50)
            this.buyFood("human");
        else if (this.getHome().getPetFood() < 30)
            this.buyFood("pets");
        else if (this.getHome().getDirt() >= 30)
            this.cleanHouse();
        else if (dice == 0)
            this.shopping();
        else if (dice == 1 && (this.getHome().isHaveCat() || this.getHome().isHaveDog()))
            this.PetPet();
        else
            this.lazinessAllDay();
    }

    private void shopping() {
        this.logs.append(String.format("%s пошла пошопиться с подругами", this.getName()));
        this.reduceFullness(10);
        this.addHappiness(60);
        if (this.getHome().getMoney() >= 600) {
            this.getHome().reduceMoney(550);
            this.logs.append("и купила себе новую шубу.\n");
            this.getHome().addFurCoatsBought(1);
        }
        else {
            this.logs.append(", но ничего не купила.\n");
        }
        this.shoppingTrips += 1;
    }

    private void buyFood(String forWho) {
        this.logs.append(String.format("Дома осталось мало еды, поэтому %s пошла в магазин", this.getName()));
        this.reduceFullness(10);
        this.reduceHappiness(5);
        boolean didBuyFood = false;

        if (forWho.equals("human")) {
            int randomEd = ThreadLocalRandom.current().nextInt(60, 70+1);
            if (this.getHome().getMoney() >= randomEd) {
                this.getHome().addEat(randomEd);
                this.getHome().reduceMoney(randomEd);
                didBuyFood = true;
            }
        }
        else if (forWho.equals("pets")) {
            int randomEd = ThreadLocalRandom.current().nextInt(50, 70+1);
            if (this.getHome().getMoney() >= randomEd) {
                this.getHome().addPetFood(randomEd);
                this.getHome().reduceMoney(randomEd);
                didBuyFood = true;
            }
        }

        if (!didBuyFood)
            this.logs.append(", но ей не хватило денег.\n");
        else
            this.logs.append("\n");
    }

    private void cleanHouse() {
        this.logs.append(
                String.format("%s не любит грязь в доме, поэтому решила потратить день на уборку.\n",
                this.getName())
        );
        this.reduceFullness(10);
        this.getHome().cleanHome();
        this.totalCleanings += 1;
    }

    public int getShoppingTrips() {return this.shoppingTrips;}
    public int getTotalCleanings() {return this.totalCleanings;}
}