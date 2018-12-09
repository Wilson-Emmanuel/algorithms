
package extra;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wilson
 * Hackerrank Problem : Beautiful Triplets
 * From : https://www.hackerrank.com/challenges/beautiful-triplets/problem
 */
public class Triplet {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
       String[] nd = sc.nextLine().split(" ");
       String[] seq = sc.nextLine().split(" ");
       
       int n = Integer.parseInt(nd[0]);
       int d = Integer.parseInt(nd[1]);
       
       int a = 0;
        int b = 0;
        int c = 0;
      
       //ArrayList<String> perms = new ArrayList<>();
       int counter = 0;
       if(n>=3){
           for(int i=0; i<=(n-3); i++){
               
               for(int j=i+1; j<=(n-2); j++){
                      a = Integer.parseInt(seq[i]);
                      b = Integer.parseInt(seq[j]);
                      if(b - a != d) continue;       //To save time for and prevent unnecessary permuations
                      
                   for(int k=j+1; k<=(n-1); k++){
                        
                        c = Integer.parseInt(seq[k]);
                       if(c-b == d){
                           
                        //perms.add(seq[i]+","+seq[j]+","+seq[k]);
                        counter ++;
                       }
                   }
               }
           }
           //System.out.println(perms.size());
           //System.out.println(perms.toString());
           System.out.println(counter);
       } else {
           System.out.println(0);
       }
        
    }
}
