import java.util.Scanner;

public class NonDuplicateArray {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter no of rows: " );
        int rows = sc.nextInt();
        System.out.println("Enter no of columns: ");
        int cols = sc.nextInt();
        int[][] array2d = new int[rows][cols];
        for(int i = 0; i < rows ; i++ ){
            for(int j = 0 ; j < cols ; j++){
                System.out.println("Enter the value: ");
                int val = sc.nextInt();
                if(!(doesElementExist(array2d,i,j,val))){
                    System.out.println("Duplicate element no allowed");
                    j--;
                }
                else{
                    array2d[i][j]=val;
                }
            }
        }
        printingAnArray(array2d);
    }
    public  static void  printingAnArray(int[][] arr){
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static boolean doesElementExist(int[][] arr,int a,int b,int num){
        for(int i=0;i<=a;i++){
            if(i!=a){
                for(int j=0;j<arr[i].length;j++){
                    if(arr[i][j]==num)
                        return false;
                }
            }
            else{
                for(int j=0;j<b;j++){
                    if(arr[i][j]==num)return false;
                }
            }
        }
        return  true;
    }
}
