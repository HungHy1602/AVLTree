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

public class BottomUpHeapConstruction {

    public void displayHeap(int[] arr) {
        System.out.println("Heap Array: " + Arrays.toString(arr));
        System.out.println("Heap Tree:");
        int depth = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int maxWidth = (int) (Math.pow(2, depth)) - 1;
        int currentLevel = 1;
        int elementsPerLevel = 1;

        for (int i = 0; i < arr.length; i++) {
            if (i == elementsPerLevel - 1) {
                System.out.println();
                currentLevel++;
                elementsPerLevel = (int) Math.pow(2, currentLevel) - 1;
            }
            int numSpaces = maxWidth / ((int) Math.pow(2, currentLevel) + 1);

            // Print spaces
            for (int j = 0; j < numSpaces; j++) {
                System.out.print(" ");
            }

            System.out.print(arr[i]);
        }
        System.out.println();
    }

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

    public static void main(String[] args) {
        int[] arr = {4, 10, 3, 5, 1};

        BottomUpHeapConstruction heapConstruction = new BottomUpHeapConstruction();
        System.out.println("Original array: " + Arrays.toString(arr));
        heapConstruction.buildHeap(arr);
    }
}
