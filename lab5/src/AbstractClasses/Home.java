package AbstractClasses;

public class Home {
    int furCoatsBought = 0;
    int earnedMoney = 0;
    int eatenFood = 0;
    int eatenByPet = 0;
    int pettedCat = 0;
    int pettedDog = 0;
    int damagedFurniture = 0;

    int money;
    int eat;
    int dirt;
    int petFood;

    boolean isHaveChild;
    boolean isHaveCat;
    boolean isHaveDog;

    public Home(boolean child, boolean cat, boolean dog) {
        this.money = 100;
        this.eat = 50;
        this.dirt = 0;
        this.petFood = 30;

        this.isHaveChild = child;
        this.isHaveCat = cat;
        this.isHaveDog = dog;
    }

    public String getStr() {
        return String.format(
                "В тумбочке %d рублей, в холодильнике %d ед. еды для людей и %d ед. для котика. Грязь - %d ед.",
                this.money, this.eat, this.petFood, this.dirt
        );
    }

    public int getDirt() {return this.dirt;}
    public void addDirt(int num) {this.dirt += num;}
    public void cleanHome() {this.dirt -= this.dirt;}
    public int getMoney() {return this.money;}
    public void addMoney(int num) {
        this.earnedMoney += num;
        this.money += num;
    }
    public void reduceMoney(int num) {this.money -= num;}
    public int getEat() {return this.eat;}
    public void addEat(int num) {this.eat += num;}
    public void reduceEat(int num) {this.eat -= num;}
    public int getPetFood() {return this.petFood;}
    public void addPetFood(int num) {this.petFood += num;}
    public void reducePetFood(int num) {this.petFood -= num;}

    public int getEatenByPet() {return this.eatenByPet;}
    public void addEatenPetFood(int num) {this.eatenByPet += num;}
    public int getEatenFood() {return this.eatenFood;}
    public void addEatenFood(int num) {this.eatenFood += num;}
    public int getEarnedMoney() {return this.earnedMoney;}
    public int getFurCoatsBought() {return this.furCoatsBought;}
    public void addFurCoatsBought(int num) {this.furCoatsBought += num;}
    public int getPettedCat() {return this.pettedCat;}
    public int getPettedDog() {return this.pettedDog;}
    public void addDamagedFurniture(int num) {this.damagedFurniture += num;}
    public int getDamagedFurniture() {return this.damagedFurniture;}

    public boolean isHaveChild() {return this.isHaveChild;}
    public boolean isHaveCat() {return this.isHaveCat;}
    public boolean isHaveDog() {return this.isHaveDog;}
}
