
package extra;

import java.util.Scanner;

/**
 *
 * @author Wilson
 * 
 * Hackerrank Problem : Halloween Sales
 * From : https://www.hackerrank.com/challenges/halloween-sale/problem
 */
public class HalloweenSale {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        String[] pdms = sc.nextLine().split(" ");
        
       int p = Integer.parseInt(pdms[0]);
       int d = Integer.parseInt(pdms[1]);
       int m = Integer.parseInt(pdms[2]);
       int s = Integer.parseInt(pdms[3]);
		
	int num_of_games = 0;
  
	while(s >= p) { //You can only buy when the money you have greater or equal to the money you have
	
          //Do buying process
	  s= s - p;
	  num_of_games++;
	
          //Assign the next price
	  if(p - d >= m){
		p = p - d;
	  }else {
		p = m;
	  }
	   
        }
		  
        System.out.println(num_of_games);
    }
}
