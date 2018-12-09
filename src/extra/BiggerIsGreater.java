
package extra;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hackerrank Problem : Bigger is Greater
 * from : https://www.hackerrank.com/challenges/bigger-is-greater/problem
 * @author wilson
 */

public class BiggerIsGreater {
    private static ArrayList<String> perms= new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        String cases = scanner.nextLine();
        int tests = Integer.parseInt(cases);
        
        
        
        for(int i =0; i<tests; i++){
            String text = scanner.nextLine().trim();
        
            char[] textChars = text.toCharArray();
            
            
            System.out.println(nextPermutation(textChars));
            
        }
    }
    
     private static String nextPermutation(char[] array){
        //char [] array = text.toCharArray(); //int arrays can be used as well
        
        //find the longest NON-INCREASING suffix
        int i = array.length - 1;
        while(i>0 && array[i-1] >= array[i]){
            i--;
        }
        //Now, i is the head index of the suffix
        
        //Are we at the last permutation already?
        if(i<=0){
            return "no answer";
        }
        
        //Let array[i-1] be the pivot
        //Find rightmost element, of course in the suffix, that is ABOVE the pivot
        int j = array.length - 1;
        while(array[j] < array[i-1]){
            j--;
        }
        //Now, array[j] will become the new pivot
       
        //Swap the former pivot, array[i-1] with the new pivot, array[j]
        array = swapArray(array, i-1, j);
        
        //Reverse the suffix (this is how to reverse a sequence with small time complexity
        j = array.length -1;
        while(i<j){
            array = swapArray(array, i, j);
            i++;
            j--;
        }
        
        
        //Successfully computed the previous permuation
        return String.valueOf(array);
    }
      private static char[] swapArray(char[] charArray, int i , int j){
        char temp;
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return charArray;
    }

    private static void permute(String text, int l, int r) {
        if(l == r){
            if(!perms.contains(text)){
                
            perms.add(text);
            }
            
        } else {
            for(int i=l; i<=r; i++){
                if(perms.size() == ((factorial(text.length()))/text.length()) ){
                    return;
                } 
              
                text = swap(text, l,i);
                permute(text, l+1, r);
                text = swap(text, l, i);
                
            }
        }
         
            
    }
    private static String swap(String a, int i , int j){
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }
    
    private static int factorial(int n){
        int an =1;
        
        for(int i=1; i<=n; i++){
            an *= i;
        }
        return an;
    }
}
