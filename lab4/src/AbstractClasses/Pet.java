package AbstractClasses;

abstract public class Pet {
    String name;
    int fullness;
    boolean type;

    public Pet(String name, boolean type) {
        // type:
        // 0 - cat
        // 1 - dog
        this.name = name;
        this.fullness = 30;
        this.type = type;
    }

    public String getType() {
        if (!this.type)
            return "котик";
        else
            return "пёсель";
    }
}