package com.yufeng.algorithm;


public class BasicSort {


    public static void main(String[] args) {

        int[] arr = new int[] {3, 1, 9, 10, 99, 20, 7};
        selectionSort(arr);
        printArr(arr);

        arr = new int[] {3, 1, 9, 10, 99, 20, 7};
        insertionSort(arr);
        printArr(arr);

        arr = new int[] {3, 1, 9, 10, 99, 20, 7};
        bubbleSort(arr);
        printArr(arr);
    }


    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {

            int minIdx = i;
            for(int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }


    public static void insertionSort(int[] arr) {

       for(int i = 1; i < arr.length; i++) {

           int temp = arr[i];
           int j = i - 1;
           while (j >= 0 && temp < arr[j] ) {
               arr[j + 1] = arr[j];
               j--;
           }
           arr[j + 1] = temp;
       }
    }

    public static void bubbleSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i -1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
