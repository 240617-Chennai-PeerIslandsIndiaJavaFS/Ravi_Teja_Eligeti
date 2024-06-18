public class MatrixMultipilcation {
    public static void main(String[] args) {
        int[][] arr1={{1,2,3},{4,5,6},{7,8,9}};
        int[][] arr2={{10,11,12},{20,21,22},{30,31,32}};

        matrixMultiplication(arr1,arr2);
    }
    public static void matrixMultiplication(int[][] arr1,int[][] arr2){
        if(arr1[0].length!=arr2.length){
            System.out.println("Not possible");
        }
        else {
            int res[][] = new int[arr1.length][arr2[0].length];
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    int sum = 0;
                    for (int k = 0; k< arr1[i].length; k++) {
                        System.out.println(arr1[i][k]+" "+arr2[k][i]);
                        sum = sum + arr1[i][k] * arr2[k][i];
                    }
                    res[i][j] = sum;
                }
            }
            NonDuplicateArray.printingAnArray(res);
        }
    }
}
