package AbstractClasses;

public abstract class Creature {
    String name;
    int fullness;
    Home home;

    public Creature(String name, Home home) {
        this.name = name;
        this.fullness = 30;
        this.home = home;
    }

    public void lazinessAllDay() {
        System.out.printf("%s решает ничего сегодня не делать.\n", this.name);
        this.fullness -= 10;
    }

    public void act() {}

    public String getStr() {return String.format("Я %s, моя сытость %d ед.", this.getName(), this.getFullness());}

    public String getName() {return this.name;}
    public int getFullness() {return this.fullness;}
    public void reduceFullness(int num) {this.fullness -= num;}
    public void addFullness(int num) {this.fullness += num;}
    public Home getHome() {return this.home;}
}
