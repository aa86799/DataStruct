package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1,3,6,10,15...n  三角数
/*
 * #			1
 * ##			1+2
 * ###			1+2+3
 * ####			1+2+3+4
 * #####		1+2+3+4+5
 * ...第1层为1， 第n层等于  n+f(n-1)
 */
public class TriangleNumber {
	
	static long triangle(long n) {
		if (n < 1) return 0;
		if (n == 1) return 1;
		return n + triangle(n - 1);
	}
	
	static long triangle(long n, long last) {//last初始传0   尾递归
		if (n < 1) return 0;
		if (n == 1) return 1 + last;
		return triangle(n - 1, n + last);
	}
	
	static long str2long(String num) {
		return Long.valueOf(num);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("-----程序开始，要计算第多少位的三角数值，请输入位数数字-----");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("exit")) {
			long n = str2long(line);
			System.out.println(triangle(n));
			System.out.println(triangle(n, 0));
			line = br.readLine();
		}
		System.out.println("-----程序退出-----");
		Runtime.getRuntime().exit(0);
		
		
	}
}
