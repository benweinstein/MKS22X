public class KnightBoard{
    private int[][] board;

    // private static int[] movesRow;
    // private static int[] movesCol;

    public KnightBoard(int startingRows, int startingCols){
	board = new int[startingRows][startingCols];
    }

    public void solve(){
	solveH(0, 0, 1);
    }

    private boolean solveH(int row, int col, int numKnight){
	//base case
	if(numKnight > board.length * board[0].length){
	    return true;
	}
	
	if(!isValidSpot(row, col)){
	    return false;
	}
	
	placeKnightHere(row, col, numKnight); //place one
	
	//generate where the next knight could be placed 
	int[] movesRow = generateRows(row);
	int[] movesCol = generateCols(col);
	
	//try out these spots
	if(!(solveH(movesRow[0], movesCol[0], numKnight + 1) ||
	     solveH(movesRow[1], movesCol[1], numKnight + 1) ||
	     solveH(movesRow[2], movesCol[2], numKnight + 1) ||
	     solveH(movesRow[3], movesCol[3], numKnight + 1) ||
	     solveH(movesRow[4], movesCol[4], numKnight + 1) ||
	     solveH(movesRow[5], movesCol[5], numKnight + 1) ||
	     solveH(movesRow[6], movesCol[6], numKnight + 1) ||
	     solveH(movesRow[7], movesCol[7], numKnight + 1))){
	    deleteKnightHere(row, col);
	    return false;
	}
	return true;	
    }
    
    //HELPERS for the helper
    private boolean isValidSpot(int row, int col){
	try{
	    return (board[row][col] == 0);
	}catch(ArrayIndexOutOfBoundsException e){
	    return false;
	}
    }

    private int[] generateRows(int row){
	int[] ans = {row - 2, row - 2, row - 1, row - 1,
		     row + 1, row + 1, row + 2, row + 2};
	return ans;
    }
    
    private int[] generateCols(int col){
	int[] ans = {col - 1, col + 1, col - 2, col + 2,
		     col - 2, col + 2, col - 1, col + 1};
	return ans;
    }

    private void placeKnightHere(int row, int col, int numKnight){
	board[row][col] = numKnight;
    }

    private void deleteKnightHere(int row, int col){
	board[row][col] = 0;
    }

    public String toString(){
	String ans = "";
	for(int r = 0; r < board.length; r++){
	    for(int c = 0; c < board[r].length; c++){
		if(board[r][c] == 0){
		    ans += "__ ";
		}
		else if(board[r][c] < 10){
		    ans += "_" + board[r][c] + " ";
		}
		else{
		    ans += board[r][c] + " ";
		}

		//end of the row
		if(c == board[r].length - 1){
		    ans += "\n";
		}
	    }
	}	
	return ans;
    }

    //TESTS
    public static void main(String[] args){
	KnightBoard b = new KnightBoard(5, 5);
	System.out.println(b);
	System.out.println(b.solveH(0,0,1));
	System.out.println(b);
    }
}
