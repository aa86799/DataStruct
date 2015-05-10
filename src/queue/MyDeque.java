package queue;

import java.util.LinkedList;

/*
 * 双端队列	两端插入、删除
 */
public class MyDeque<T> {
	private LinkedList<T> list;

	public MyDeque() {
		list = new LinkedList<T>();
	}

	// 插入队头
	public void insertLeft(T t) {
		list.addFirst(t);
	}

	// 插入队尾
	public void insertRight(T t) {
		list.addLast(t);
	}

	// 移除队头
	public T removeLeft() {
		return list.removeFirst();
	}

	// 移除队尾
	public T removeRight() {
		return list.removeLast();
	}

	// 查看队头
	public T peekLeft() {
		return list.getFirst();
	}

	// 查看队尾
	public T peekRight() {
		return list.getLast();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}

}
