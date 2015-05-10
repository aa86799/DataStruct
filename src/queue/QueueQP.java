package queue;

import java.util.Arrays;

/*
 * 优先级队列	队列中按优先级排序，是一个有序的队列
 */
public class QueueQP {
	private int max;
	private int[] ary;
	private int nItems; //实际数据项个数
	
	public QueueQP(int size) {
		this.max = size;
		ary =  new int[max];
		nItems = 0;
	}
	//插入队尾
	public void insert(int t) {
		int j;
		if (nItems == 0) {
			ary[nItems++] = t;
		} else {
			for (j = nItems - 1; j >= 0; j--) {
				if (t > ary[j]) {
					ary[j + 1] = ary[j]; //前一个赋给后一个  小的在后		相当于用了插入排序，给定序列本来就是有序的，所以效率O(N)
				} else {
					break;
				}
			}
			ary[j + 1] = t;
			nItems++;
		}
		System.out.println(Arrays.toString(ary));
	}
	//移除队头
	public int remove() {
		return ary[--nItems]; //移除优先级小的
	}
	//查看队尾 优先级最低的
	public int peekMin() {
		return ary[nItems - 1];
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
		QueueQP queue = new QueueQP(3);
		queue.insert(1);
		queue.insert(2);
		queue.insert(3);
		int remove = queue.remove();
		System.out.println("remove:" + remove);
		
	}
	
}
