package leetCode;

import java.util.Stack;

/**
 * Created by Wilson on
 * Dec. 04, 2020
 *
 * Solution 1.
 * ---------------------
 * This an extension of the Largest Rectangle in a Histogram problem.
 * 1. We are interested in the maximum of the minimum side of every rectangle encountered.
 * 2. Square of the number in No. 1 is the answer
 *
 *
 * LeetCode Submission Info for Solution 1.
 * -------------------------------------------
 *Runtime: 13 ms, faster than 5.29% of Java online submissions for Maximal Square.
 * Memory Usage: 51.3 MB, less than 5.14% of Java online submissions for Maximal Square.
 *
 * Solution 2.
 * =----------------------------
 * 1. Loop through all entries O(n^2), if entry is '0', continue to the next entry.
 *    Else continue to the next step. Keep the max of the len returned by Next step.
 * 2. Check all entries the same column and row of the square matrix formed by the entry in No 1.
 *     and the current entry.  if all are not '1's, break and return len to No. 1. if all '1's increment the counter and move to the next entry
 *      diagonally and repeat No. 2 until the entry hit outside the matrix, then return len to No. 1.
 * 3. When Loop in No. 1 is done, return the max of the len.
 *
 * LeetCode submission info for Solution 2.
 * --------------------------------------------
 * Runtime: 5 ms, faster than 21.74% of Java online submissions for Maximal Square.
 * Memory Usage: 42.3 MB, less than 53.25% of Java online submissions for Maximal Square.
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        int maxArea = maximalSquareIterative(matrix);
        System.out.println(maxArea);
    }

    /**
     * O(n^2)
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
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
        return maxArea * maxArea;
    }

    /**
     * This returns max of the min of the 2 sides of every rectangle encountered
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
                    //int area = (i - topIndex) * top[1];
                    max = Math.max(max, Math.min(i-topIndex,top[1]));
                }
                indexStack.push(new int[]{topIndex,arr[i]});
            }
        }
        while(!indexStack.isEmpty()){
            int[] top = indexStack.pop();
            int topIndex = top[0];
            //int area = (i - topIndex) * top[1];
            max = Math.max(max, Math.min(i-topIndex,top[1]));
        }
        return max;

    }

    public static int maximalSquareIterative(char[][] matrix){

        int max =0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if(matrix[i][j] == '1'){
                    max = Math.max(max,findSquare(matrix,i,j));
                }
            }
        }
        return max * max;
    }

    private static int findSquare(char[][] matrix, int i, int j) {
        int len = 0;
        int startX = i, startY = j;
        top:
        while( i >=0 && i < matrix.length && j>=0 && j < matrix[0].length && matrix[i][j] == '1'){
            //check all elems from [i,startY] to [i,j] for 1s. (i.e in row i)
            for (int k = startY; k <= j ; k++) {
                if (matrix[i][k] == '0') break top;
            }
            //check all elems from [startX,j] to [i,j] for 1s. (i.e in column j)
            for (int k = startX; k <= i ; k++) {
                if (matrix[k][j] == '0') break top;
            }

            len++;
            i++; j++;//move next diagonally
        }
        return len;
    }
}
