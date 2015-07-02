package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * n的阶乘，即n! (n*(n-1)*(n-2)*...1)。 
 * 0!为什么=1，因为1!=1*0!，所以0!=1
 * 
 * @author stone
 * @date   2015-1-6 下午18:48:00
 */
public class FactorialRecursion {
	
	static long fact(long n) {
		if (n < 0) return 0;
	    else if (n <= 1) return 1;//n == 1 || n == 0
		return n * fact(n - 1); //占用的临时内存空间随着层级越来越大，直至有直接返回值时，再从底层一路向上返回到顶层
	}
	
	//如果一个函数的递归形式的调用出现在函数的末尾，则称为 尾递归函数
	static long fact(long n, long lastValue) {//last表示要被相乘的上次求出的数，初始值应为1
		if (n < 0) return 0;
	    else if (n == 0) return 1;
	    else if (n == 1) return lastValue;
		return fact(n - 1, n * lastValue);
	}
	
	static long str2long(String num) {
		return Long.valueOf(num);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("-----程序开始，请输入要计算的阶乘数值，-----");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("exit")) {
			long n = str2long(line);
			System.out.println(fact(n));
			System.out.println(fact(n, 1));
			System.out.println(fact2(n));
			line = br.readLine();
		}
		System.out.println("-----程序退出-----");
		Runtime.getRuntime().exit(0);

	}
	
	//计算n的阶乘  非递归法
    private static long fact2(long n) throws IllegalAccessException {  
        if (n == 1 || n == 0) {  
            return 1;  
        }  
        if (n < 0) {  
            throw new IllegalAccessException("参数错误");  
        }  
          
        long sum = 1; // 非递归法  
        for (long i = n; i >= 2; i--) {  
            sum = sum * i;  
        }  
        return sum;  
    }  

}
