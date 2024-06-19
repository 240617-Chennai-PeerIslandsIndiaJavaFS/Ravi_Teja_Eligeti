package Entity;

public class Potion extends Item{
    int potion;

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }

    @Override
    public void use(Creature c) {
        potion-=1;
        c.health=c.health+((c.health*20)/100);

    }
}
