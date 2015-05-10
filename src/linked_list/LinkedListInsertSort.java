package linked_list;

import java.util.Arrays;
import java.util.Random;

/**
 * 有序链表 对数组进行插入排序
 * @author stone
 */
public class LinkedListInsertSort<T extends Comparable<T>> {
	
	private Link<T> first;		//首结点
	public LinkedListInsertSort() {
		
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void sortList(T[] ary) {
		if (ary == null) {
			return;
		}
		//将数组元素插入进链表,以有序链表进行排序
		for (T data : ary) {
			insert(data);
		}
		//
		
	}
	
	public void insert(T data) {// 插入 到 链头, 以从小到大排序
		Link<T> newLink = new Link<T>(data);
		Link<T> current = first, previous = null;
		while (current != null && data.compareTo(current.data) > 0) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			first = newLink;
		} else {
			previous.next = newLink;
		}
		newLink.next = current;
	}
	
	public Link<T>  deleteFirst() {//删除 链头
		Link<T> temp = first;
		first = first.next; //变更首结点，为下一结点
		return temp;
	}
	
	public Link<T> find(T t) {
		Link<T> find = first;
		while (find != null) {
			if (!find.data.equals(t)) {
				find = find.next;
			} else {
				break;
			}
		}
		return find;
 	}
	
	public Link<T> delete(T t) {
		if (isEmpty()) {
			return null;
		} else {
			if (first.data.equals(t)) {
				Link<T> temp = first;
				first = first.next; //变更首结点，为下一结点
				return temp;
			}
		}
		Link<T> p = first;
		Link<T> q = first;
		while (!p.data.equals(t)) {
			if (p.next == null) {//表示到链尾还没找到
				return null;
			} else {
				q = p;
				p = p.next;
			}
		}
		
		q.next = p.next;
		return p;
	}
	
	public void displayList() {//遍历
		System.out.println("List (first-->last):");
		Link<T> current = first;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
	}
	
	public void displayListReverse() {//反序遍历
		Link<T> p = first, q = first.next, t;
		while (q != null) {//指针反向，遍历的数据顺序向后
			t = q.next; //no3
			if (p == first) {// 当为原来的头时，头的.next应该置空
				p.next = null;
			}
			q.next = p;// no3 -> no1  pointer reverse
			p = q; //start is reverse
			q = t; //no3 start
		}
		//上面循环中的if里，把first.next 置空了, 而当q为null不执行循环时，p就为原来的最且一个数据项，反转后把p赋给first
		first = p; 
		displayList();
	}
	
	class Link<T> {//链结点
		T data;		//数据域
		Link<T> next; //后继指针，结点		链域
		Link(T data) {
			this.data = data;
		}
		void displayLink() {
			System.out.println("the data is " + data.toString());
		}
	}
	
	public static void main(String[] args) {
		LinkedListInsertSort<Integer> list = new LinkedListInsertSort<Integer>();
		Random random = new Random();
		int len = 5;
		Integer[] ary = new Integer[len];
		for (int i = 0; i < len; i++) {
			ary[i] = random.nextInt(1000);
		}
		System.out.println("----排序前----");
		System.out.println(Arrays.toString(ary));
		System.out.println("----链表排序后----");
		list.sortList(ary);
		list.displayList();
	}
}
