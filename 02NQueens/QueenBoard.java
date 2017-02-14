public class QueenBoard{
    private int[][] board;
    private int solutionCount;

    public QueenBoard(int size){
	board = new int[size][size];
	solutionCount = -1;
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
    public void solve(){
	solveH(0);
    }

    private boolean solveH(int col){
	//NO SOLUTION FOR 2x2, 3x3 BOARDS
	if(board.length == 2 || board.length == 3){
	    return true; //t or f? doesn't really matter
	}
	
	for(int row = 0; row < board.length; row++){
	    //base case for end of the board
	    if(col >= board.length){
		return true;
	    }
	    //other cases
	    if(isQueenable(row, col)){
		placeQueenHere(row, col);
		if(solveH(col + 1)){
		    return true;
		}
		else{
		    deleteQueenHere(row,col);
		}
	    }
	}
	return false;
    }

    //helpers for solveH()
    //private boolean isQueenable(int row, int col){}
    //have to check diagonals, row, and col
    private boolean isQueenable(int row, int col){
	return isSafeRow(row, col) && isSafeCol(row, col) && areSafeDiagonals(row, col);
    }
    
    //the helper's helpers:
    private boolean isSafeRow(int row, int col){
	for(int c = 0; c < board.length; c++){
	    if(c != col && board[row][c] == -1){
		return false;
	    }
	}
	return true;
    }
    
    private boolean isSafeCol(int row, int col){
	for(int r = 0; r < board.length; r++){
	    if(r != row && board[r][col] == -1){
		return false;
	    }
	}
	return true;
    }
    
    private boolean areSafeDiagonals(int row, int col){
	int r1 = row;
	int r2 = row;
	int r3 = row;
	int r4 = row;
	int c1 = col;
	int c2 = col;
	int c3 = col;
	int c4 = col;

	//down to the right
	while(r1 < board.length && c1 < board.length){
	    if(board[r1][c1] == -1){
		return false;
	    }
	    r1++;
	    c1++;
	}

	//up to the left
	while(r2 >= 0 && c2 >= 0){
	    if(board[r2][c2] == -1){
		return false;
	    }
	    r2--;
	    c2--;
	}

	//down to the left
	while(r3 < board.length && c3 >= 0){
	    if(board[r3][c3] == -1){
		return false;
	    }
	    r3++;
	    c3--;
	}

	//up to the right
	while(r4 >= 0 && c4 > board.length){
	    if(board[r4][c4] == -1){
		return false;
	    }
	    r4--;
	    c4++;
	}
	
	//if it all works...
	return true;
    }	
    
    //private void placeQueenHere(int row, int col){}
    private void placeQueenHere(int row, int col){
	board[row][col] = -1;
    }

    //private void deleteQueenHere(int row, int col){}
    private void deleteQueenHere(int row, int col){
	board[row][col] = 0;
    }

    /**
     *precondition: board is filled with 0's only.
     *operates similar to solve(), but counts total number of solutions.
     *updates solutionCount instance variable accordingly. 
     *Uses countH
     */
    public void countSolutions(){
	//erase board at the beginning
	//I know Mr. K doesn't need this, but it makes testing easier for me
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board.length; c++){
		board[r][c] = 0;
	    }
	}
	
	solutionCount = 0; //necessary if called multiple times on same board
	
	countH(0);

	//should I erase the board at the end, too? Yup
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board.length; c++){
		board[r][c] = 0;
	    }
	}
    }
    
    private boolean countH(int col){
	//so 2x2, 3x3 boards have (technically) 0 solutions
	if(board.length == 2 || board.length == 3){
	    return false;
	}

	//meat of the problem
	for(int row = 0; row < board.length; row++){
	    //base case for end of the board
	    //NEEDS TO ACCOUNT FOR CHECKING AMOUNT OF QUEENS??? idts anymore
	    if(col >= board.length){
		return true;
	    }
	    //other cases
	    if(isQueenable(row, col)){
		placeQueenHere(row, col);
		if(countH(col + 1)){
		    solutionCount++;
		    deleteQueenHere(row, col);
		}
		else{
		    deleteQueenHere(row, col);
		}
	    }
	}
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

	return solutionCount;
    }

    /**toString
     *all numbers that represent queens are replaced with 'Q'
     *all others are replaced with underscores ('_')
     */
    public String toString(){
	String ans = "";
	for(int row = 0; row < board.length; row++){
	    for(int col = 0; col < board.length; col++){
		if(board[row][col] == -1){
		    ans += "Q ";
		}
		else{
		    ans += "_ ";
		}
		if(col == board.length - 1){
		    ans += "\n";
		}
	    }
	}
	return ans;	    
    }

    //TESTS
    public static void main(String[] args){
	QueenBoard n = new QueenBoard(5);
	System.out.println(n);
	n.solve();
	System.out.println(n);
	n.countSolutions();
	System.out.println(n.getSolutionCount());
    }
}
