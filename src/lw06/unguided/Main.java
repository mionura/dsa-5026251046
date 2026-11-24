package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree bst = new BinarySearchTree();
        
        String[] numbers = read.nextLine().split(" ");
        for(String number : numbers){
            int data = Integer.parseInt(number);
            bst.insert(data);
        }

        int target = read.nextInt();

        System.out.print("Preorder : ");
        bst.preOrder(); 

        System.out.print("Inorder : ");
        bst.inOrder();

        System.out.print("Postorder : ");
        bst.postOrder();

        System.out.println("Search " + target + " : " + bst.search(target));
        System.out.println("Nodes : " + bst.countNodes());
        System.out.println("Leaves : " + bst.countLeaves());

        read.close();
    }
}



// Preorder : 55 30 15 40 75 65 70 85
// Inorder : 15 30 40 55 65 70 75 85
// Postorder : 15 40 30 70 65 85 75 55
// Search (target) : true
// Nodes : 8
// Leaves : 4