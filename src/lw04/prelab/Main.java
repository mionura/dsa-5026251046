package prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        
        int n = scanner.nextInt();
        int[] branches = new int[n];
        for (int i = 0; i < n; i++) {
            branches[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        //Sorting the branches (3 passes of insertion sort)
        for(int i = 1; i <= 3; i++) {
            int key = branches[i];
            int j = i - 1;

            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j--;
            }
            branches[j + 1] = key;
        }

        for(int i = 0; i < n; i++) {
            System.out.print(branches[i]);

            if(i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();

        //Searching for targets
        for(int i = 0; i < m; i++) {
            int target = targets[i];
            int index = -1;

            for(int j = 0; j < n; j++) {
                if (branches[j] == target) {
                    index = j;
                    break;
                }
            }
    
            if(index != -1) {
                System.out.println(target + ": FOUND at index " + index);
            }else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}
