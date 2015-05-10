package stack;

/**
 * a{b(c[d]e)f},读取到左分隔符，入栈；读取到右分隔符，取栈顶比较
 * @author stone
 *
 */
public class BracketSeparator {
	public static void main(String[] args) {
		String s = "a{b(c[d]e)f}";
		check(s);
	}
	
	static void check(String str) {
		StackSS<Character> stack = new StackSS<Character>();
		char[] ary = str.toCharArray();
		int len = ary.length;
		for (int i = 0; i < len; i++) {
			
			switch (ary[i]) {
			case '{':
			case '(':
			case '[':
				stack.push(ary[i]);
				break;
			case '}':
			case ')':
			case ']':
				if (stack.isEmpty()) {
					System.out.println("error: " + ary[i] + " at " + i);
				} else {
					char ch = stack.pop();
					if ((ch == '{' && ary[i] != '}')
							|| (ch == '(' && ary[i] != ')')
							|| (ch == '[' && ary[i] != ']')) {
						System.out.println("error: " + ary[i] + " at " + i);
					} else {
						System.out.println(ary[i]);
					}
				}
				
				break;
			default:
				System.out.println(ary[i]);
				break;
			}
		}
	}
}
