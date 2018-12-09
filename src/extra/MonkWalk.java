/**
 * Hackerrank Problem : Monk Takes a Walk  
 * https://www.hackerearth.com/practice/algorithms/searching/linear-search/practice-problems/algorithm/monk-takes-a-walk/
 */
package extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MonkWalk {
    public static void main(String[] args) throws IOException {
       
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('A'); vowels.add('E');vowels.add('I');vowels.add('O');vowels.add('U');
        vowels.add('a'); vowels.add('e');vowels.add('i');vowels.add('o');vowels.add('u');
        
        for(int i=0; i<cases; i++){
            char[] line = br.readLine().trim().toCharArray();
            int count =0;
            for(int j=0; j<line.length; j++){
                if(vowels.contains(line[j])){
                    count++;
                }
            }
            System.out.println(count);
        }
        
    }
}
