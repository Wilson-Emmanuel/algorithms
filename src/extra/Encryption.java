
package extra;

import java.util.Scanner;

/**
 *
 * @author wilson
 * Hackerrank Problem : Encryption
 * From :https://www.hackerrank.com/challenges/encryption/problem
 */
public class Encryption {
    static Scanner scanner  = new Scanner(System.in);
    public static void main(String[] args) {
        String text = scanner.nextLine();
        
        System.out.println(getEncrypted(text));
    }

    private static String getEncrypted(String text) {
       String enc = "";
       int row = 0;
       int col = 0;
       
       int size = text.trim().length();
       int floor =(int) Math.floor(Math.sqrt(size));
       int ceil = (int) Math.ceil(Math.sqrt(size));
        System.out.println("floor"+floor+" ceil"+ceil);
       if(size <= (floor*ceil)){
           row = floor;
           col = ceil;
       } else{
           row = ceil;
           col = ceil;
       }
        System.out.println("row "+row+" col "+col);
       char[] textToChar = text.toCharArray();
       String[] words = new String[col];
       
       
       main:
       for(int i=0; i<row; i++){
           
           for(int j=0; j<col; j++){
               if((col * i)+j == textToChar.length){
                   break main;
               }
               if(words[j] == null){
                   words[j] = " ";
                    words[j] += Character.toString(textToChar[(col * i)+j]);
               }else {
                   words[j] += Character.toString(textToChar[(col * i)+j]);
               }
              // words[j] += String.valueOf(textToChar[(row * i)+j]);
               
           }
       }
       
//      
       
    for(int m=0; m<words.length; m++){
        enc += words[m].trim()+((m != words.length-1)?" ":"");
    }
       
       
       return enc.trim();
    }
}
