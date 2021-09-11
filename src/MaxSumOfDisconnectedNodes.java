import java.util.*;
class Solution
{
    //Function to return the maximum sum of non-adjacent nodes.
    int id = 0;
    static int getMaxSum(Node root)
    {
        Pair pair = getSum(root);
        return Math.max(pair.yes, pair.no);
    }
    static Pair getSum(Node root){
        if(root == null)return new Pair(0,0);
       
            Pair left = getSum(root.left);
            Pair right = getSum(root.right);
            
            Pair sum = new Pair(0,0);
            //include this node, don't include the children
            sum.yes = root.data + left.no + right.no;
            
            //dont include this node, but include thier children
            sum.no = Math.max(left.no, left.yes) + Math.max(right.no, right.yes);
            return sum;
    }
    static class Pair{
        int yes,no;//yes means node is included, no means it is not included
        public Pair(int f, int s){
            yes=f;
            no=s;
        }
    }
}

//2nd solution
class Solution
{
    //Function to return the maximum sum of non-adjacent nodes.
    int id = 0;
    static int getMaxSum(Node root)
    {
        return getSum(root,true);
    }
    static int getSum(Node root, boolean get){
        if(root == null)return 0;
       
        if(!get)
            return getSum(root.left,true) + getSum(root.right,true);
        else{
            int left = getSum(root.left, false);
            int right = getSum(root.right, false);
            int sum = left + right + root.data;
            
            left = getSum(root.left,true);
            right = getSum(root.right, true);
            return Math.max(sum, left+right);
        }
        
    }
}
