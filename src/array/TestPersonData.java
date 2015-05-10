package array;

public class TestPersonData {
	
	public static void main(String[] args) {
		PersonDataArray pda = new PersonDataArray(5);
//		pda.insert(new Person("a", "b1", 18));
//		pda.insert(new Person("b", "b2", 18));
//		pda.insert(new Person("c", "b3", 18));
//		pda.insert(new Person("d", "b4", 18));
//		pda.insert(new Person("e", "b5", 18));
//		pda.delete("a");
//		pda.delete("d");
//		pda.displayAll();
		
		pda.insert(new Person("a", "" , 17));
		pda.insert(new Person("c", "" , 17));
		pda.insert(new Person("e", "" , 17));
		pda.insert(new Person("b", "" , 17));
		pda.insert(new Person("d", "" , 17));
		pda.insertAsc1();
		pda.displayAll();
	}
}
