/*
 * Problem Statement
    You will be given a square chss board with one queen and a number of obstckes placed on it.
    Determine how many squares the queen can attack.

   Explanation
     A queen is standing on N x N chessboard. the chess board's rows are numbered from 1 to N, going frmo bottom to top. Its columns
     are numbered from 1 to N, going from left to right. Each square is referenced bby a tuple, (r,c), describing the row, r, and 
     column, c where teh suarae is located.
     The queen is standing at position (rq, cq). In a single move, she can attack any square in any of the eight directions (left,
      right, up, down, and the four diagonals). 

      Algorithm
1. Create hashmap with key and values both string. Get the obstacles with "rc", and set store both key and value for the hashmap
    to be "r_c" for each obstacles.;
2. Trace all the valid 8 routes squares the queen can attack (2 vertical, 2 horizontal and the 4 diagonal routes). For each valid 
   cell the queen can attack, get the "r_c" value and check if is it exist in the hashmap, if it is, terminate the move on the route
   if not, increase the attackCounter by 1.
3. Return the attackCounter.

Improvement Area : Look for a data structure that will allow you to check the hashmap before traversing the valid routes
                   to check if the row or column in the obstacles. if not, add all the valid cells in that directions
 
*/
package hackerrank;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author owner
 */
public class QueensAttackII {
    
    public static int calc(int n, int r, int c, HashMap<String,String> obstacles){
        int attackCounter =0; String location ="";
        int r_q = r;
        int c_q = c;
        //Simulate the queen movement
        //First: go right. this means increasing the column and keeping the row constant. stop when col >n or when a key on the 
        //hashmap is found
        for(int i=c_q+1; i<=n; i++){
            location = r_q+"_"+i;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
        //reset the queen position
        r_q =r;
        c_q =c;
        //Second: Go left, the same approach in First, but decrease column instead
        for(int i=c_q-1; i>=1; i--){
            location = r_q+"_"+i;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Third: Go up by increasing rows and keep the column constant
        for(int i=r_q+1; i<=n; i++){
            location = i+"_"+c_q;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Fourth: Go down by decreasing rows and keep the column constant
        for(int i=r_q-1; i>=1; i--){
            location = i+"_"+c_q;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Fifth: Go upper right diagonal(increasing row and col)
        for(int i=r_q+1,j=c_q+1; i<=n&&j<=n; i++,j++){
            location = i+"_"+j;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Sixth: Go upper left diagonal (increasing row, decreasing col)
        for(int i=r_q+1,j=c_q-1; i<=n&&j>=1; i++,j--){
            location = i+"_"+j;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Seven: Go lower left diagonal (decreasing row, decreasing col)
        for(int i=r_q-1,j=c_q-1; i>=1&&j>=1; i--,j--){
            location = i+"_"+j;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
         //reset the queen position
        r_q =r;
        c_q =c;
        //Seven: Go lower right diagonal (decreasing row, increasing col)
        for(int i=r_q-1,j=c_q+1; i>=1&&j<=n; i--,j++){
            location = i+"_"+j;
            if(!obstacles.containsKey(location))
                attackCounter++;
            else
                break;
        }
        return attackCounter;
    }
    
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String[] n_k = sc.nextLine().trim().split(" ");
        int n = Integer.parseInt(n_k[0].trim());
        int k = Integer.parseInt(n_k[1].trim());
        String[] queen = sc.nextLine().trim().split(" ");
        int r_q = Integer.parseInt(queen[0].trim());
        int c_q = Integer.parseInt(queen[1].trim());
        
       HashMap<String,String> obs = new HashMap<>();
        for (int i = 0; i < k; i++) {
                String[] k_ob = sc.nextLine().trim().split(" ");
                String key= k_ob[0].trim()+"_"+k_ob[1].trim();
                //Using the underscore as a separator is important
                //else r=104 c=1 will give the same as r=10 c=41
                //checking if the key already exist and changing it a little bit is important because
                //Notice that when r=c, r_c=c_r. this is not an issue since such cases are on the diagonal and can only appear once
                obs.put(key, key);
        }
        int ans = calc(n, r_q, c_q, obs);
        System.out.println(ans);
    }
}
