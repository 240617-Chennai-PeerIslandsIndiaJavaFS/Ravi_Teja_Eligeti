package Entity;

abstract public class Creature {
    String name;
    int health;
    abstract public void attack(Creature c);
    abstract public void takeDamage();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
