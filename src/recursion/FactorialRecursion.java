package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 一般递归
 * n的阶乘，即n! (n*(n-1)*(n-2)*...1), 0!为什么=1，因为1!=1*0!，所以0!=1
 */
public class FactorialRecursion {
	
	static long fact(long n) {
		if (n < 0) return 0;
	    else if (n <= 1) return 1;//n == 1 || n == 0
		return n * fact(n - 1); //占用的临时内存空间随着层级越来越大，直至有直接返回值时，再从底层一路向上返回到顶层
	}
	
	//如果一个函数的递归形式的调用出现在函数的末尾，则称为 尾递归函数
	static long fact(long n, long lastValue) {//a表示要被相乘的上次求出的数，初始值应为1
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
			line = br.readLine();
		}
		System.out.println("-----程序退出-----");
		Runtime.getRuntime().exit(0);
	}
	
	//单链表
	Link head;
	class Link<T> {
		T data;
		Link next;
	}
	//双端链表
	Link1 head1;
	Link1 rear;
	class Link1<T> {
		T data;
		Link1 next;
	}
	//双向链表，可心是双端链表，非必须
	Link2 hear2;
	class Link2<T> {
		T data;
		Link2 previous;
		Link2 next;
	}
}
