
package extra;

/**
 *
 * @author wilson
 * 
 * See problem here : https://www.hackerrank.com/challenges/strange-code/problem
 */
public class StrangeCounter {
    static long exponent(int base, int power){
        if(power ==0)return 1;
        if(power ==1)return base;
        long t = exponent(base, power/2);
        return t*t*exponent(base,power%2);
    }
    
   
    public static void main(String[] args) {
        System.out.println(strangeCouter(14));
    }

    private static long calc(long t, long startT, long startV) {
       if(t>=startT && t<=(startT+startV-1)){
           long diff = t - startT;
           return startV -diff;
       }
       return calc(t, startT+startV, startV*2);
    }
    
    static long strangeCouter(long t){
        
       return calc(t, 1, 3);
    }
}
