package array;

public class Person {
	
	private String lastName; // 姓
	private String firstName;// 名
	private int age;

	public Person(String lastName, String firstName, int age) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
	}
	
	public void displayPerson() {
		System.out.println("lastName:" + this.lastName);
		System.out.println("firstName:" + this.firstName);
		System.out.println("age:" + this.age);
	}
	
	public String getLastName() {
		return lastName;
	}
}
