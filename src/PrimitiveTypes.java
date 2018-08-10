import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Questions related to bit manipulation and primitive types.
 * Tricky is the bit manipulation
 * Bit Operators: &(And),|(Or),^(XOR),~(compliment)
 * Bit Shift: 
 * 			1. Left Shift Operator: << (op1 << op2) :Shifts bits of op1 left by distance op2; fills with 0 bits on the right side. Excess bits shifted off to the left are discarded. Zero bits are shifted in from the right
 * 			2. Right Shift Operator: >> (op1 >> op2) :Shifts bits of op1 right by distance op2; fills with highest (sign) bit on the left side
 * 			3. Unsigned Right Shift Operator: >>> (op1 >>> op2) :Shifts bits of op1 right by distance op2; fills with 0 bit on the left side
 * 
 * Examples:
 * Left shift operator:
 * 					12<<2 : 00000000 00000000 00000000 00001100 << 2(times) 
 * 					Output:  00000000 00000000 00000000 00110000
 * 					-12<<2: 11111111 11111111 11111111 11110100 << 2 (Note: for -ve numbers complement digits and add 1; complement 0s and 1s from 12 and add 1 to binary)
 * 					Output: 11111111 11111111 11111111 11010000
 * 
 * Right Shift Operator:
 * 					12>>1 : 00000000 00000000 00000000 00001100 >> 1
 * 					Output: 00000000 00000000 00000000 00000011
 * 					-12>>2: 11111111 11111111 11111111 11110100 >> 2
 * 					Output: 11111111 11111111 11111111 11111101
 * 
 * Unsigned Right Shift Operator: 
 * 					12>>>2 : 00000000 00000000 00000000 00001100 >> 2
 * 					Output: 00000000 00000000 00000000 00000011
 * 					-12>>>2: 11111111 11111111 11111111 11110100 >> 2
 * 					Output: 00111111 11111111 11111111 11111101
 * 
 * 
 * Bitwise Operators:
 * 1. And (&): n1&n2: if both n1 and n2 is 1,1;0 otherwise
 * 2. Or (1): n1|n2: if both n1 and n2 is 0	,0;1 otherwise
 * 3. Xor (^): n1^n2: if n1 and n2 are different,1; 0 otherwise
 * 4. Compliment (~): ~n1: reverse bits		(2's compliment)		
 * 			
 * @author Sahibdeep Singh
 *
 */
public class PrimitiveTypes {

	/**
	 * Calculate the number of 1s in an integer
	 * 
	 * @param x
	 */
	public static short countBits(int x) {

		short count = 0;

		while (x != 0) {
			count += (x & 1);
			x >>>= 1;
		}
		return count;
	}

	/**
	 * parity bit is used in error detecting code. This bit is sit to 1 if the
	 * number of 1s in the word are odd and 0 if the number of 1s are even. It
	 * is added to the the leftmost bit. wiki article:
	 * https://en.wikipedia.org/wiki/Parity_bit
	 * 
	 * Problem statement: check the parity of a given number. Return 1 or 0
	 * based on the number of 1s in the given number.
	 * Hint: (num & num-1) will remove the rightmost 1.
	 * 
	 * @param num
	 */
	public static int checkParity(int num) {
		int parity = 0;
		while (num != 0) {
			num = (num & num - 1);
			parity ^= 1;
		}
		return parity;
	}


	/**
	 * Convert al rightmost 0 bits to 1. 
	 * Ex: 00100 -> 00111
	 */
	public static int rightProp(int num) {
		return (num | num - 1);
	}
	
	/**
	 * Calculate x modulus power of 2
	 * Ex: 77 mod 64=13
	 */
	public static int calcMod(int n1,int n2){
		
		return (n1 & (n2-1));
	}
	
	/**
	 * Swap bits in a 64 bit word on ith and jth position.
	 * Ex 110000,2,5=100100
	 * Hint: 1. Check if both the bits at ith and jth are same
	 * 		 2. Use bitmask to flip bits if not same. (Use left signed operation to move position i and j)
	 * @param num
	 * @param i
	 * @param j
	 * @return
	 */
	public static long swapBits(long num, int i, int j) {
		if(i>=j)
			return -1L;
		
		if(((num>>>i) &1) == ((num >>> j) &1))
			return num;
		//move 1 to ith and jth position and then or. This will give the bitmask to be xored with.
		
		long bitMask=1L << i | 1L << j;
		return num^bitMask;
	}
	
	
	public static double sqrt(int N){
		double X=1;
		for(int i=0;i<=N/2;i++){
			double fX=Math.pow(X, 2)-N;
			double f_X=2*X;
			X=X-(fX/f_X);
		}
		return X;
	}
	
	/*
	 * reverse integer and check for reverse integer overflow
	 */
public static int reverseInt(int x){

	long result=0;
	int xRemaining=Math.abs(x);
	while (xRemaining>0){
		//1132 2 + x%10=113 x/10 3 x%10=11	
		result=result*10+xRemaining%10;
		if(result>Integer.MAX_VALUE)
			return 0;
		xRemaining=xRemaining/10;
	}
	return x>=0?(int)result:-(int)(result);
}

public static String longestPrefix(String[] strs){
	String common="";
	if(strs.length<2)
		return "";
	
	
	String firstElem=strs[0];
	
	for(int c=0;c<firstElem.length();c++)
	{
		int count=0;
		for (int i=1;i<strs.length;i++){
			
			if(c>=strs[i].length())
				break;
			
			if(strs[i].charAt(c)==firstElem.charAt(c)){
				count++;
			if(count==strs.length-1)
				common+=firstElem.charAt(c);
			}
			
		}
		if(count!=strs.length-1)
			break;
	}
	return common;
}

/**
 * check for valid parentheses
 * @param s
 * @return
 */
public static boolean isValid(String s) {
 	boolean valid=false;
    
if(s=="")
  return true;
    
if(s.length()%2!=0)
	return valid;

Stack<Character> st=new Stack<>();

for(int i=0;i<s.length();i++){
	char out=s.charAt(i);	
	if(st.empty())
	st.push(s.charAt(i));
	else{
		char in=st.peek();
		if((in=='(' && out==')') || (in=='{' && out=='}') || (in=='[' && out==']')){
			st.pop();
			valid =true;
	}
			else{
				st.push(s.charAt(i));
				valid =false;
			}
		}
	}

if(!st.isEmpty())
	return false;

return valid;
}



	public static void main(String[] args) {
		// number of 1s
		System.out.println(countBits(4));
		// number of 0s
		System.out.println(32 - countBits(10));
		System.out.println(rightProp(4));
		System.out.println(checkParity(4));//1
		System.out.println(checkParity(6));//0
		System.out.println(calcMod(77,64));
		System.out.println(swapBits(4, 0, 2));
		System.out.println(sqrt(5863332));

		System.out.println(reverseInt(1534236469));
		
		String[] strs={"flower","flow","flight"};
		System.out.println(longestPrefix(strs));
		

		String[] strs1={"doger","dog","flight"};
		System.out.println(longestPrefix(strs1));
		
		
		String[] strs2={"flower","flow","folwer"};
		System.out.println(longestPrefix(strs2));
		
		String[] strs3={"a"};
		System.out.println(longestPrefix(strs2));
		
		System.out.println(isValid("{[]}"));
		System.out.println(isValid("{}[]()"));
		System.out.println(isValid("{[]()"));
		System.out.println(isValid(""));
		System.out.println(isValid("(()("));
		System.out.println(isValid("}}(]}}){)(])](}{{}[]"));
 	}

}
