package linked_list;

/**
 * 双端队列	两端插入、删除
 * 基于双向链表实现
 * @author stone
 */
public class DequeBaseOfDoublyList<T> {
	private DoublyLinkedList<T> list;

	public DequeBaseOfDoublyList() {
		list = new DoublyLinkedList<T>();
	}

	// 插入队头
	public void insertLeft(T t) {
		list.insertFirst(t);
	}

	// 插入队尾
	public void insertRight(T t) {
		list.insertLast(t);
	}

	// 移除队头
	public T removeLeft() {
		return list.deleteHead();
	}

	// 移除队尾
	public T removeRight() {
		return list.deleteRear();
	}

	// 查看队头
	public T peekLeft() {
		return list.peekHead();
	}

	// 查看队尾
	public T peekRight() {
		return list.peekRear();
	}
	
	public void displayQueue() {
		list.displayList4Head();//从头开始遍历
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		DequeBaseOfDoublyList<Integer> list = new DequeBaseOfDoublyList<Integer>();
		list.insertLeft(1);
		list.insertRight(2);
		list.insertLeft(3);
		list.insertRight(4);
		list.displayQueue();
		System.out.println("size:" + list.size());
		list.removeLeft();
		System.out.println("size:" + list.size());
		list.displayQueue();
		list.removeRight();
		System.out.println("size:" + list.size());
		list.displayQueue();
		
	}
}
