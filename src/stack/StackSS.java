package stack;

import java.util.LinkedList;
/**
 * 使用LinkedList存储来实现栈
 * @author stone
 *
 * @param <T>
 */
public class StackSS<T> {
	private LinkedList<T> datas;
	
	public StackSS() {
		datas = new LinkedList<T>();
	}
	
	// 入栈
	public void push(T data) {
		datas.addLast(data);
	}
	
	// 出栈
	public T pop() {
		return datas.removeLast();
	}
	
	// 查看栈顶
	public T peek() {
		return datas.getLast();
	}
	
	//栈是否为空
	public boolean isEmpty() {
		return datas.isEmpty();
	}
	
	//size
	public int size() {
		return datas.size();
	}
	
	public static void main(String[] args) {
		StackS<Integer> stack = new StackS<Integer>(3);
		for (int i = 0; i < 5; i++) {
			stack.push(i);
			System.out.println("size:" + stack.size());
		}
		for (int i = 0; i < 5; i++) {
			Integer peek = stack.peek();
			System.out.println("peek:" + peek);
			System.out.println("size:" + stack.size());
		}
		for (int i = 0; i < 5; i++) {
			Integer pop = stack.pop();
			System.out.println("pop:" + pop);
			System.out.println("size:" + stack.size());
		}
		
		System.out.println("----");
		for (int i = 5; i > 0; i--) {
			stack.push(i);
			System.out.println("size:" + stack.size());
		}
		for (int i = 5; i > 0; i--) {
			Integer peek = stack.peek();
			System.out.println("peek:" + peek);
			System.out.println("size:" + stack.size());
		}
		for (int i = 5; i > 0; i--) {
			Integer pop = stack.pop();
			System.out.println("pop:" + pop);
			System.out.println("size:" + stack.size());
		}
	}
}
