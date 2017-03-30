public class MyLinkedList{
    //instance vars
    private LNode start;
    private int size;

    //default constructor:
    public MyLinkedList(){
	//defaults to null for start, 0 for size
    }

    /*
      PHASE I
    */
    
    
    //adds the value to the start of a linked list	
    private boolean addToStart(int value){
	LNode newStart = new LNode(value);
	start = newStart;
	size++;
	return true;
    }

    //adds the value to the end of a linked list
    public boolean add(int value){
	//basics: change the 'next' of the ending node
	if(size == 0){
	    addToStart(value);
	}
	else{
	    LNode oldEnd = getNthNode(size - 1);
	    oldEnd.next = new LNode(value); 
	}
	
	size++;
	return true;
    }

    //return the number of elements in the list
    public int size(){
	return size;
    }
    
    //return the value of the element at the specified index (0 based)
    public int get(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	return getNthNode(index).value;
    }

    //change the value of the element at the specified index to the newValue,
    //return the old value
    public int set(int index, int newValue){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	LNode beingReplaced = getNthNode(index);
	int holder = beingReplaced.value;

	beingReplaced.value = newValue;
	    
	return holder;
    }

    //helper method, super useful
    private LNode getNthNode(int n){
	LNode nth = start;
	
	while(nth.next != null && n > 0){
	    //System.out.println(nth.value);
	    nth = nth.next;
	    n--;
	}

	return nth;
    }

    //toString
    public String toString(){
	String ans = "[";
	LNode current = start;

	while(current != null){
	    if(current.next == null){
		ans += " " + current.value;
	    }
	    else{
		ans += " " + current.value + ",";
	    }

	    current = current.next;
	}

	ans += "]";

	return ans;
    }

    //inner LNode class
    private class LNode{
	private int value;
	private LNode next;

	//constructors:

	//your average node
	public LNode(int val, LNode nex){
	    value = val;
	    next = nex;
	}
	
	//so you can create an end node
	public LNode(int val){
	    value = val;
	    next = null;
	}
    }

    //TESTS
    public static void main(String[] args){
	MyLinkedList l = new MyLinkedList();
	l.add(34);
	System.out.println(l);
	l.add(3);
	l.add(5);
	System.out.println(l);
	//System.out.println(l.get(5));
	System.out.println(l.set(2, 234)); //5
	System.out.println(l.set(0, 66)); //34
	System.out.println(l); //[ 66, 3, 234]

    }
}
