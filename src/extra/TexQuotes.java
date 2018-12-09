
package extra;

import java.util.Scanner;

/**
 *
 * @author wilson
 * 
 * Problem 272 UVA Online Judge
 * 
 */
public class TexQuotes {
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean br = true;
        while(sc.hasNextLine()){
            if(br){
                System.out.println("\n\n");
                br = false;
            }
            String line = sc.nextLine();
           String output = processor(line);
            System.out.println(output);
        }
        
    }
      private static String processor(String line) {
            char[] arr = line.trim().toCharArray();
            String holder = "";
            boolean open = true;
            
            for (int i = 0; i < arr.length; i++) {
                char c = arr[i];
                if(c =='"' && open == true){ 
                    holder += "``";
                    open = false;
                }else if(c =='"' && open == false){
                    holder += "''";
                    open = true;
                }else{
                    holder += String.valueOf(c);
                }

        }
            return holder;
    }
}
