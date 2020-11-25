package AlgoMart;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Wilson on
 * Nov. 25, 2020
 *
 * Question & Solution:
 * Using The Sieve of Eratosthenes's to find the primes up an integer
 *
 */
public class SieveOfEratosthenes {
     static ArrayList<Integer> primes = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sieve(scanner.nextInt());
        System.out.println(primes);
    }
    public static void sieve(int num){
        int[] nums = new int[num];
        int i;
        for (int k = 1; k < nums.length; k++) {

            //if the current number has been cancelled, skip it
            //because all of its multiples would have also been
            //cancelled
            if(nums[k] <0 )continue;

            //the actual current number we are checking
            //add it to the list of sieved primes
            i = k+1;
            primes.add(i);

            //loop to sieve/cancel out multiples of the
            //current prime. this loop is optimized
            for (int j = i*2; j <= num; j +=i) {
                nums[j-1] = -1;
            }
        }
        nums = null;
        System.gc();
    }
}
