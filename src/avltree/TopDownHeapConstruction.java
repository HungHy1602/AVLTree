/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avltree;

/* Name: Le Cong Hung
Student Code: SE161248
Purpose: AVLTree.
 */
import java.util.Arrays;

public class TopDownHeapConstruction {

    public void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            displayHeap(arr);

            heapify(arr, n, largest);
        }
    }

    public void buildHeap(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    public void displayHeap(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};

        TopDownHeapConstruction heapConstruction = new TopDownHeapConstruction();
        heapConstruction.buildHeap(arr);
    }
}
