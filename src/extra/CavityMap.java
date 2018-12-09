
package extra;

import java.util.Scanner;

/*
*See problem here : https://www.hackerrank.com/challenges/cavity-map/problem
*/

public class CavityMap {
    
    static String[] cavityMap(String[] grid){
        int len = grid.length;
        char[][] matrix = new char[len][len];
        char[][] sec = new char[len][len];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = grid[i].trim().toCharArray();
            sec[i] = grid[i].trim().toCharArray();
        }
        
        for(int i=1; i<matrix.length-1; i++){
            for(int j=1; j<matrix[i].length-1; j++){
                
                    int up = Integer.parseInt(String.valueOf(matrix[i-1][j]));
                    int down = Integer.parseInt(String.valueOf(matrix[i+1][j]));
                    int right = Integer.parseInt(String.valueOf(matrix[i][j+1]));
                    int left = Integer.parseInt(String.valueOf(matrix[i][j-1]));
                    int cur = Integer.parseInt(String.valueOf(matrix[i][j]));
                    if(cur >up && cur >down && cur >right && cur>left){
                        sec[i][j] ='X';
                    }
                
            }
        }
        String line;
        for(int i=0; i<matrix.length; i++){
             line ="";
            for(int j=0; j<matrix.length; j++){
                line +=String.valueOf(sec[i][j]);
            }
            grid[i] =line;
        }
        return grid;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       
        int num = Integer.parseInt(sc.nextLine());
        String[] grid = new String[num];
        for (int i = 0; i < num; i++) {
            grid[i] = sc.nextLine().trim();
        }
        System.out.println("");
        grid = cavityMap(grid);
        for(int i=0; i<grid.length; i++){
            System.out.println(grid[i]);
        }
    }
}
