package leetCode;

/**
 * Created by Wilson
 * on Fri, 11/12/2020.
 */

/**
 * 322. Coin Change LeetCode
 *
 * Solution
 * =----------------------
 * https://riptutorial.com/dynamic-programming/example/25891/minimum-number-of-coins-to-get-total
 *
 * The solution pseudocode on the link above is not correct, though the steps explained there can help to
 * understand and solve the problem.
 *
 * LeetCode submission
 * ---------------------------------
 *Runtime: 21 ms, faster than 31.78% of Java online submissions for Coin Change.
 * Memory Usage: 39.2 MB, less than 15.98% of Java online submissions for Coin Change.
 */
public class CoinChangeByDPMatrix {
    public static void main(String[] args) {
        int[] coins = {2,4,8,16,34,40,64};
        int amount = 50;
        coinChange(coins,amount);

    }
    public static int coinChange(int[] coins, int amount){
        int[][] dp = change(coins,amount);
        int[] result = countCoin(coins,amount,dp);
        return result[0];
    }
    public static int[][] change(int[] coins, int amount){

        int[][] DP = new int[coins.length][amount+1];

         //fill the first column with 0
        //int array contains 0 by default, so this part is not necessary
        /*
        for (int i = 0; i < coins.length; i++) {
            DP[i][0] =0;
        }
         */


        //fill the first row.
        //At 0th row, we are trying to find the min number of ways to change j amount using only
        //one coin i.e. coins[0] (that is the meaning of DP[0][j];
        for (int j = 1; j <= amount; j++) {
            if(coins[0] > j || j % coins[0] != 0){
                DP[0][j] = -1;
            }else{
                DP[0][j] = j /coins[0];
            }
        }

        //iterate the rest of the unfilled DP
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount+1; j++) {

                if(coins[i] > j){
                    DP[i][j] = DP[i-1][j];
                }else {
                    int prev = DP[i-1][j];
                    int cur = 1+DP[i][j-coins[i]];

                    if(cur == 0){
                        DP[i][j] = DP[i-1][j];
                    } else if(prev == -1) {
                        DP[i][j] = 1 + DP[i][j - coins[i]];
                    }else{
                        DP[i][j] = Math.min(DP[i-1][j],1+DP[i][j-coins[i]]);
                    }
                }
            }
        }
        return DP;
    }
    public static int[] countCoin(int[] coins, int amount, int[][] DP){

        int[] result = new int[coins.length+1];//The 1 added is to hold result.
        int i = coins.length -1;
        int j = amount;
        //while the rest will contain counter for coins used.
        result[0] = DP[i][j];
        if(result[0] ==0 || result[0] ==-1)return result;

        while (j > 0 ){
            if(i-1 >= 0 && DP[i][j] == DP[i-1][j]){
                i = i-1;
            }else{
                j = j - coins[i];
                result[i+1] += 1;
            }
        }
        return result;
    }


}
