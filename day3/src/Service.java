import Entity.*;

import java.time.Period;
import java.util.Scanner;

public class Service {
    Scanner sc=new Scanner(System.in);
    public  void displayRules(){
        System.out.println("*****************************************");
        System.out.println("➡️ Welcome to Dungeon Adventure`(*>﹏<*)′");
        System.out.println("➡️ Here are the rules of game: ");
        System.out.println("     1️⃣.You have to choose to go right or left");
        System.out.println("     2️⃣.There you will get some weapons and potion");
        System.out.println("     3️⃣.You will get the enemy to fight");
        System.out.println("     4️⃣.You and enemy will initially will have 100 health");
        System.out.println("     5️⃣.Press 1 to use weapon.For every time you use weapon a random from 1-50 health will be lost to enemy");
        System.out.println("     7️⃣.Press 2 for potion, for every use of potion you will be increased with 20% of health.");
        System.out.println("     6️⃣.After every twice choosing your option, enemy will give 50% of damage to you.");
        System.out.println("     8️⃣.If are out of weapons/health is zero you are lost. If you kill enemy you will win");
        System.out.println("All the best");

    }
    public void startGame(){
        System.out.print("Enter your name: ");
        Scanner sc=new Scanner(System.in);
        String name=sc.nextLine();
        System.out.println("All the best "+ name+" !!!!!!");
        Player p=new Player(name);
        p.setName(name);
        p.setHealth(100);

        System.out.println("Which room you want to go!!!");
        System.out.println("    'L' for left");
        System.out.println("    'R' for right");
        System.out.println("Choose your option: ");
        String ch=sc.nextLine();
        System.out.println(p.getHealth());
        System.out.println(p.getName());
        room(ch,p);
    }

    public void room(String c, Player p){
        Potion potion=new Potion();
        Weapon weapon=new Weapon();
        if(c.equalsIgnoreCase("L")){

            potion.setPotion(2);
            weapon.setWeapons(4);
            Dragon tiger=new Dragon();
            tiger.setName("Dragon");
            byte count=0;
            tiger.setHealth(100);
            displayRules();
            System.out.println("Your enemy is dragon 🐉!!");
            System.out.println("Lets start the war!!!!");
            while(true){

                System.out.println("You have "+weapon.getWeapons()+ " weapons.");
                System.out.println("You have "+potion.getPotion()+ " potions.");
                System.out.println("Your health: "+p.getHealth());
                System.out.println("Tiger health: "+tiger.getHealth());
                System.out.print("Enter 1 to fight or 2 to heal: ");
                byte ouput=sc.nextByte();
                if(ouput==1){
                    p.interact(ouput,tiger);
                    weapon.use(p);

                }
                else {
                    if(potion.getPotion()>0) {
                        potion.use(p);
                    }
                    else{
                        System.out.println("potion is over you can't heal");
                        continue;
                    }
                }
                count++;
                if(count==2){
                    System.out.println(tiger.getName()+" attack 😈");
                    tiger.interact(count,p);
                    count=0;
                }
                if((weapon.getWeapons()<=0 || p.getHealth()<=0) && tiger.getHealth()>0 ){
                    System.out.println(weapon.getWeapons()+" "+p.getHealth()+" "+tiger.getHealth());
                    System.out.println(p.getName()+" you had lost the game!!Try again");
                    break;
                }
                if(tiger.getHealth()<=0){
                    System.out.println(p.getName()+" you have won the game!!!!!");
                    break;
                }

            }


        }
        else{
            potion.setPotion(3);
            weapon.setWeapons(3);
            Tiger tiger=new Tiger();
            tiger.setName("tiger");
            tiger.setHealth(100);
            displayRules();
            byte count=0;
            System.out.println("Your enemy if Tiger 🐯");
            System.out.println("Lets start the war!!!!");
            while(true){

                System.out.println("You have "+weapon.getWeapons()+ " weapons.");
                System.out.println("You have "+potion.getPotion()+ " potions.");
                System.out.println("Your health: "+p.getHealth());
                System.out.println("Tiger health: "+tiger.getHealth());
                System.out.print("Enter 1 to fight or 2 to heal: ");
                byte ouput=sc.nextByte();
                if(ouput==1){
                    p.interact(ouput,tiger);
                    weapon.use(p);

                }
                else {
                    if(potion.getPotion()>0) {
                        potion.use(p);
                    }
                    else{
                        System.out.println("potion is over you can't heal");
                        continue;
                    }
                }
                count++;
                if(count==2){
                    System.out.println(tiger.getName()+" attack 😈");
                    tiger.interact(count,p);
                    count=0;
                }
                if((weapon.getWeapons()<=0 || p.getHealth()<=0) && tiger.getHealth()>0 ){
                    System.out.println(weapon.getWeapons()+" "+p.getHealth()+" "+tiger.getHealth());
                    System.out.println(p.getName()+" you had lost the game!!Try again");
                    break;
                }
                if(tiger.getHealth()<=0){
                    System.out.println(p.getName()+" you have won the game!!!!!");
                    break;
                }

            }

        }
    }

}
