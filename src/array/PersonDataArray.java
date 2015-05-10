package array;

public class PersonDataArray {
	
	private Person[] ary;
	private int num; //实际元素数目、位置
	private int max;
	
	public PersonDataArray(int max) {
		this.ary = new Person[max];
		this.max = max;
	}
	
	public Person find(String serarchName) {
		int j;
		for (j = 0; j < num; j++) {
			if (ary[j].getLastName().equals(serarchName)) {
				break;
			}
		}
		if (j != num) {
			return ary[j];
		} 
		return null;
	}
	
	public void insert(Person p) {
		if (num < max) {
			ary[num] = p;
			num++;
		}
	}
	
	public void insert(String lastName, String firstName, int age) {
		ary[num] = new Person(lastName, firstName, age);
		num++;
	}
	
	public boolean delete(String serarchName) {
		int j;
		for (j = 0; j < num; j++) {
			if (ary[j].getLastName().equals(serarchName)) {
				break;
			}
		}
		if (j != num) {//找到了需要删除的
			for (int k = j; k < num - 1; k++) {
				ary[k] = ary[k + 1];//向前进一位
			}
			ary[num - 1] = null;//最后一个元素置空
			num--;
			return true;
		}
		return false;
	}
	
	public void displayAll() {
		for (Person p : this.ary) {
			if (p != null) {
				p.displayPerson();
				System.out.println("--------");
			}
		}
	}
	
	public void insertAsc() {
		int len = ary.length;
		for (int i = 1; i < len ; i++) {
			Person key = ary[i];
			for (int j = i; j > 0; j--) {
				if (ary[j - 1].getLastName().compareTo(ary[i].getLastName()) > 0) {
					Person p = ary[j] ;
					ary[j] = ary[j -1];
					ary[j -1] = p;
				} else {
					break;
				}
			}
		}
	}
	
	public void insertAsc1() {
		int len = ary.length;
		for (int i = 1; i < len ; i++) {
			Person key = ary[i];
			int j = i;
			for (; j > 0; j--) {
				if (ary[j - 1].getLastName().compareTo(key.getLastName()) > 0) {
					ary[j] = ary[j -1];
				} else {
					break;
				}
			}
			ary[j] = key;
		}
	}
	
	public void binaryInsertAsc() {
		
	}
}
