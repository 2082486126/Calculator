import java.util.Stack;

/**
 * @author 2082486126
 */
public class Main {

    private static String[] op = {"+", "-", "*", "/"};

    public static void main(String[] args) {
        String question = MakeFormula();
        System.out.println(question);
        String ret = Solve(question);
        System.out.println(ret);
    }

    public static String MakeFormula() {
        StringBuilder build = new StringBuilder();
        int count = (int) (Math.random() * 2) + 1;
        int start = 0;
        int number1 = (int) (Math.random() * 99) + 1;
        build.append(number1);
        while (start < count) {
            int operation = (int) (Math.random() * 3);
            int number2 = (int) (Math.random() * 99) + 1;
            build.append(op[operation]).append(number2);
            start++;
        }
        return build.toString();
    }

    public static String Solve(String formula) {
        if (formula.isEmpty()) {
            return "Invalid formula";
        }

        Stack<Integer> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        StringBuilder numBuilder = new StringBuilder();

        for (int i = 0; i < formula.length(); i++) {
            char ch = formula.charAt(i);

            if (Character.isDigit(ch)) {
                numBuilder.append(ch);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                if (numBuilder.length() > 0) {
                    int num = Integer.parseInt(numBuilder.toString());
                    numStack.push(num);
                    numBuilder.setLength(0);
                }

                while (!opStack.empty() && hasPrecedence(ch, opStack.peek())) {
                    char op = opStack.pop();
                    int num2 = numStack.pop();
                    int num1 = numStack.pop();
                    int result = applyOperator(num1, num2, op);
                    numStack.push(result);
                }

                opStack.push(ch);
            } else {
                // Invalid character found
                return "Invalid formula";
            }
        }

        if (numBuilder.length() > 0) {
            int num = Integer.parseInt(numBuilder.toString());
            numStack.push(num);
        }

        while (!opStack.empty()) {
            char op = opStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            int result = applyOperator(num1, num2, op);
            numStack.push(result);
        }

        return formula + "=" + numStack.pop();
    }

    private static boolean hasPrecedence(char op1, char op2) {
        return (op1 != '*' && op1 != '/') || (op2 != '+' && op2 != '-');
    }

    private static int applyOperator(int num1, int num2, char op) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }
}
