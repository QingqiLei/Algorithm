package google;

import java.util.Stack;

/**
 * basic calculator
 * give a expression of addition, subtraction, parentheses
 * <p>
 * It is easy for addition and subtraction,
 * change the sign and modify the res.
 * <p>
 * For parentheses, it needs stack to store the res
 */
public class lc224 {


    public int calculate(String s) {
        int res = 0, sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+') sign = 1;
            else if (c == '-') sign = -1;
            else if (Character.isDigit(c)) {
                int num = c - '0';
                int j = i + 1;
                for (; j < s.length(); j++) {
                    char c1 = s.charAt(j);
                    if (Character.isDigit(c1)) num = num * 10 + (c1 - '0');
                    else break;
                }
                i = j - 1;
                res += sign * num;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else if (c == ')') {
                res = res * stack.pop() + stack.pop();
            }
        }
        return res;
    }
}
