
package extra;

/**
 *
 * @author problem
 * Kattis Problem : Urban Design
 * From : https://open.kattis.com/problems/urbandesign
 */
public class Urban {
    
    static String readLn(int maxLen){
        int[] holder = new int[maxLen];
        int counter =0, reader = -1;
        
        try{
            while(counter < maxLen){
                reader = System.in.read();
                if(reader<0 || (reader =='\n'))break;
                holder[counter++] = reader;
            }
        }catch(Exception ex){
            return (null);
        }
        
        if(counter ==0 && reader <0)return (null);
        return new String(holder,0,counter);
    }
    void begin(){
        int noStreets = Integer.parseInt(Urban.readLn(255).trim());
        Line[] streets = new Line[noStreets];
        for (int i = 0; i < streets.length; i++) {
            String[] line = Urban.readLn(255).trim().split(" ");
            streets[i] = new Line(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            
        }
        int noPpties = Integer.parseInt(Urban.readLn(255).trim());
        Line[] ppties =new Line[noPpties];
        for (int i = 0; i < ppties.length; i++) {
            String[] line = Urban.readLn(255).trim().split(" ");
            ppties[i] = new Line(Integer.parseInt(line[0]),Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            
        }
        
        for (int i = 0; i < ppties.length; i++) {
            Line ppty = ppties[i];
            String ans = "same";
            for(int j=0; j<streets.length; j++){
                Line strt = streets[j];
                if( (strt.y1 == strt.y2 && ppty.x2 == ppty.x1 && across(strt, ppty)) ||
                        (strt.x1 == strt.x2 && ppty.y1 == ppty.y2 && across(strt,ppty)) ||
                        slope(strt)*slope(ppty) == -1 && across(strt,ppty)){
                    ans = "different";
                    System.out.println((slope(strt)*slope(ppty) == -1)+" Slopes street "+slope(strt)+" Slope ppty "+slope(ppty)+ " Street ("+strt.x1+", "+strt.y1+") ("+strt.x2+", "+strt.y2+")"  +" PPty ("+ppty.x1+", "+ppty.y1+") ("+ppty.x2+", "+ppty.y2+")");
                    break;
                }
            }
            System.out.println(ans);
        }
        
    }
    
    double slope(Line line){
        double slope = ((line.y2 - line.y1) / (line.x2 - line.x1));
        return slope;
    }
    boolean across(Line strt, Line ppty){
        boolean pass = false;
        double d1 = (ppty.x1 - strt.x1)*(strt.y2 - strt.y1) - (ppty.y1 - strt.y1)*(strt.x2 - strt.x1);
        double d2 = (ppty.x2 - strt.x1)*(strt.y2 - strt.y1) - (ppty.y2 - strt.y1)*(strt.x2 - strt.x1);
        
        if( (d1 >0 && d2 < 0) || (d1 < 0 && d2 >0)){
            //if the 2 have opposite signs
            pass = true;
        }
        
        return pass;
    }
    
    public static void main(String[] args) {
        Urban ub = new Urban();
        ub.begin();
    }
    
    class Line{
        int x1, x2, y1, y2;
        Line(int x1, int y1, int x2, int y2){
            this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
        }
    }
}
