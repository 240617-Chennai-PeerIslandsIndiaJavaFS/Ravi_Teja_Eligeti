package Entity;

import java.util.ArrayList;
import java.util.Random;

public class Player  extends Creature implements Interactable{
    String name;

    @Override
    public void attack(Creature c) {
        Random rand=new Random();
        int num=rand.nextInt(49)+1;
        c.health-=num;
    }


    public Player(String name) {
        this.name = name;
    }

    @Override
    public void takeDamage() {
        this.health=health/2;
    }

    @Override
    public void interact(byte num,Creature c) {
        if(num==1){
            attack(c);
        }
        else{
            takeDamage();
        }

    }



}
