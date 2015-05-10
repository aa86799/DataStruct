package linked_list;



/**
 * 链表 迭代器
 * @author stone
 *
 */
public class ListIterator<T> {
	
	private Link<T> current;
	private Link<T> previous;
	private LinkList<T> ourList;
	
	public ListIterator(LinkList<T> list) {
		this.ourList = list;
		reset();
	}
	
	public void reset() {//start at first
		current = ourList.getFirst();
		previous = null;
	}
	
	public boolean atEnd() {
		return current.next == null;
	}
	
	public T next() {//返回的是下一个值了
		previous = current;
		current = current.next;
		if (current == null) return null;
		return current.data;
	}
	
	public Link getCurrent() {
		return current;
	}
	
	public void insertAfter(T data) {//after current link
		Link<T> newLink = new Link(data);
		if (ourList.isEmpty()) {
			ourList.setFirst(newLink);
			current = newLink;
		} else {
			newLink.next = current.next;//新结点的next等于原本的next
			current.next = newLink;
			next();
		}
	}
	
	public void insertBefore(T data) {//before current link
		Link<T> newLink = new Link(data);
		if (previous == null) {//前结点为空，那么current要么为空，要么不为空
			newLink.next = ourList.getFirst();
			ourList.setFirst(newLink);
			reset();
		} else {
			newLink.next = current;//=previous.next
			previous.next = newLink; 
			current = newLink;
		}
	}
	
	public T deleteCurrent() {
		if (current == null) return null;
		T data = current.data;
		if (previous == null) {//表示迭代下标在第1个位置
			ourList.setFirst(current.next);
			reset(); 
		} else {
			previous.next = current.next;
			if (atEnd()) {
				reset();
			} else {
				current = current.next;
			}
		}
		return data;
	}
	
	public boolean hasNext() {
		if (current == null) return current == null;
		return current.next != null;
	}
	
	
	static class Link<T> {//链结点
		T data;		//数据域
		Link<T> next; //后继指针，结点			链域
		Link(T data) {
			this.data = data;
		}
		void displayLink() {
			System.out.println("the data is " + data.toString());
		}
	}
	
	static class LinkList<T> {
		private Link<T> first;
		
		public LinkList() {
			
		}

		public Link<T> getFirst() {
			return first;
		}

		public void setFirst(Link<T> first) {
			this.first = first;
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public ListIterator<T> iterator() {
			return new ListIterator<T>(this);
		}
		
		public void displayList() {
			Link cur = first;
			while (cur != null) {
				cur.displayLink();
				cur = cur.next;
			}
			System.out.println("-----------");
		}
	}
	
	public static void main(String[] args) {
		LinkList<Integer> list = new LinkList<Integer>();
		ListIterator<Integer> iterator = list.iterator();
		iterator.insertAfter(1);
		iterator.insertAfter(3);
		iterator.insertBefore(5);
		iterator.insertBefore(7);
		iterator.ourList.displayList();
		iterator.deleteCurrent();
		iterator.ourList.displayList();
		
		iterator.reset();
		System.out.println("iterator--data--is--" + iterator.getCurrent().data);
		while (iterator.hasNext()) {
			int data = iterator.next();//略过了第1个结点
			System.out.println("iterator--data--is--" + data);
		}
	}
	
}
