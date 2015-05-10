package linked_list;

/**
 * 链表实现 队列  用双端链表实现
 * @author stone
 *
 */
public class LinkQueue<T> {
	private TwoEndpointList<T> list;
	
	public LinkQueue() {
		list = new TwoEndpointList<T>();
	}
	//插入队尾
	public void insert(T data) {
		list.insertLast(data);
	}
	//移除队头
	public T remove() {
		return list.deleteHead();
	}
	//查看队头
	public T peek() {
		return list.peekHead();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public static void main(String[] args) {
		LinkQueue<Integer> queue = new LinkQueue<Integer>();
		for (int i = 1; i < 5; i++) {
			queue.insert(i);
		}
		for (int i = 1; i < 5; i++) {
			Integer peek = queue.peek();
			System.out.println("peek:" + peek);
		}
		for (int i = 1; i < 5; i++) {
			Integer remove = queue.remove();
			System.out.println("remove:" + remove);
		}
		
		System.out.println("----");
		
		for (int i = 5; i > 0; i--) {
			queue.insert(i);
		}
		for (int i = 5; i > 0; i--) {
			Integer peek = queue.peek();
			System.out.println("peek2:" + peek);
		}
		for (int i = 5; i > 0; i--) {
			Integer remove = queue.remove();
			System.out.println("remove:" + remove);
		}
	}
}
