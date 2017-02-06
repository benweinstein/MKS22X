public class Recursion{
    //required name function
    public static String name(){
	return "Weinstein,Ben";
    }
    
    //required final function
    public static double sqrt(double n){
	if(n < 0){
	    throw new IllegalArgumentException();
	}
	return guessRoot(n, 1);
    }

    //Helper functions

    //in the place of "==" for floating point numbers
    private static boolean isCloseEnough(double exact, double approx){
        //guidelines: nine to twelve decimal placeness of "sameness" required
	return (Math.abs(exact - approx) / exact * 100) <= 0.0000000001;
    }

    //to be wrapped by sqrt function, called with guess == 1
    private static double guessRoot(double n, double guess){
	if(isCloseEnough(n, guess * guess)){
	    return guess;
	}
	double betterGuess = (n / guess + guess) / 2;
	return guessRoot(n, betterGuess);
    }
    
}
	
