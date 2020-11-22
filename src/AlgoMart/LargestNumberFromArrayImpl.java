package AlgoMart;

import java.util.Scanner;

/**
 * Created by Wilson on
 * Nov. 22, 2020
 *
 *
 * Interview Problem: Largest Number formed from an Array
 * Given a list of non-negative integers, arrange them in such a manner that they
 * form the largest number possible. The result is going to be very large,
 * hence return the result in the form of a string.
 *
 * Test Case 1:
 *
 * Input: N = 5, Arr[] = [3, 30, 34, 5, 9]
 * Output: 9534330
 * Explanation:
 *
 * Given numbers are {3, 30, 34, 5, 9}, the arrangement 9534330 gives the largest value.
 *
 * Test Case 2:
 *
 * Input: N = 4, Arr[] = [54, 546, 548, 60]
 * Output: 6054854654
 * Explanation:
 *
 * Given numbers are {54, 546, 548, 60}, the arrangement 6054854654 gives the largest value.
 *
 * SOLUTION:
 * 1. Go through the input files and build a "reverse" Binary Search Tree. By "reverse" Binary Search Tree, I mean
 *    a Binary Tree where the root is logically bigger than the right node and logically smaller than the left node.
 *    Node A is logically bigger than Node B if a1...anb1...bn is numerically bigger than b1...bna1...an.
 *    where a1...anb1...bn is the string formed by concatenating the Node A value(a1...an) with the Node B value(b1...bn).
 *
 * 2. Get the required string by performing inorder traversal of the created Node.
 */
public class LargestNumberFromArrayImpl {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //E.g., 54 546 548 60 //3 30 9 8 34 5 9
        String[] data = sc.nextLine().split(" ");

        LargestNumberFromArray lg = new LargestNumberFromArray();

        Node root = new Node(data[0].trim());
        for (int i = 1; i < data.length; i++) {
            root = lg.insert(root,new Node(data[i].trim()));
        }

        lg.inOrder(root);
    }

}
class Node{
    Node left,right;
    String value;
    int counter;
    public Node(String value){
        this.value = value;
        counter =1;
    }
}
class LargestNumberFromArray{


    public  void inOrder(Node root){
        if(root != null){
            inOrder(root.left);
            int counter = root.counter;
            while(counter-- >0){
                System.out.printf("%s",root.value);
            }
            inOrder(root.right);
        }
    }
    public  Node insert(Node root,Node newNode){
        if(root ==null){
            root = newNode;
            return root;
        }
        if(root.value.equals(newNode.value)){
            root.counter++;
            return root;
        }
        if(rootBigger(root.value,newNode.value)){
            //since root is bigger based on the comparison logic,
            //we insert the new node to the right of the root,
            //so that the root can be taking first during an inorder traversal
            root.right = insert(root.right,newNode);
        }else {
            root.left = insert(root.left,newNode);
        }
        return root;
    }
    public  boolean rootBigger(String root, String n2){
        //rootFirst and rootLast all have the same len
        String rootFirst = root+n2, rootLast = n2+root;

        for (int i = 0; i < rootFirst.length(); i++) {
            //the idea here is to return true if the root can be take first during an inorder traversal
            if(rootFirst.charAt(i) > rootLast.charAt(i)){
                return true;
            }else if(rootFirst.charAt(i) < rootLast.charAt(i)) {
                return false;
            }
        }
        //this is when all characters are the same
        return true;
    }
}
