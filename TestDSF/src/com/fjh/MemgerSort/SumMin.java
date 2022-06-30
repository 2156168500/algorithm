package com.fjh.MemgerSort;

public class SumMin {
    public static int minSum(int[]array){
        if(array==null){
            return 0;
        }
        if(array.length<2){
            return 0;
        }
        return progress(array,0, array.length-1);
    }
    public static int progress(int[]arr,int left,int right){
        if(left==right){
            return 0;
        }
        int mind=left+((right-left)>>1);
     int L  = progress(arr,left,mind);
      int R = progress(arr,mind+1,right);
      int mergeSum=merge(arr,left,mind,right);
      return L+R+mergeSum;

    }
    public static int merge(int[]arr,int left,int mind,int right){
        int  sum=0;
        int L=left;
        int R=mind+1;
        int k=0;
        int[]temp=new int[right-left+1];
        while (L<=mind&&R<=right){
            if(arr[L]<arr[R]){
                sum+=((right-R+1)*arr[L]);
                temp[k++]=arr[L++];
            }else{
                temp[k++]=arr[R++];
            }
        }
        while (L<=mind){
            temp[k++]=arr[L++];
        }
        while (R<=right){
            temp[k++]=arr[R++];
        }
        for (int i = 0; i < temp.length; i++) {
            arr[left+i]=temp[i];
        }
        return sum;
    }
    public static int minSum02(int []array){
        if(array==null||array.length<2){
            return 0;
        }
        int sum=0;
        for(int i=array.length-1;i>=0;i--){
            for(int j=i-1;j>=0;j--){
                if(array[j]<array[i]){
                    sum+=array[j];
                }
            }
        }
        return sum;
    }

    public static int[] getRandomArray(int maxSize, int maxVal) {
        int arraySize = (int) (Math.random() * maxSize + 1);
        int[] array = new int[arraySize];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) ((int) (Math.random() * maxVal) - (int) (Math.random() * maxVal - 1));
        }
        return array;
    }

    public static int[] copyArray(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
    public static void main(String[] args) {

        int maxSize = 5;
        int maxVal = 100;
        int count = 100;
        boolean flag = true;
        for(int i=0;i<count;i++){
            int[]array1=getRandomArray(maxSize,maxVal);
            int[]array2=copyArray(array1);
            if(minSum(array1)!=minSum02(array2)){
                flag=false;
                break;
            }
        }
        System.out.println(flag);

    }
}
