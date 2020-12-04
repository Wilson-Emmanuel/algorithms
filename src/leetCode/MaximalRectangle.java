package leetCode;

import java.util.Stack;

/**
 * Created by Wilson on
 * Dec. 04, 2020
 *
 * Solution
 * ---------------------
 * This an extension of the Largest Rectangle in a Histogram problem.
 * 1. Loop through all the rows. take the current row as base of a histogram, where 1s means bar heights.
 *    For example, considering the matrix in the main method of the of solution below, the histogram formed
 *    by the 3rd row, has the following; first and third bars of height 3, second bar of height 1, and
 *    fourth and fifth bars of height 2.
 *    Whereas, the histogram formed by the fourth row, has first bar of height 4, fourth bar of height 3, while
 *    the second, third and fifth bars of 0 height.
 * 2. Use the "Largest Rectangle in a Histogram" algorithm to calculate the largest rectangle
 * 3. Keep track of max rectangle encountered.
 *
 *
 * LeetCode Submission Info
 * ------------------------
 * Runtime: 7 ms, faster than 61.63% of Java online submissions for Maximal Rectangle.
 * Memory Usage: 40.8 MB, less than 98.99% of Java online submissions for Maximal Rectangle.
 */
public class MaximalRectangle {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        int maxArea = maximalRectangle(matrix);
        System.out.println(maxArea);
    }

    /**
     * O(n^2)
     * @param matrix
     * @return
     */
    public static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length ==0 || matrix[0].length ==0) return 0;
        int maxArea =0;
        int[] curHistogram = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < curHistogram.length; j++) {
                if(i ==0){
                    curHistogram[j] = matrix[i][j] == '0'?0:1;
                }else{
                    curHistogram[j] = matrix[i][j] == '0'?0:curHistogram[j]+1;
                }
            }

             maxArea = Math.max(maxArea,useStack(curHistogram));
        }
        return maxArea;
    }

    /**
     * This method find the largest Rectangle in the Histogram formed by the current row.
     * Please see the LargestRectangleInHistogram solution for the algorithm explanation.
     * @param arr
     * @return
     */
    public static int useStack(int[] arr){
        int max =0;
        Stack<int[]> indexStack = new Stack<>();
        //indexStack[0] is for index, indexStack[1] is for height
        int i =0;
        for (; i < arr.length; i++) {
            if(indexStack.isEmpty() || arr[i] >= indexStack.peek()[1] ){
                indexStack.push(new int[]{i,arr[i]});
            }else{
                int topIndex =0;//this initialization is guaranteed to change since while
                //loop below must be executed
                while(!indexStack.isEmpty() && arr[i] < indexStack.peek()[1]){
                    int[] top = indexStack.pop();
                    topIndex = top[0];
                    int area = (i - topIndex) * top[1];
                    max = Math.max(area,max);
                }
                indexStack.push(new int[]{topIndex,arr[i]});
            }
        }
        while(!indexStack.isEmpty()){
            int[] top = indexStack.pop();
            int topIndex = top[0];
            int area = (i - topIndex) * top[1];
            max = Math.max(area,max);
        }
        return max;

    }
}
