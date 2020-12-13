package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilson
 * on Sun, 13/12/2020.
 *
 * 300. Longest Increasing Subsequence (LeetCode)
 *
 * Solution
 * ------------------
 * Recursive DB approach - O(nLog(n))
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {
        int[] arr = {1,3,6,7,9,4,10,5,6};
        System.out.println(lengthOfLIS(arr));
    }
        public static int lengthOfLIS(int[] nums) {
                return LIS(nums,Integer.MIN_VALUE, 0,new HashMap<>()) -1;
        }
        public static int LIS(int[] arr, int value, int nextIndex, Map<String,Integer> memo){
            if(memo.containsKey(value+","+nextIndex))return memo.get(value+","+nextIndex);
            if(nextIndex >= arr.length)return 1;

            int max = Integer.MIN_VALUE;
            for(int i=nextIndex; i<arr.length; i++){
                if(arr[i] > value){
                    max = Math.max(max,LIS(arr,arr[i],i+1,memo));
                }
            }
            if(max == Integer.MIN_VALUE)return 1; //when an entry does not have an other one bigger than it
            max++;
            memo.put(value+","+nextIndex,max);
            return max;
        }
}
