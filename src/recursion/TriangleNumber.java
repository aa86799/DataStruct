package recursion;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//1,3,6,10,15...n  三角数
/*
 * #			1
 * ##			1+2
 * ###			1+2+3
 * ####			1+2+3+4
 * #####		1+2+3+4+5
 * ...第1层为1， 第n层等于  n + (f(n-1))
 */
public class TriangleNumber {
	
	static int triangle(int n) {
		if (n < 1) return 0;
		if (n == 1) return 1;
		return n + triangle(n - 1); //递归到里层计算，从最底层递归向上返回结果并与n计算和
	}
	
	//如果一个函数的递归形式的调用出现在函数的末尾，则称为 尾递归函数
	static int triangle(int n, int last) {//last初始传0   尾递归
		if (n < 1) return 0;
		if (n == 1) return 1 + last;
		return triangle(n - 1, n + last); //递归到里层，最底层直接算好最终结果并返回，
	}
	
	//非递归
	static int triangle2(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += i;
		}
		return sum;
	}
	
	static int str2Int(String num) {
		return Integer.valueOf(num);
	}
	
	//栈保存处理步骤.  这里就是保存 计算过程的 每一步骤 的值
	private static class MyStack {
		int maxSize;
		int[] stackAry;
		int top; //栈顶的索引 
		MyStack(int max) {
		  this.maxSize = max;
		  this.stackAry = new int[maxSize];
		  top = -1;
		}
		
		void push(int n) {
			stackAry[++top] = n;
		}
		
		int pop() {
			return stackAry[top--];
		}
		
		int peek() {
			return stackAry[top];
		}
		
		boolean isEmpty() {
			return top == -1;
		}
	}
	
	//使用栈 实现
	static int triangle3(int n) {
		MyStack stack = new MyStack(n);
		int result = 0;
		while (n > 0) {
			stack.push(n);
			n--;
		}
		while (!stack.isEmpty()) {
			int temp = stack.pop();
			result += temp;
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("-----程序开始，要计算第多少位的三角数值，请输入位数数字 (输入exit结束程序)-----");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("exit")) {
			int n = str2Int(line);
			System.out.println("递归：" + triangle(n));
			System.out.println("尾递归：" + triangle(n, 0));
			System.out.println("非递归：" + triangle2(n));
			System.out.println("栈实现：" + triangle3(n));
			System.out.println();
			
			System.out.println("-----程序开始，要计算第多少位的三角数值，请输入位数数字 (输入exit结束程序)-----");
			line = br.readLine();
		}
		System.out.println("-----程序退出-----");
		Runtime.getRuntime().exit(0);
		
	}
}
