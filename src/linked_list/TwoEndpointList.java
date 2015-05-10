package linked_list;
/**
 * 双端链表
 * @author stone
 */
public class TwoEndpointList<T> {
	private Link<T> head;		//首结点
	private Link<T> rear;		//尾部指针
	
	public TwoEndpointList() {
		
	}
	
	public T peekHead() {
		if (head != null) {
			return head.data;
		}
		return null;
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void insertFirst(T data) {// 插入 到 链头
		Link<T> newLink = new Link<T>(data);
		if (isEmpty()) {
			rear = newLink;
		}
		newLink.next = head; //新结点的next指向上一结点
		head = newLink;
	}
	
	public void insertLast(T data) {//在链尾 插入
		Link<T> newLink = new Link<T>(data);
		if (head == null) {
			rear = null; //如果不置空，那么当第一次清空后，再次插入时，rear是有引用的，那么下面的判断，不会赋值给head了
		}
		if (rear != null) {
			rear.next = newLink;
		} else {
			head = newLink;
			head.next = rear;
		}
		rear = newLink; //下次插入时，从rear处插入
		
	}
	
	public T  deleteHead() {//删除 链头
		if (isEmpty()) return null;
		Link<T> temp = head;
		head = head.next; //变更首结点，为下一结点
		if (head == null) {
			rear = head;
		} 
		return temp.data;
	}
	
	public T find(T t) {
		if (isEmpty()) {
			return null;
		}
		Link<T> find = head;
		while (find != null) {
			if (!find.data.equals(t)) {
				find = find.next;
			} else {
				break;
			}
		}
		if (find == null) {
			return null;
		}
		return find.data;
 	}
	
	public T delete(T t) {
		if (isEmpty()) {
			return null;
		} else {
			if (head.data.equals(t)) {
				Link<T> temp = head;
				head = head.next; //变更首结点，为下一结点
				return temp.data;
			}
		}
		Link<T> p = head;
		Link<T> q = head;
		while (!p.data.equals(t)) {
			if (p.next == null) {//表示到链尾还没找到
				return null;
			} else {
				q = p;
				p = p.next;
			}
		}
		q.next = p.next;
		return p.data;
	}
	
	public void displayList() {//遍历
		System.out.println("List (head-->last):");
		Link<T> current = head;
		while (current != null) {
			current.displayLink();
			current = current.next;
		}
	}
	
	public void displayListReverse() {//反序遍历
		if (isEmpty()) {
			return;
		}
		Link<T> p = head, q = head.next, t;
		while (q != null) {//指针反向，遍历的数据顺序向后
			t = q.next; //no3
			if (p == head) {// 当为原来的头时，头的.next应该置空
				p.next = null;
			}
			q.next = p;// no3 -> no1  pointer reverse
			p = q; //start is reverse
			q = t; //no3 start
		}
		//上面循环中的if里，把head.next 置空了, 而当q为null不执行循环时，p就为原来的最且一个数据项，反转后把p赋给head
		head = p; 
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
		TwoEndpointList<Integer> list = new TwoEndpointList<Integer>();
		list.insertLast(1);
		list.insertFirst(2);
		list.insertLast(3);
		list.insertFirst(4);
		list.insertLast(5);
		list.displayList();
		
		Integer deleteHead = list.deleteHead();
		System.out.println("deleteHead:" + deleteHead);
		list.displayList();
		
		System.out.println("find:" + list.find(6));
		System.out.println("find:" + list.find(3));

		System.out.println("delete find:" + list.delete(6));
		System.out.println("delete find:" + list.delete(5));
		list.displayList();
		System.out.println("----reverse----");
		list.displayListReverse();
	}
}
