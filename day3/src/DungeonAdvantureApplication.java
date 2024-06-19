import java.util.Scanner;

public class DungeonAdvantureApplication {
    public static void main(String[] args) {
        Service service=new Service();
        service.displayRules();


        Scanner sc=new Scanner(System.in);
        System.out.println("*****************************************");
        System.out.println("Enter 0 to start the game: ");
        int num=sc.nextInt();
        if(num==0){
            service.startGame();
        }
        else {
            System.out.println("Try again");
        }

    }

}