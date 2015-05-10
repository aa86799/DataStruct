package linked_list;

/**
 * 单链表:尾插法 	后进先出
 * 若将链表的左端固定，链表不断向右延伸，这种建立链表的方法称为尾插法。
 * 尾插法建立链表时，头指针固定不动，故必须设立一个尾部的指针，向链表右边延伸，
 * 尾插法最先得到的是头结点。
 * @author stone
 */
public class SingleLinkedList2<T> {
	
	private Link<T> head;		//首结点
	public SingleLinkedList2() {
		
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void insertLast(T data) {//在链尾 插入
		Link<T> newLink = new Link<T>(data);
		if (head != null) {
			Link<T> nextP = head.next;
			if (nextP == null) {
				head.next = newLink;
			} else {
				Link<T> rear = null;
				while (nextP != null) {
					rear = nextP;
					nextP = nextP.next;
				}
				rear.next = newLink;
			}
		} else {
			head = newLink;
		}
	}
	
	public Link<T>  deleteLast() {//删除 链尾
		Link<T> p = head;
		Link<T> q = head;
		while (p.next != null) {// p的下一个结点不为空，q等于当前的p(即q是上一个，p是下一个) 循环结束时，q等于链尾倒数第二个
			q = p;
			p = p.next;
		}
		//delete
		q.next = null;
		return p;
	}
	
	public Link<T> find(T t) {
		Link<T> find = head;
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
			if (head.data.equals(t)) {
				Link<T> temp = head;
				head = head.next; //变更首结点，为下一结点
				return temp;
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
		return p;
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
		SingleLinkedList2<Integer> list = new SingleLinkedList2<Integer>();
		list.insertLast(33);
		list.insertLast(78);
		list.insertLast(24);
		list.insertLast(22);
		list.insertLast(56);
		list.displayList();
		
		list.deleteLast();
		list.displayList();
		
		System.out.println("find:" + list.find(56));
		System.out.println("find:" + list.find(33));
		
		System.out.println("delete find:" + list.delete(99));
		System.out.println("delete find:" + list.delete(78));
		list.displayList();
		System.out.println("----reverse----");
		list.displayListReverse();
	}
}
