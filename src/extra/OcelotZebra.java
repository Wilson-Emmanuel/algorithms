
package extra;

import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author wilson
 * Kattis Problem : Ocelot and Zebra
 * https://open.kattis.com/problems/zebrasocelots
 */
public class OcelotZebra {
    
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
        LinkedList<String> animals = new LinkedList<>();
        String line = OcelotZebra.readLn(255).trim();
        int animalsCount = Integer.parseInt(line);
        for(int i=0; i<animalsCount; i++){
            line = OcelotZebra.readLn(255).trim();
            animals.addFirst(line.toLowerCase());
        }
        //animal list done
        int counter = 0;
        Stack<String> store = new Stack<>();
        while(animals.contains("o")){
            counter++;
            //start dequeing
            String animal = animals.removeLast();
            while(animal.equalsIgnoreCase("z")){
                store.push(animal);
                if(!animals.isEmpty()){
                    animal = animals.removeLast();
                }else break;
            }
            //the last animal removed is o and/or animals list is empty
            if(animals.isEmpty() && !animal.equalsIgnoreCase("o")){
                break; //no O again
            }else{
                //o was encountered or o was encountered at the end thereby making animals to be empty as well
                //change the o to z and put is back to the animals list
                animals.addLast("z");
                //add all the animals in the store
                //changing the z to o
                while(!store.isEmpty()){
                    String animalZ = store.pop();
                    animals.addLast("o");
                }
            }
        }
        System.out.println(counter);
        
    }
    void begin2(){
        String line = OcelotZebra.readLn(255).trim();
        int animalsCount = Integer.parseInt(line);
        String[] animals = new String[animalsCount];
        for(int i=0; i<animalsCount; i++){
            line = OcelotZebra.readLn(255).trim();
            animals[i] = line.trim().toLowerCase();
        }
        //animal list done
        int counter = 0;
       for(int j = animalsCount-1; j>=0; j--){
           if(animals[j].equalsIgnoreCase("o")){//**
               animals[j] = "z";
               for(int n = j+1; n<animalsCount; n++){
                   animals[n] = "o";
               }
               j = animalsCount; //reset the counting loop for more bell ringing
               //this will continue infinitely but the if will be stopped when there is 
               //no more O by the ** check
               counter++;
           }
       }
        System.out.println(counter);
        
    }
    
     void begin3(){
         String line = OcelotZebra.readLn(255).trim();
        int animalsCount = Integer.parseInt(line);
        String[] animals = new String[animalsCount];
        for(int i=0; i<animalsCount; i++){
            line = OcelotZebra.readLn(255).trim();
            animals[i] = line.trim();
        }
        //animal list done
        int counter = 0;
        int index = animals.length-1;
        while(index >= 0){
            for(index=animals.length-1; index>=0; index--){
                if("O".equals(animals[index])){
                    animals[index] = "Z";           
                    break;
                }else{
                    animals[index] = "O";
                }
            }
            if(index >=0)counter++;
        }
         System.out.println(counter);
     }
    public static void main(String[] args) {
        OcelotZebra o = new OcelotZebra();
        o.begin3();
    }
}
