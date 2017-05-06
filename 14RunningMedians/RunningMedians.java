public class RunningMedians{
    private IntegerHeap lt; //max heap of numbers <= median
    private IntegerHeap gt; //min heap of numbers >= median

    public RunningMedians(){
	lt = new IntegerHeap(); //max heap
	gt = new IntegerHeap(false); //min heap
    }

    
    public double getMedian(){ //int or double...? 
	if(lt.size() == gt.size()){
	    return (lt.peek() + gt.peek()) / 2.0;
	}
	else if(lt.size() > gt.size()){
	    return lt.peek() + 0.0;
	}
	else{
	    return gt.peek() + 0.0;
	}
    }
    
    public void add(Integer n){
	/*
	  EXCEPTION CASES
	*/
	
	//if completely empty:
	if(gt.size() == 0 && lt.size() == 0){
	    gt.add(n);
	    return;
	}
	
	//if just one side is empty:
	if(lt.size() == 0 && gt.peek().compareTo(n) < 0){
	    lt.add(gt.remove());
	    gt.add(n);
	    return;
	}
	
	//this one will most likely never fire
	//should only fire if Mr. K wants us to implement a remove() function
	//for the RunningMedians class:
	if(gt.size() == 0 && lt.peek().compareTo(n) > 0){
	    gt.add(lt.remove());
	    lt.add(n);
	    return;
	}

	/*
	  STANDARD CASES
	*/
	
	if(n > getMedian()){
	    gt.add(n);

	    //check to make sure the sizes are right:
	    if(gt.size() - lt.size() > 1){
		lt.add(gt.remove());
	    }
	}
	else{
	    lt.add(n);

	    //check to make sure the sizes are right:
	    if(lt.size() - gt.size() > 1){
		gt.add(lt.remove());
	    }
	}
    }

    //very temp (really, just so it's more convenient for me to print out
    //while I'm running tests...):
    public String toString(){
	return getMedian() + "";
    }
    
    public static void main(String[] args){
	RunningMedians m = new RunningMedians();
	m.add(4);
	System.out.println(m);
	m.add(12);
	

    }
}

	
