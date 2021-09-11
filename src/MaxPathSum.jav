import java.util.*;

//Algo Expert: https://www.algoexpert.io/questions/Max%20Path%20Sum%20In%20Binary%20Tree
//Leetcode: https://leetcode.com/problems/binary-tree-maximum-path-sum/
class Program {
	static int global;
	
  public static int maxPathSum(BinaryTree tree) {
		global = -10000;
    getMax(tree);
    return global;
  }
	private static int getMax(BinaryTree tree){
        if(tree == null)return -10000;
        
        int left = getMax(tree.left);
        int right = getMax(tree.right);
        int temp = Math.max(Math.max(tree.value, tree.value + left) ,
                            Math.max(tree.value + right, tree.value + left + right));
        
		     global = Math.max(global, temp);
		
        return Math.max(Math.max(tree.value, tree.value + left), tree.value + right);
    }

  static class BinaryTree {
    public int value;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value) {
      this.value = value;
    }
  }
}
