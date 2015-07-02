package queue;
/*
 * 队列	先进先出，一个指针指示插入的位置，一个指针指示取出数据项的位置
 * 
 * 实际使用时，size不是固定的，可以自动增长，如size不足，扩充一倍；内部ary复制成一个新的长度的ary
 */
public class QueueQ<T> {
	private int max;
	private T[] ary;
	private int front; //队头指针  指示取出数据项的位置
	private int rear;  //队尾指针  指示插入的位置
	private int nItems; //实际数据项个数
	
	public QueueQ(int size) {
		this.max = size;
		ary = (T[]) new Object[max];
		front = 0;
		rear = -1;
		nItems = 0;
	}
	//插入队尾
	public void insert(T t) {
		if (rear == max - 1) {//已到实际队尾，从头开始
			rear = -1;
		}
		ary[++rear] = t;
		if (nItems < max) {
			nItems++;
		}
	}
	//移除队头
	public T remove() {
		T temp = ary[front++];
		if (front == max) {//列队到尾了，从头开始
			front = 0;
		}
		nItems--;
		return temp;
	}
	//查看队头
	public T peek() {
		return ary[front];
	}
	
	public boolean isEmpty() {
		return nItems == 0;
	}
	
	public boolean isFull() {
		return nItems == max;
	}
	
	public int size() {
		return nItems;
	}
	
	public static void main(String[] args) {
		QueueQ<Integer> queue = new QueueQ<Integer>(3);
		for (int i = 0; i < 5; i++) {
			queue.insert(i); //012  312 342
			System.out.println("size:" + queue.size());
		}
		for (int i = 0; i < 5; i++) {
			Integer peek = queue.peek();
			System.out.println("peek:" + peek); //3
			System.out.println("size:" + queue.size());
		}
		System.out.println("----");
		for (int i = 0; i < 5; i++) {
			Integer remove = queue.remove();
			System.out.println("remove:" + remove);
			System.out.println("size:" + queue.size());
		}
		System.out.println("----");
		
		for (int i = 5; i > 0; i--) {
			queue.insert(i);
			System.out.println("size:" + queue.size());
		}
		for (int i = 5; i > 0; i--) {
			Integer peek = queue.peek();
			System.out.println("peek:" + peek);
			System.out.println("size:" + queue.size());
		}
		for (int i = 5; i > 0; i--) {
			Integer remove = queue.remove();
			System.out.println("remove:" + remove);
			System.out.println("size:" + queue.size());
		}
	}
	
}
