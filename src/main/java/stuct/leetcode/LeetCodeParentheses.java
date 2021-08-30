package stuct.leetcode;

import java.util.Stack;

/*
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 *      给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

        有效字符串需满足：

        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串

    解：以 stack 来进行处理。左边括号入栈；遇到右边，要从栈顶弹出，比较是否匹配；最后匹配结束后，要求栈内为空
 */
class LeetCodeParentheses {

    public boolean isValid(String s) {
        if (s == null)
            return false;

        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char topChar = stack.pop();
                switch (topChar) {
                    case '(':
                        if (c != ')')
                            return false;
                        break;
                    case '[':
                        if (c != ']')
                            return false;
                        break;
                    case '{':
                        if (c != '}')
                            return false;
                        break;
                }
            }
        }
        return stack.isEmpty();  //没有闭合，多出了左边的括号 可能有 n 个
    }

    public static void main(String[] args) {
        LeetCodeParentheses solution = new LeetCodeParentheses();
        System.out.println(solution.isValid(""));
        System.out.println(solution.isValid("()"));
        System.out.println(solution.isValid("(("));
        System.out.println(solution.isValid("[(])"));
        System.out.println(solution.isValid("{[]}"));
        System.out.println(solution.isValid("{[](})"));
        System.out.println(solution.isValid("({[]}()"));
        System.out.println(solution.isValid("[({[]})]"));
    }
}