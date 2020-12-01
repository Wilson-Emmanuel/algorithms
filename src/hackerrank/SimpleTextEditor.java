package hackerrank;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Wilson on
 * Dec. 01, 2020
 *
 * Problem
 * ============
 * In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, . You must perform  operations of the following  types:
 *
 * append - Append string  to the end of .
 * delete - Delete the last  characters of .
 * print - Print the  character of .
 * undo - Undo the last (not previously undone) operation of type  or , reverting  to the state it was in prior to that operation.
 */
public class SimpleTextEditor {


    private static final Scanner scanner = new Scanner(System.in);
    private static String S = "";
    private static Stack<String[]> operations = new Stack<>();
    public static void append(String W,boolean undoable){
        S = S + W;

        if(!undoable)return;
        //push a delete operation into the stack
        operations.push(new String[]{"D",""+W.length()});
    }
    public static void delete(int k, boolean undoable){
        int len = S.length();
        String toRemove = S.substring(len-k);
        S = S.substring(0,len-k);

        if(!undoable)return;
        //push an append operation into the stack
        operations.push(new String[]{"A",toRemove});
    }
    public static void print(int k) {
        System.out.println(S.charAt(k-1));
    }
    public static void undo(){

        String[] topOperation = operations.pop();

        if(topOperation[0].equals("D")){
            delete(Integer.parseInt(topOperation[1]),false);
        }else {
            append(topOperation[1],false);
        }
    }


    public static void main(String[] args) {

        String[] items = {
                "1 abc",
                "3 3",
                "2 3",
                "1 xy",
                "3 2",
                "4",
                "4",
                "3 1"
        };

        String[] hItems;
        for (int i = 0; i < items.length; i++) {
            hItems = items[i].split(" ");
            if(hItems[0].equals("1")){
                append(hItems[1],true);
            }else if(hItems[0].equals("2")){
                delete(Integer.parseInt(hItems[1]),true);
            }else if(hItems[0].equals("3")){
                print(Integer.parseInt(hItems[1]));
            }else if(hItems[0].equals("4")){
                undo();
            }
        }

    }
}
