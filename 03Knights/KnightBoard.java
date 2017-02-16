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
	if(numKnight > row * col){
	    return true;
	}
	if(board[row][col] == 0){ //if there's no knight here...
	    placeKnightHere(row, col, numKnight); //place one
	  
	    //generate where the next knight could be placed 
	    int[] movesRow = generateRows(row);
	    int[] movesCol = generateCols(col);
	    
	    //try out these spots
	    for(int i = 0; i < 8; i++){
		if(isValidSpot(movesRow[i], movesCol[i])){//if one of them works
		    //move on to the next recursive call
		    return solveH(movesRow[i], movesCol[i], numKnight + 1);
		}
	    }
	    
	    //none of them worked, so delete this knight,
	    //so end this recursive call
	    deleteKnightHere(row, col);
	}
	return false;
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
