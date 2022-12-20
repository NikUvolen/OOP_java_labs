package Humans;

import AbstractClasses.Human;
import AbstractClasses.Home;

import java.util.concurrent.ThreadLocalRandom;

public class Wife extends Human{
    int shoppingTrips = 0;
    int totalCleanings = 0;

    public Wife(String name, Home home) {super(name, home);}

    @Override
    public String getStr() {
        return super.getStr();
    }

    @Override
    public void act() {
        this.getHome().addDirt(5);

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
        else if (dice == 1)
            this.PetPet();
        else
            this.lazinessAllDay();

        int randomFood = ThreadLocalRandom.current().nextInt(15, 30+1);
        if (this.getFullness() <= 0 || this.getHappiness() <= 10)
            System.out.printf("%s умерла...\n", this.getName());
        else if (this.getFullness() <= 25)
            this.eat(randomFood);
    }

    private void shopping() {
        System.out.printf("%s пошла пошопиться с подругами", this.getName());
        this.reduceFullness(10);
        this.addHappiness(60);
        if (this.getHome().getMoney() >= 400) {
            this.getHome().reduceMoney(350);
            System.out.print("и купила себе новую шубу.\n");
            this.getHome().addFurCoatsBought(1);
        }
        else {
            System.out.print(", но ничего не купила.\n");
            this.reduceHappiness(20);
        }
        this.shoppingTrips += 1;
    }

    private void buyFood(String forWho) {
        System.out.printf("Дома осталось мало еды, поэтому %s пошла в магазин", this.getName());
        this.reduceFullness(10);
        this.reduceHappiness(10);
        boolean didBuyFood = false;

        if (forWho.equals("human")) {
            int randomEd = ThreadLocalRandom.current().nextInt(40, 60+1);
            if (this.getHome().getMoney() >= randomEd) {
                this.getHome().addEat(randomEd);
                this.getHome().reduceMoney(randomEd);
                didBuyFood = true;
            }
        }
        else if (forWho.equals("pets")) {
            int randomEd = ThreadLocalRandom.current().nextInt(30, 50+1);
            if (this.getHome().getMoney() >= randomEd) {
                this.getHome().addPetFood(randomEd);
                this.getHome().reduceMoney(randomEd);
                didBuyFood = true;
            }
        }

        if (!didBuyFood)
            System.out.print(", но ей не хватило денег.\n");
        else
            System.out.print("\n");
    }

    private void cleanHouse() {
        System.out.printf("%s не любит грязь в доме, поэтому решила потратить день на уборку.\n", this.getName());
        this.reduceHappiness(3);
        this.reduceFullness(10);
        this.getHome().cleanHome();
        this.totalCleanings += 1;
    }

    public int getShoppingTrips() {return this.shoppingTrips;}
    public int getTotalCleanings() {return this.totalCleanings;}
}