package linked_list;


public class Test {
	
	//单链表
	Link head;
	class Link<T> {
		T data;
		Link next;
	}
	//双端链表
	Link1 head1;
	Link1 rear;
	class Link1<T> {
		T data;
		Link1 next;
	}
	//双向链表，可以是双端链表，非必须
	Link2 hear2;
	class Link2<T> {
		T data;
		Link2 previous;
		Link2 next;
	}
}
