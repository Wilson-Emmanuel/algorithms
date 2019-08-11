
package hackerrank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author owner
 */
public class NonDivisibleSubsetImpr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first[] = sc.nextLine().trim().split(" ");
        String second[] = sc.nextLine().trim().split(" ");
        int[] arr = new int[Integer.parseInt(first[0])];
        for (int i = 0; i < Integer.parseInt(first[0]); i++) {
            arr[i]  = Integer.parseInt(second[i]);
            
        }
        NonDivisibleSubsetImpr non = new NonDivisibleSubsetImpr();
        int ans =non.calc(arr, Integer.parseInt(first[1]));
        System.out.println(ans);
    }
    
    public int calc(int[] arr, int k){
        HashMap<Integer,Item> pairs = new HashMap<>();
        for(int i=0; i<arr.length-1; i++){
            
            for(int j=i+1; j<arr.length; j++){
                if((arr[i] + arr[j])%k ==0){
                    
                    int first = arr[i];
                    int second = arr[j];
                    Item already_cur1 = pairs.getOrDefault(first, null);
                    if(already_cur1 != null){
                        already_cur1.freq=already_cur1.freq+1;
                        already_cur1.list.add(second);
                    }else{
                        already_cur1 = new Item(first,1);
                        already_cur1.list.add(second);
                    }
                    pairs.put(first,already_cur1);
                    
                    Item already_cur2= pairs.getOrDefault(second, null);
                    if(already_cur2 != null){
                        already_cur2.freq=already_cur2.freq+1;
                        already_cur2.list.add(first);
                    }else{
                        already_cur2=new Item(second,1);
                        already_cur2.list.add(first);
                    }
                    pairs.put(second, already_cur2);
                    
                }
            }
        }
        //get all the value of the map as an arrayList
        Collection<Item> mapValues = pairs.values();
        LinkedList<Item> arrItems = new LinkedList<>(mapValues);
        
        Collections.sort(arrItems);
        int countRemoved =0;
        for(int i=0; i<arrItems.size(); i++){
            System.out.println("\n\n");
            print(arrItems);
          Item top = arrItems.get(i);
          if(!top.list.isEmpty()){
              countRemoved++;
              //loop through the remaing item arrays to remove this just removed item
              for(int j=i+1; j<arrItems.size(); j++){
                  Item it = arrItems.remove(j);
                  it.list.remove(new Integer(top.value));
                  arrItems.add(j,it);
              }
             // arrItems.remove(i);
          }
        }
        return arr.length - countRemoved;
    }
    public static void print(LinkedList<Item> arr){
        for(int i=0; i<arr.size(); i++){
            System.out.println("index "+i+" "+arr.get(i));
        
        }
    }
    
    class Item implements Comparable<Item>{
         int value;
         int freq;
        LinkedList<Integer> list = new LinkedList<>();
        Item(int value, int freq){
            this.value = value;
            this.freq = freq;
        }

        @Override
        public int compareTo(Item o) {
            if(this.freq < o.freq)return 1;
            if(this.freq > o.freq)return -1;
            return 0;
        }
        @Override
        public String toString(){
            String text = "Value = "+this.value+", Freq = "+freq+", [";
            for(Integer i: list){
                text += i+",";
            }
            text += "]";
            return text;
        }
    }
    
}
