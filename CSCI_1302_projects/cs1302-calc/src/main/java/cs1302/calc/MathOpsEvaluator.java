package cs1302.calc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Contains static methods for working with implementations of the
 * <code>MathOps</code> interface. It also contains methods for
 * working reverse polish notation.
 */
public class MathOpsEvaluator {

    // map for storing the precedence levels of each operator
    private static final Map<String, Integer> precedenceMap;

    static {
	Map<String, Integer> pMap = new HashMap<String, Integer>();
	pMap.put("<", 0);
	pMap.put(">", 0);
	pMap.put("+", 1);
        pMap.put("-", 1);
        pMap.put("*", 2);
        pMap.put("/", 2);
	pMap.put("^", 3);
	pMap.put("!", 4);
	precedenceMap = Collections.unmodifiableMap(pMap);
    } // static

    /** 
     * Converts an expression expressed in infix notation as an array of Strings
     * to the appropriate expression expressed in postfix notationan as an array 
     * of Strings.
     *
     * <p>
     * Here is an example of an expression in infix notation:
     * <code>4 ! + 2 / 3 - 7 * 2 ^ 3</code>
     * Each element of this expression would be an element in the array that is
     * passed into this method.
     *
     * <p>
     * The resulting postfix expression is:
     * <code>4 ! 2 3 / + 7 2 3 ^ * -</code>
     * Each element of this expression would be an element in the array that is
     * returned by this method.
     *
     * @param infix an array containing an infix expression
     * @return an array containing the postfix expression
     */
    private static String[] infixToPostfix(String[] infix) {

	// a list for the resulting postfix expression
	List<String> postfix = new ArrayList<String>();

	// a stack for implementing the conversion
	Stack<String> operatorStack = new Stack<String>();

	// check the length of the expression
        if (infix.length != 0) {
                
	    for (int i = 0; i < infix.length; i++) {
                        
		// precedence is null for operands
		Integer precedence = precedenceMap.get(infix[i]);

		if (precedence != null) {

		    // then the current token is an operator
		    while (!operatorStack.isEmpty()) {
			String opFromStack = operatorStack.pop();
			if (precedenceMap.get(opFromStack) < precedence) {
			    operatorStack.push(opFromStack);
			    break;
			} else {
			    postfix.add(opFromStack);
			} // if
		    } // while
                                
		    operatorStack.push(infix[i]);
                                
		} else { 
		    // current token is not an operator
		    postfix.add(infix[i]);
		} // if
                        
	    } // for
                
	    // add the remaining operators to the postfix expression
	    while (!operatorStack.isEmpty()) {
		postfix.add(operatorStack.pop());
	    } // while
                
        } // if
        
        return postfix.toArray(new String[postfix.size()]);

    } // infixToPostfix

    /**
     * Evaluates a mathematical expression expressed in infix notation. The
     * expression must be trimmed and contain only a single whitespace between
     * each operator and operand.
     *
     * @param impl an instance of a Math implementation
     * @param expression the mathematical expression in infix notation
     * @return the result of evaluating the expression
     * @throws StackOverflowError if a stack overflow occurs
     * @throws ArithmeticException if an arithmetic exception occurs
     * @throws NumberFormatException if a number format exception occurs
     */
    public static int evaluate(MathOps impl, String expression) throws StackOverflowError, ArithmeticException, NumberFormatException {

	expression = expression.replaceAll("<<", "<")
	    .replaceAll(">>", ">")
	    .replaceAll("\\^\\^", "^");
	

	String[] infix   = expression.split(" ");
	String[] postfix = infixToPostfix(infix);
	
	// a stack for implementing the evaluation
	Stack<Integer> stack = new Stack<Integer>();

	for (int i = 0; i < postfix.length; i++) {

	    if (postfix[i].length() == 1 && !Character.isDigit(postfix[i].charAt(0))) {

		// if the first character of the element is not a digit then we 
		// assume it is an operator

		String operator = postfix[i];
		
		if (operator.equals("<")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.lshift(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals(">")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.rshift(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("+")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.add(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("-")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.sub(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("*")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.mul(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("/")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.div(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("^")) {
		    int rhs = stack.pop();
		    int lhs = stack.pop();
		    int result = impl.pow(lhs, rhs);
		    stack.add(result);
		} else if (operator.equals("!")) {
		    int num = stack.pop();
		    int result = impl.fac(num);
		    stack.add(result);
		} // if

	    } else {

		// otherwise we assume it is an operand and add it to the stack
		stack.add(Integer.parseInt(postfix[i]));

	    } // if

	} // for

	// the only element left in the stack will be the result of the evaluation
	return stack.pop();

    } // evaluate

} // MathOpsEvaluator

