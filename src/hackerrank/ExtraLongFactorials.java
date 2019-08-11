
package hackerrank;

import java.math.BigInteger;
/*
The factorial of the integer , written , is defined as:

Calculate and print the factorial of a given integer.

For example, if , we calculate  and get .

Function Description

Complete the extraLongFactorials function in the editor below. It should print the result and return.

extraLongFactorials has the following parameter(s):

n: an integer
Note: Factorials of  can't be stored even in a  long long variable. Big integers must be used for such calculations. Languages like Java, Python, Ruby etc. can handle big integers, but we need to write additional code in C/C++ to handle huge values.

We recommend solving this challenge using BigIntegers.

Input Format

Input consists of a single integer 

Constraints


Output Format

Print the factorial of .

Sample Input


Sample Output


Explanation



*/
/**
 *
 * @author Wilson
 */
public class ExtraLongFactorials {
    public static void main(String[] args) {
        
        System.out.println(new ExtraLongFactorials().fact(BigInteger.valueOf(30)).toString().length());
    }
    
    public BigInteger fact(BigInteger num){
        if(num.equals(BigInteger.ONE) || num.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        
        return num.multiply(fact(num.subtract(BigInteger.ONE)));
    }
}
