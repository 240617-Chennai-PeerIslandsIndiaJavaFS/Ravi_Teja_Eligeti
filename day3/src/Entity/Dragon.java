package Entity;

import java.util.Random;

public class Dragon extends Creature implements  Interactable{
    @Override
    public void attack(Creature c) {
        c.health=c.health/2;
    }


    @Override
    public void takeDamage() {
        Random rand=new Random();
        int num=rand.nextInt(49)+1;
        this.health-=num;
    }

    @Override
    public void interact(byte num,Creature c) {
        if(num==2){
            attack(c);
        }
        else{
            takeDamage();
        }

    }

}
