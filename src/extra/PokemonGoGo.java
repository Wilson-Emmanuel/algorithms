
package extra;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author wilson
 * Kattis Problem : Pokemongogo
 * From :https://open.kattis.com/problems/pokemongogo
 */
public class PokemonGoGo {
    
    Node[] nodes;
    int[][] adj;
    int cycle;
     public static String readLn(int maxLen){
        byte[] lineHolder = new byte[maxLen];
        int counter =0, reader = -1;
        
        try{
            while(counter < maxLen){
                reader = System.in.read();
                if(reader <0 || (reader =='\n'))break;
                lineHolder[counter++] += reader;
            }
        }catch(Exception ex){
            return (null);
        }
        
        if(counter ==0 && reader <0)return (null);
        return new String(lineHolder,0,counter);
    }
     void begin(){
         StringTokenizer token;
         int cases = Integer.parseInt(PokemonGoGo.readLn(255).trim());
          nodes = new Node[cases+1];
          nodes[0] = new Node("Starting",0,0);
          adj = new int[nodes.length][nodes.length];
         for (int i = 0; i < cases; i++) {
             token = new StringTokenizer(PokemonGoGo.readLn(255).trim());
             int x = Integer.parseInt(token.nextToken().trim());
             int y = Integer.parseInt(token.nextToken().trim());
             String name = token.nextToken();
             nodes[i+1] = new Node(name,x,y);
         }
         for(int i=0; i<adj.length; i++){
             for(int j=0; j<adj[i].length; j++){
                 if(i==j){
                     adj[i][j] = Integer.MAX_VALUE;
                 }else
                 adj[i][j] = distance(nodes[i],nodes[j]);
             }
         }
         int[] parent =analyze(adj);
         int min =0;
         for(int i=0; i<parent.length; i++){
             //-1(i.e. 0, the index that has -1) will be linked with the index that has -3 while -2 will be ignored
             if(parent[i] ==-2  || parent[i] ==-1){
             }else{
                 min += adj[i][parent[i]];
             }
         }
         //complete the cycle
           min += adj[0][cycle];
         System.out.println(min);
         
         
     }
     int distance(Node n1, Node n2){
         return Math.abs((n2.x - n1.x)+(n2.y - n1.y));
     }

    private int[] analyze(int[][] adj) {
        boolean[] mstSet = new boolean[adj.length];
        int parent[] = new int[adj.length];
        int key[] = new int[adj.length];
        ArrayList<String> names = new ArrayList<>();
        
        for (int i = 0; i < key.length; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
            parent[i] = -3;
        }
        key[0] = 0; //to make it the first to visit
        parent[0] = -1;
        int lastVisited = -3;
        while(true){
            int min = getMin(key, mstSet,names);
            if(min == -4)break;//all nodes has been traversed
            mstSet[min] = true;//marking visited
            names.add(nodes[min].name);//putting the names of visited to avoid
            //putting pokemon of the same in a later time
            
            for(int j=0; j<adj[min].length; j++){
                //updating the minimum keys for the adjacent nodes of the current minimum by checking 3 things
                //1. that the weight is valid for existing connections
                //2. that the node has not been visited
                //3. replacing the keys of any of the adjacent nodes witht the minimum 
                //between the weight of the edge formed b/w the current min and each node and the 
                //value of the adjacent node on the key array
                if(adj[min][j] != Integer.MAX_VALUE && !mstSet[j] && adj[min][j] < key[j]){
                    //check if a pokemon of the same value has been visited before
                    if(names.contains(nodes[j].name)){
                        //then mark it visited because is not need
                        mstSet[j] = true;
                        //put the parent to -2 so to show that it's not visited
                        //note that -1 has been given to starting node while -3 is being retained by 
                        //the last visited node which will be eventually connected to the initial node
                        parent[j] = -2;
                    }else{
                        key[j] = adj[min][j]; parent[j] = min;
                        //notice that the current valid unvisitd nodes adjacent to the just visited node
                        //that are given parent node equal to the just visited node, this is because, one of them will 
                        //eventually become visited and it should retain the parent assign to it for final calculations
                    }
                }else{
                    //last visited node will entere here because all nodes will have been visited
                    //such that the condition above will not be met
                    //store the index of this somewhere because it will be used to link back the starting
                    //node in order to complete the cycle
                    cycle = min;
                }
            }
        }
        return parent;
        
    }

    private int getMin(int[] key, boolean[] mstSet, ArrayList<String> names) {
        int min = Integer.MAX_VALUE, minIndex =-4; 
        for(int i=0; i<key.length; i++){
            if(key[i] < min && !mstSet[i] && !names.contains(nodes[i].name)){
                min = key[i]; minIndex = i;
            }
        }
        return minIndex;
    }
    public static void main(String[] args) {
        PokemonGoGo pk = new PokemonGoGo();
        pk.begin();
    }
     
     class Node{
         String name;
         int x;
         int y;
         Node(String name, int x, int y){
             this.name = name; 
             this.x = x;
             this.y = y;
         }
     }
     
}
