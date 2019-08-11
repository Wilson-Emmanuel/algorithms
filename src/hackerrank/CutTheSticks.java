
package hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author wilson
 * 
 * Problem Statement
 * You are given a number of sticks of varying lengths. You will iteratively cut the sticks into smaller sticks, discarding the shortest pieces until there are none left. At
 * each iteration you will determine the length of the shortest stick remaining, cut that length from each of the longer sticks and then discard
 * all the pieces of that shortest length When all the remaining sticks are the same length, they cannot be shortened so discard them.
 * 
 * Given the lengths of n sticks, print the number of sticks that are left before each iteration until there are none left.
 * 
 * For example, there are n = 3 sticks of lengths
 * arr = [1,2,3]. The shortest stick length is 1, so we cut that length from the longer two and discard the pieces of length 1
 * 
 */
public class CutTheSticks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arrSize = Integer.parseInt(sc.nextLine().trim());
        String[] arrStr = sc.nextLine().trim().split(" ");
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
            
        }
        ArrayList<Integer> result = cutTheStick(arr);
        for(int k=0; k<result.size(); k++){
            System.out.println(result.get(k));
        }
     
    }
    public static ArrayList<Integer> cutTheStick(int[] arr){
        
            int arrSize = arr.length;
            ArrayList<Integer> result = new ArrayList<>();
           Arrays.sort(arr);
        int firstEle = arr[0];
        int removed =1;
        result.add(arrSize);
        for(int j=1; j<arrSize; j++){
            if(arr[j] == firstEle){
                removed++;
            }else{
                firstEle = arr[j];
                result.add(arrSize-removed);
                removed++;
            }
        }
        return result;
    }
    
}
