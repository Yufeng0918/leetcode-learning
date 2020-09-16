package com.yufeng.algorithm;

public class AdvanceSort {

    public static void main(String[] args) {

        int[] arr = new int[] {3, 1, 9, 10, 99, 20, 7};
        mergeSort(arr, 0, arr.length -1);
        printArr(arr);

        arr = new int[] {3, 1, 9, 10, 99, 20, 7};
        quickSort(arr, 0, arr.length - 1);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void mergeSort(int[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        int i = l;
        int j = mid + 1;
        int k = 0;
        int[] temp = new int[r - l + 1];
        while (i <= mid && j <= r) {
            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for(int q = 0, p = l; q < temp.length; q++, p++) {
            arr[p] = temp[q];
        }
    }

    private static  void quickSort(int[] arr, int l, int r) {

        if (l >= r)
            return;

        int pivot = arr[r];

        int idx = l;
        int temp;
        for(int i = l; i <= r ; i++) {
            if (arr[i] < pivot) {
                temp = arr[idx];
                arr[idx++] = arr[i];
                arr[i] = temp;
            }
        }

        temp = arr[idx];
        arr[idx] = arr[r];
        arr[r] = temp;

        quickSort(arr, l, idx - 1);
        quickSort(arr, idx + 1, r);
    }

}
