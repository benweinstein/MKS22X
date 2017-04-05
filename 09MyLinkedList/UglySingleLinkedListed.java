public class UglySingleLinkedList{
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
	LNode newStart = new LNode(value, start);
	start = newStart;
	size++;
	return true;
    }

    //adds the value to the end of a linked list
    public boolean add(int value){
	//basics: change the 'next' of the ending node
	if(size == 0){
	    return addToStart(value);
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


    /*
      PHASE II
    */

    //returns the index of the first occurrence of the value in the linked list,
    //returns -1 if not found
    public int indexOf(int value){
	int counter = 0;
	LNode current = start;
	
	while(current.next != null){
	    if(current.value == value){
		return counter;
	    }
	    
	    current = current.next;
	    counter++;
	}

	//for the last case: 
	if(counter == size() - 1 && current.value == value){
	    return counter;
	}
	    
	return -1;
    }

    //insert a new element at the specified index, 0 at the front, size() at 
    //the end
    public void add(int index, int value){
	if(index < 0 || index > size()){
	    throw new IndexOutOfBoundsException();
	}

	//Using what has already been written: 
	if(index == 0){ //if you're adding to the start...
	    addToStart(value);
	    return;
	}

	if(index == size()){ //if you're adding to the end...
	    add(value);
	    return;
	}

	//if you're adding into the middle:
	LNode current = start;

	while(index > 1){
	    current = current.next;
	    index--;
	}
	
	LNode toBeAdded = new LNode(value, current.next);
	current.next = toBeAdded;

	size++;
    }

    //remove the element at the specified index, returns the value removed
    public int remove(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}
	
	int holder = 0;

	//removing 0th LNode:
	if(index == 0){
	    holder = start.value;
	    
	    start = start.next;

	    size--;
	    return holder;
	}

	//removing last LNode:
	if(index == size() - 1){
	    holder = getNthNode(index).value;
	    
	    LNode secondToLast = getNthNode(index - 1);
	    secondToLast.next = null;

	    size--;
	    return holder;
	}
	
	//removing from the middle (a.k.a. any other case):
	LNode indexthNode = getNthNode(index);
	LNode nodeBefore = getNthNode(index - 1);

	holder = indexthNode.value;

	nodeBefore.next = indexthNode.next;	
	      
	size--;
	return holder;
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

	System.out.println(l.indexOf(66));
	System.out.println(l.indexOf(234));
	System.out.println(l.indexOf(24123));

	l.add(3, 24);
	l.add(0, 55);
	System.out.println(l);
	l.add(2, 499);
	l.add(1, 99);
	l.add(6, 6);
	System.out.println(l);
	
	System.out.println(l.remove(0));
	System.out.println(l.remove(5));
	System.out.println(l);

	System.out.println(l.remove(5));
	System.out.println(l);

	MyLinkedList a = new MyLinkedList();
	
	a.add(0);
	System.out.println(a.remove(0));
	System.out.println(a);
	
	a.add(1);
	a.add(2);
	a.add(3);
	a.add(4);
	a.add(5);

	System.out.println(a);
    }
}
