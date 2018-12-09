
package extra;

import java.util.*;

/**
 *
 * @author Wilson
 * Hackerrank Problem : Organizing containers of balls
 * From : https://www.hackerrank.com/challenges/organizing-containers-of-balls/problem
 */
public class BallsContainer {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        String queries  = scanner.nextLine();
        int q = Integer.parseInt(queries);
        
        for(int p =0; p<q; p++){
            
            String orderString = scanner.nextLine();
            int n = Integer.parseInt(orderString);
            
            int[] box = new int[n];
            int[] type = new int[n];
            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
               // scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    box[i] += containerItem;
                    type[j] += containerItem;
                }
            }
            
            String output = swappable(box, type);
            System.out.println(output);
        }
    }

    private static String swappable(int[] box, int[] type) {
       //Since swapping does not change the number of balls in each container, rather changes the number of balls of
       //particular type in a container, it means that, prior to swapping, if this method should return "Possible",
       //there should a container that contains the same number of balls a particular random columns. that is to say
       //when you get the total of balls of a particular type, there should be a row which shuould have the same number 
       //of balls as well.
       
        
        String swappable = "";
        
        for(int r=0; r<box.length; r++){
            System.out.print(box[r]+((r != box.length-1)?"|":"\n"));
        }
        for(int r=0; r<box.length; r++){
            System.out.print(type[r]+((r != box.length-1)?"|":"\n"));
        }
        Arrays.sort(box);
        Arrays.sort(type);
        if(Arrays.equals(box, type)){
            swappable = "Possible";
        } else {
            swappable = "Impossible";
        }
        
        return swappable;
    }
    

    
   
}
