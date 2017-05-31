import java.util.Stack;

public class StackCalc{
     public static double eval(String s){
	String[] tokens = s.split(" ");
	Stack<String> values = new Stack<String>();

	for(String token : tokens){
	    if(isOperator(token)){
		values.push(apply(token, values.pop(), values.pop()));
	    }
	    else{
		values.push(token);
	    }
	}
	
	return Double.parseDouble(values.pop());
    }

    private static String apply(String operation, String a1, String b1){
	double a = Double.parseDouble(a1);
	double b = Double.parseDouble(b1);
	char op = operation.charAt(0);

	if(op == '+'){
	    return a + b + "";
	}
	if(op == '-'){
	    return b - a + ""; //order switches b/c pop() happens in reverse!!!
	}
	if(op == '*'){
	    return a * b + "";
	}
	if(op == '/'){
	    return b / a + "";
	}
	//last case is mod:
	return b % a + "";
    }

    private static boolean isOperator(String s){
	//THIS IS THE MISTAKE!!! NEEDS .equals() 
	return (s.equals("+") ||
		s.equals("-") ||
		s.equals("*") ||
		s.equals("/") ||
		s.equals("%"));
    }

    public static void main(String[] args){
	System.out.println(eval("5 2 + 3 * 8 -")); //13
	System.out.println(eval("0 1 2 3 * + +")); //7
	
    }
}
