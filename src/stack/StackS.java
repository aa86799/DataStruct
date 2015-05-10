package stack;

public class StackS<T> {
	private int max;
	private T[] ary;
	private int top;	//指针，指向栈顶元素的下标
	
	public StackS(int size) {
		this.max = size;
		ary = (T[]) new Object[max];
		top = -1;
	}
	
	// 入栈
	public void push(T data) {
		if (!isFull())
			ary[++top] = data;
	}
	
	// 出栈
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		return ary[top--];
	}
	
	// 查看栈顶
	public T peek() {
		return ary[top];
	}
	
	//栈是否为空
	public boolean isEmpty() {
		return top == -1;
	}
	
	//栈是否满
	public boolean isFull() {
		return top == max - 1;
	}
	
	//size
	public int size() {
		return top + 1;
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
