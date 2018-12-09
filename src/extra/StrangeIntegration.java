/*
 *StrangeIntegration - Abstract Syntax Tree Algorithm
UVA Online Judge Problem 10906 : Strange Integration
 */
package extra;

import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Wilson
 */
 class StrangeIntegration {
  Stack<String> holder = new Stack<>();
    
    public static void main(String[] args) {
        StrangeIntegration ast = new StrangeIntegration();
        ast.begin();
        
    }
    public static String readLn(int maxLen){
        byte[] lineHolder = new byte[maxLen];
        int counter =0, reader =-1;
        
        try{
            while(counter < maxLen){
                reader = System.in.read();
                
                if(reader < 0 || (reader == '\n')) break;
                lineHolder[counter++] += reader;
            }
        }catch(IOException ex){
            return (null);
        }
        
        if(counter ==0 && reader  <0)return (null);
        return new String(lineHolder, 0, counter);
    }
    
    void begin(){
       HashMap<String, Node> disconnectedT = new HashMap<>();
       StringTokenizer tokenizer;
    
       int cases  = Integer.parseInt(StrangeIntegration.readLn(255).trim());
       for(int i=0; i<cases; i++){
           int exp = Integer.parseInt(StrangeIntegration.readLn(255).trim());
           String lastKey = "";
           for(int j=0; j<exp; j++){
               String line = StrangeIntegration.readLn(255).trim();
               tokenizer = new StringTokenizer(line);
               
            
                   
                    String var = tokenizer.nextToken().trim();
                    String equ = tokenizer.nextToken().trim();
                    String first = tokenizer.nextToken().trim();
                    String op = tokenizer.nextToken().trim();
                    String second = tokenizer.nextToken().trim();
                    
                    
                    Node cur = new Node(var);
                    cur.op = op; cur.value = op;
                    if(disconnectedT.containsKey(first)){
                        cur.left = disconnectedT.get(first);
                        
                    }else{
                        cur.left = new Node(first);
                    }
                    if(disconnectedT.containsKey(second)){
                        cur.right = disconnectedT.get(second);
                        
                    }else{
                        cur.right = new Node(second);
                    }
                    //insert the current node, cur into the hashmap
                    disconnectedT.put(var, cur);
                    lastKey = var;
                    
               
               
           }
               Node root = disconnectedT.get(lastKey);
               //Tranversing the Abstract Syntax Tree, StrangeIntegration
           
              System.out.println("Expression #"+(i+1)+": "+print(root));
             System.out.println("Prefix form "+prefixForm(root, new Stack<String>()));
             System.out.println("Postfix form"+postfixForm(root, new Stack<String>()));
              disconnectedT.clear();
       }
    }
    
    public Stack<String> prefixForm(Node root, Stack<String> st){
        st.push(root.value);
        
        if(root.left != null){
            prefixForm(root.left, st);
        }
        if(root.right != null){
            prefixForm(root.right,st);
        }
        
        return st;
    }
    public Stack<String> postfixForm(Node root, Stack<String> st){
        if(root.left !=null){
            postfixForm(root.left, st);
        }
        if(root.right !=null){
            postfixForm(root.right,st);
        }
        st.push(root.value);
        return st;
    }
    
 public String print(Node root){
     //right(store), left(store), root(combine conditionally)
     
     //right first
     if(root.right != null){
         holder.push(print(root.right));
     }
     
     //left
     if(root.left != null){
         holder.push(print(root.left));
     }
     
     //root
     if(root.op == null){//leaf
        return root.value;
     }else{
         //non-leaf
         
             String tempL = holder.pop(); //left entered last
             String tempR = holder.pop(); //right entered first
             String temp = "";
             //check left side
            if("+".equals(root.left.op) && root.op.equals("*")){//parent op is * while child op is + (* has higher preced.. comes last in evaluation)
                temp += "("+tempL+")";//* has higher precedence
            }else{//parent op is + while child op is * (* comes first in evaluation)
                temp += tempL;
            }
             
            //attach op
            temp += root.op;
            
             //check right side
             if(root.op.equals(root.right.op) || (root.op.equals("*") && root.right.op != null)){//parent and child ops are the same op
                 //since root.op is an op, if first part of the above is true, root.right will be an exp
                 //second part shows that bracket is needed if root.op is * as far as right is an expression
                 temp += "("+tempR+")";
             }else{//parent and child ops are not the same or right is not an exp
                 //roo.op is + and root.right.op is *
                 temp += tempR;
                 
             }
             
             return temp;
         
     }
     
 }
    
   
    class Node{
        Node left;
        Node right;
        String op = null; //default operator
        String value;
        
        Node(String value){
            this.value = value;
        }
    }
}
