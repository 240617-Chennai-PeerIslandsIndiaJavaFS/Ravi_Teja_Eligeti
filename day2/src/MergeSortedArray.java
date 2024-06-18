public class MergeSortedArray {
    public static void main(String[] args) {
        int arr1[]={2,4,7,8,12,24,33};
        int arr2[]={1,2,3,4,6,12,34,64};
        mergeArray(arr1,arr2);
    }
    public static void mergeArray(int arr1[],int arr2[]){
        int res[]=new int[arr1.length+arr2.length];
        int i=0;
        int j=0;
        int index=0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i]<=arr2[j]){
                res[index++]=arr1[i++];
            }
            else{
                res[index++]=arr2[j++];
            }
        }
        while(i< arr1.length){
            res[index++]=arr1[i++];
        }
        while (j<arr2.length){
            res[index++]=arr2[j++];
        }
        for(int val:res){
            System.out.println(val);
        }
    }
}
