
package extra;

/**
 *
 * @author wilson
 * 
 * Problem : Solving    A^n in Log(n) time complexity
 */
public class APowerN {
  static  double pow(double a, int n){
        if(n ==0)return 1;
        if(n == 1)return a;
        double t = pow(a,n/2);
        return t*t*pow(a,n%2);
    }
    static double powIt(double a, int n){
        double ret = 1;
        while(n>0){
            if(n%2 == 1) ret *= a;
            a *= a;
            n /= 2;
        }
        return ret;
    }
    public static void main(String[] args) {
        System.out.println(pow(2,3));
        System.out.println(powIt(10,5));
    }
}
