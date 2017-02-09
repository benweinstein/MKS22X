public class QueenBoard{
    private int[][] board;
    private int solutionCount;

    public QueenBoard(int size){
	board = new int[size][size];
    }

    /**
     *precondition: board is filled with 0's only.
     *@return false when the board is not solveable. True otherwise.
     *postcondition:
     *if false: board is still filled with 0's.
     *if true: board is filled with the final configuration of the board
     *after having added all n queens. 
     *Uses solveH
     */
    public boolean solve(){
	return solveH(0);
    }

    private boolean solveH(int col){
	return false;
    }

    /**
     *precondition: board is filled with 0's only.
     *operates similar to solve(), but counts total number of solutions.
     *updates solutionCount instance variable accordingly. 
     *Uses countH
     */
    public boolean countSolutions(){
	return countH();
    }
    
    private boolean countH(){
	return false;
    }
    
    /**
     *@return the number of solutions found, or -1 if the board was never solved.
     *The board should be reset after this is run.
     */
    public int getSolutionCount(){
	//blank the board
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board.length; c++){
		board[r][c] = 0;
	    }
	}
	
	//return stuff
	if(solutionCount == 0){
	    return -1;
	}
	return solutionCount;
    }

    /**toString
     *all numbers that represent queens are replaced with 'Q'
     *all others are replaced with underscores ('_')
     */
    public String toString(){
	return "";
    }

    //TESTS
    public static void main(String[] args){
	System.out.println("Hi");
    }
}
