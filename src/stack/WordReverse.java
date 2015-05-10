package stack;
//词 逆序
public class WordReverse {
	
	public static void main(String[] args) {
		reverse("株式会社");
	}
	
	static void reverse(String word) {
		if (word == null) return;
		StackSS<Character> stack = new StackSS<Character>();
		char[] charArray = word.toCharArray();
		int len = charArray.length;
		for (int i = 0; i <len; i++ ) {
			stack.push(charArray[i]);
		}
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println("反转后：" + sb.toString());
	}
}
