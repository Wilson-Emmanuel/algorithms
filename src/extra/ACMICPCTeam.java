package extra;

import java.util.*;

/**
 * 
 * @author Wilson
 * 
 * Hackerrank Problem : ACM ICPC TEAM
 * From :https://www.hackerrank.com/challenges/acm-icpc-team/problem
 */

public class ACMICPCTeam {
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String[] line1 = scanner.nextLine().split(" ");
                
		int numPersons = Integer.parseInt(line1[0]);
		int numTopics = Integer.parseInt(line1[1]);
		
		String[] topicsPerPerson = new String[numPersons];
		
		for(int i=0; i<numPersons; i++){
			topicsPerPerson[i] = scanner.nextLine();
		}
		
		
		ACMICPCTeam a = new ACMICPCTeam();
		ArrayList<Integer> perms = a.compute(topicsPerPerson, numPersons, numTopics);
                
                System.out.println("");
                int max = Collections.max(perms);
                System.out.println(max);
                System.out.println(Collections.frequency(perms, max));
                
             /*System.out.println("");
             int[] output = a.computeImproved(topicsPerPerson, numPersons, numTopics);
             System.out.println(output[0]);
             System.out.println(output[1]);*/
             
	}
	
	private ArrayList<Integer> compute(String[] topicsPerPerson, int numPersons, int numTopics) {
		ArrayList<Integer> perms = new ArrayList();
                
                for(int i=0; i<(numPersons-1); i++){
                    
                    for(int j=i; j<numPersons; j++){
                        
                       //(firstPerson, secondPerson)
                       String firstPerson = topicsPerPerson[i];
                       String secondPerson = topicsPerPerson[j];
                       int counter = 0;
                       
                       for(int k=0; k<numTopics; k++){
                           if(firstPerson.charAt(k) == '1' || secondPerson.charAt(k) == '1'){
                               counter += 1;
                           }
                       }
                       perms.add(counter);
                    }
                }
                
                return perms;
		
	}
	private int[] computeImproved(String[] topicsPerPerson, int numPersons, int numTopics) {
		//Without using an ArrayList, because there is no need storing all the permutations which i may no need
                int highest = 0;
                int occur = 0;
                for(int i=0; i<(numPersons-1); i++){
                    
                    for(int j=i; j<numPersons; j++){
                        
                       //(firstPerson, secondPerson)
                       String firstPerson = topicsPerPerson[i];
                       String secondPerson = topicsPerPerson[j];
                       int counter = 0;
                      
                       for(int k=0; k<numTopics; k++){
                           if(firstPerson.charAt(k) == '1' || secondPerson.charAt(k) == '1'){
                               counter += 1;
                           }
                       }
                       if(highest < counter){
                           highest = counter;
                           occur = 1;
                       } else if(highest > counter){
                           //don't do anything, we don't need it
                       } else{
                           //if it is equal to  highest
                           occur += 1;
                       }
                    }
                }
                
                return new int[]{highest, occur};
		
	}

	private int factorial(int n){
		int result = 1;
		
		for(int i=1; i<=n; i++){
			result *= i;
		}
		return result;
		
	}
}
