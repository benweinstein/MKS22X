public class RunningMedian{
    private MyHeap lt; //max heap of numbers <= median
    private MyHeap gt; //min heap of numbers >= median

    public RunningMedian(){
	lt = new MyHeap(); //max heap
	gt = new MyHeap(false); //min heap
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
	}
	
	//if just one side is empty:
	else if(lt.size() == 0 && gt.peek().compareTo(n) < 0){
	    lt.add(gt.remove());
	    gt.add(n);
	}
	
	//this one will most likely never fire
	//should only fire if Mr. K wants us to implement a remove() function
	//for the RunningMedians class:
	else if(gt.size() == 0 && lt.peek().compareTo(n) > 0){
	    gt.add(lt.remove());
	    lt.add(n);
	}

	/*
	  STANDARD CASES
	*/
	
	else if(n > getMedian()){
	    gt.add(n);

	    //check to make sure the sizes are right:
	    if(gt.size() - lt.size() > 1){
		lt.add(gt.remove());
	    }
	}
	else{
	    lt.add(n);
	    System.out.println(this.lt);

	    //check to make sure the sizes are right:
	    if(lt.size() - gt.size() > 1){
		gt.add(lt.remove());
	    }
	}
	
	//for testing purposes:
	//System.out.println("Less than: " + this.lt);
	//System.out.println("Greater than: " + this.gt);
    }

    //very temp (really, just so it's more convenient for me to print out
    //while I'm running tests...):
    public String toString(){
	return getMedian() + "";
    }
    
    public static void main(String[] args){
	RunningMedian m = new RunningMedian();
	m.add(4);
	System.out.println(m); //4.0
	m.add(12);
	System.out.println(m); //8.0
	m.add(1);
	System.out.println(m); //4.0
	m.add(3);
	System.out.println(m); //3.5
	m.add(15);
	System.out.println(m); //4.0
    }
}

	
