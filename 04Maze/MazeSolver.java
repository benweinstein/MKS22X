import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeSolver{
    private char[][] maze;
    //methods...


    //main
    public static void main(String[] args) throws FileNotFoundException{
	File f = new File("Maze1.txt");
	Scanner inf = new Scanner(f);
	while(inf.hasNextLine()){
	    String line = inf.nextLine();
	    System.out.println(line);
	}
    }
}
    
