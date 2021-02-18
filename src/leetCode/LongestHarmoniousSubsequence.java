package leetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Wilson on
 * Feb. 18, 2021
 *
 * 594. Longest Harmonious Subsequence
 *
 * We define a harmonious array as an array where the difference between its maximum
 * value and its minimum value is exactly 1.
 * Given an integer array nums, return the length of its longest harmonious subsequence
 * among all its possible subsequences.
 *
 * A subsequence of array is a sequence that can be derived from the array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * SOLUTION
 * Generating a subsequence of an array is a costly operation (exponential time is required),
 * especially when the size of the array is large (2^n, where n is the size of the array).
 * However, the property/condition of the harmonious subsequence makes it easily approachable
 * without actually generating the subsequences. The difference between the largest and smallest
 * element in the subsequence (when generated) is 1. This means that the subsequence must contain
 * only 2 distinct numbers. For instance "1 2 2 2 2 1" and "3 3 3 3 2" are valid harmonious
 * subsequences while "2 2 2 2 4 3" and "3 3 3 3 3" are not valid harmonious subsequence.
 * This means that A and A+1 (or A and A-1) are complementary.
 *
 * Also the subsequence does not change it's property when SORTED.
 * "1 2 2 2 2 1" is equivalent to "1 1 2 2 2 2".
 *
 * Approach: Time: O(n), Space: O(n)
 * 1. Count the frequencies of each number and store them in a hashmap (better to use an array
 *    if the range of numbers is known and also small). Use the number as key and its frequency as
 *    value in the hashmap.
 * 2. Iterate through the hashmap. For each number A (the map key), check if it's complementary exists
 *    (i.e. check if A+1 exists). If yes, sum the frequencies of A and A+1. Continue until all map
 *    entries are iterated. Then return the maximum sum.
 *
 */
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {

        //store the frequencies of each number in a hashmap
        Map<Integer,Integer> freq = new HashMap<>();
        for(int a: nums)
            freq.put(a,freq.getOrDefault(a,0)+1);

        //iterate through the hash map and count the frequencies if each
        //element and it's complement (if it exists in the map)
        //return the max of these counts
        int longest = 0,cur;
        for(Map.Entry<Integer,Integer> entry: freq.entrySet()){
            if(freq.containsKey(entry.getKey()+1)){
                cur = entry.getValue() + freq.get(entry.getKey()+1);
                longest = Math.max(longest, cur );
            }
        }
        return longest;
    }
}
