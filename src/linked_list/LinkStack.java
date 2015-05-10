package linked_list;
/**
 * 使用链表实现栈  ，用前插 单链表就能实现，
 * 本类采用双端链表实现
 * @author stone
 *
 */
public class LinkStack<T> {
	private TwoEndpointList<T> datas;
	
	public LinkStack() {
		datas = new TwoEndpointList<T>();
	}
	
	// 入栈
	public void push(T data) {
		datas.insertFirst(data);
	}
	
	// 出栈
	public T pop() {
		return datas.deleteHead();
	}
	
	// 查看栈顶
	public T peek() {
		return datas.peekHead();
	}
	
	//栈是否为空
	public boolean isEmpty() {
		return datas.isEmpty();
	}
	
	public static void main(String[] args) {
		LinkStack<Integer> stack = new LinkStack<Integer>();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		for (int i = 0; i < 5; i++) {
			Integer peek = stack.peek();
			System.out.println("peek:" + peek);
		}
		for (int i = 0; i < 6; i++) {
			Integer pop = stack.pop();
			System.out.println("pop:" + pop);
		}
		
		System.out.println("----");
		for (int i = 5; i > 0; i--) {
			stack.push(i);
		}
		for (int i = 5; i > 0; i--) {
			Integer peek = stack.peek();
			System.out.println("peek:" + peek);
		}
		for (int i = 5; i > 0; i--) {
			Integer pop = stack.pop();
			System.out.println("pop:" + pop);
		}
	}
}
