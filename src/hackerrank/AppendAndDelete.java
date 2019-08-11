
package hackerrank;

import java.util.Scanner;

/**
 *
 * @author owner
 */
public class AppendAndDelete {
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        String from = scan.next().trim();
        String to = scan.next().trim();
        int changes = Integer.parseInt(scan.next().trim());
        
        System.out.println( appendAndDelete(from,to,changes));
    }

    private static String appendAndDelete(String from, String to, int changes) {
      char[] fromChars = from.toCharArray();
      char[] toChars = to.toCharArray();
        System.out.println("from len "+fromChars.length);
        System.out.println("to len "+toChars.length);
      int equalPointer=0;
      //I want to check if the from and to have common starting characters
      while(equalPointer <toChars.length && equalPointer < fromChars.length){
         
          if(fromChars[equalPointer] == toChars[equalPointer]){
              equalPointer++;
          }else{
              break;
          }
      }
     
      
      //if the number of the allowed changes is more than removing all the from and replacing with to
          if(changes >= (fromChars.length + toChars.length)){
              return "Yes";
          }else{
              //Get all required changes
              int required = (fromChars.length - equalPointer) + (toChars.length - equalPointer);
              int rem = changes - required;
              System.out.println("required "+required);
              System.out.println("remaning "+rem);
              if((rem ==0 || rem%2==0) && rem >0){
                  return "Yes";
              }else{
                  return "No";
              }
              
          }
    }    
    
}
