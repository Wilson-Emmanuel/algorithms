
package extra;


import java.util.HashMap;

/**
 *
 * @author wilson
 * Alice and Bob Problem : https://www.hackerearth.com/problem/algorithm/alice-bob/
 */
public class AliceBobGame {
    
    HashMap<Long,Integer> lookup = new HashMap<>();
    long[] lookupArr = new long[63];
    String winner = "";
    
    public static  String readLn(int maxLen){
        int[] holder = new int[maxLen];
        int counter =0, reader =1;
        
        try{
            while(counter < maxLen){
                reader = System.in.read();
                if(reader <0 || (reader =='\n'))break;
                holder[counter++] = reader;
            }
        }catch(Exception ex){
            return (null);
        }
        
        if(counter ==0 && reader <0)return (null);
        return new String(holder,0,counter);
    }
    
     void begin(){
         //prebuild the lookup table
         long start = 1;
         for(int i= 1; i<=63; i++){
             start *=2;
             lookup.put(start, i);
             lookupArr[i-1] = start;
         }
        String line; 
        while(!(line = AliceBobGame.readLn(255).trim()).equals("-1")){
            long num = Long.parseLong(line);
            play(num);
        }
    }

    private void play(long num) {
        long look = lookup.getOrDefault(num, -1);
        while(num >1){
            if(look ==-1){
            long lesser = lesserSquare2(num);
            if(lesser ==0)return;//invalid input
            num = num - lesser;
            }else{
                num = num/2;
            }
            if(winner.trim().length() ==0 || winner.trim().equals("Bob")){
                winner = "Alice";
            }else {
                winner = "Bob";
            }
            look = lookup.getOrDefault(num, -1);
        }
        System.out.println(winner);
        winner = "";
      
    }
    public static void main(String[] args) {
        AliceBobGame gm  = new AliceBobGame();
        gm.begin();
    }

    private long lesserSquare2(long look) {
        long ret = 0;
        for (int i = 0; i < lookupArr.length-1; i++) {
            if(look > lookupArr[i] && look < lookupArr[i+1]){
                ret = lookupArr[i];
                break;
            }
            
        }
        return ret;
        
    }
}
