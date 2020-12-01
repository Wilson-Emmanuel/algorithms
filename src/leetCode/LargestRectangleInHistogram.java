package leetCode;

import java.util.Stack;

/**
 * Created by Wilson on
 * Dec. 01, 2020
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] arr = {11,11,10,10,10};
        int maxRecArea = useStack(arr);
        System.out.println(maxRecArea);
    }

    /**
     * Improved bruteForce O(n^2)
     * Solution
     * -------------
     * 1. Notice that the top of at least one height will form a rectangle
     *    one of these rectangles will be the largest and we are trying to find.
     * 2. It suffice to find all the rectangles using the top of each rectangle as a
     *     reference point, including all rectangle right and left that are equal or higher than
     *     the current height and stop when a smaller height is encountered.
     * 3. Find the area of each of the rectangles in 2. above and the max is returned.
     *
     * Execution (LeetCode and Hackerrank)
     * ---------------
     * Info on LeetCode
     * ------------------
     * Runtime: 616 ms, faster than 13.70% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 40.7 MB, less than 21.24% of Java online submissions for Largest Rectangle in Histogram.
     * @param arr
     * @return
     */
    public static int bruteforce(int[] arr){
        int max =0;
        for (int i = 0; i < arr.length; i++) {
            int start=i, end=i,curHeight = arr[i];
            while(start>=0){
                if(arr[start] < curHeight)
                    break;
                start--;
            }
            start++;//shift the start to an eligible bar
            while(end <arr.length){
                if(arr[end] <curHeight){
                    break;
                }
                end++;
            }
            int len = end - start;//Notice that the end isn't an eligible bar
            //that's why we didn't add 1 to the len since it's zero based
            int area = len * curHeight;
            max = Math.max(area, max);
        }
        return max;
    }

    /**
     * O(n)
     * Solution
     * ---------------------
     * 1. Here we use stack to keep track of the index and heights as we go through the heights (i, h).
     *     Start by declaring max =0 and stack
     * 2. For each height h at index i, enter (i,h) to the stack if the stack is empty or the height at the
     *     top of the stack is greater or equal to the height at current index.
     * 3. Else (i.e. if No. 2 failed)
     *   a. while height at the top of the stack (non empty) is greater than the current height h at index i,
     *       pop from the stack (topIndex, topH) and calculate the area by area = (i-topIndex)*topH
     *       compare max. keep track of the topIndex.
     *   b. Push the height at index i with the topIndex (last popped in step 3(a)).
     *      Notice that the last height popped in step 3(a) is where the height at index i starts extending.
     * 4. After the loop in No.2,
     *    for each item in the stack, perform 3(a).
     *
     *    Execution (LeetCode & Hackerrank)
     *    -----------------------
     *    Info on LeetCode
     *    ---------------
     *    Runtime: 6 ms, faster than 88.70% of Java online submissions for Largest Rectangle in Histogram.
     * Memory Usage: 40.2 MB, less than 67.30% of Java online submissions for Largest Rectangle in Histogram.
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
