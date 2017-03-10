import java.util.*;

public class Quiz2Redux{
    /*Returns an ArrayList<String> that contains all subsets of the
     *characters of String s. Assume s has no duplicate characters.
     *the characters should appear in the same order that they occur 
     *in the original string.
     */
    public static ArrayList<String> combinations(String s){
	ArrayList<String>words = new ArrayList<String>();
	help(words, s, "");
	Collections.sort(words); //so it's all sorted! he just checks equality with his own
	return words;
    }
    
    private static void help(ArrayList<String> words, String s, String partial){
	//base case
	if(s.length() == 0){
	    words.add(partial);
	    return;
	}        

	//recursive call
	help(words, s.substring(1), partial + s.substring(0, 1));
	help(words, s.substring(1), partial);	
    }

    //TESTS
    public static void main(String[] args){
	System.out.println(combinations("abc"));
	
	//longest word without any duplicate letters:
	//System.out.println(combinations("uncopyrightable"));
	//it's pretty ugly to print out because it's 2^15 combinations... but it works!!!
    }
}
