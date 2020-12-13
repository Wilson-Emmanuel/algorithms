package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilson
 * on Fri, 11/12/2020.
 */

/**
 * 322. Coin Change LeetCode
 *
 * Runtime: 153 ms, faster than 5.59% of Java online submissions for Coin Change.
 * Memory Usage: 40.1 MB, less than 8.76% of Java online submissions for Coin Change.
 */
public class CoinChangeByRecursiveDP {
    public static void main(String[] args) {
        int[] coins = {2,3,5};
        int amount = 31;
        System.out.println(coinChange(coins,amount));
    }
    public static int coinChange(int[] coins, int amount){
        int min = change(coins,amount,new HashMap<>());
        if(min > amount)return -1;
        return min;
    }
    public static int change(int[] coins, int amount, Map<Integer,Integer> memo){

        if(memo.containsKey(amount))return memo.get(amount);
        if(amount == 0 && memo.isEmpty())return 0;
        if(amount == 0)return 0;

        int min = Integer.MAX_VALUE - 1;
        for (int i = 0; i < coins.length; i++) {
            if(coins[i] > amount)continue;
            min = Math.min(min,change(coins,amount-coins[i],memo));
        }
        min++;
        memo.put(amount,min);
        return min;
    }


}
