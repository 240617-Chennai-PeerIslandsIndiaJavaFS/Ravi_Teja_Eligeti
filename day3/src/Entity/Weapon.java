package Entity;

import java.util.Random;

public class Weapon extends Item{
    int weapons;

    public int getWeapons() {
        return weapons;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }

    @Override
    public void use(Creature c) {
        weapons-=1;
    }

}
