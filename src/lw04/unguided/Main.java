package lw04.unguided;

import java.util.*;

public class Main {

    static void bubbleSort(int[] array, int passes){
        for(int i = 0; i < passes && i < array.length - 1; i++){
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] < array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    static int[] binarySearch(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        while(low <= high){
            steps++;
            int mid = (low + high) / 2;
            if(arr[mid] == target){
                return new int[]{mid, steps};
            } else if(arr[mid] < target){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{-1, steps};
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));
        
        int n = sc.nextInt();
        int[] parcels = new int[n];
        for(int i = 0; i < n; i++){
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for(int i = 0; i < m; i++){
            targets[i] = sc.nextInt();
        }
        sc.close();

        int[] after2Passes = new int[n];
        for(int i = 0; i < n; i++){
            after2Passes[i] = parcels[i];
        }
        bubbleSort(after2Passes, 2);

        int[] fullySorted = new int[n];
        for(int i = 0; i < n; i++){
            fullySorted[i] = parcels[i];
        }
        bubbleSort(fullySorted, n - 1);

        System.out.println("==== After 2 Passes ====");
        for(int i = 0; i < n; i++){
            System.out.print(after2Passes[i]);
            if(i < n - 1){
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for(int i = 0; i < n; i++){
            System.out.print(fullySorted[i]);
            if(i < n - 1){
                System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Search Results ====");
        for(int t : targets){
            int[] res = binarySearch(fullySorted, t);
            if(res[0] != -1){
                System.out.println(t + ": FOUND at index " + res[0] + " (steps: " + res[1] + ")");
            } else {
                System.out.println(t + ": NOT FOUND (steps: " + res[1] + ")");
            }
        }
    }
}