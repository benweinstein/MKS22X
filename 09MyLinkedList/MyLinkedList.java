public class MyLinkedList{
    
    //inner LNode class
    private class LNode{
        LNode next, prev;
	int value;
	
	//constructor:
	public LNode(int value){
	    this.value = value;
	}
	
	//toString()
	public String toString(){
	    return value + " ";
	}
    }

    //instance vars
    private LNode head, tail;
    private int size;

    //default constructor:
    public MyLinkedList(){
	//defaults to null for start, 0 for size
    }
    
    //returns the number of elements in the list
    public int size(){
	return size;
    }

    //super helpful helper method:
    //needs to prioritize from the back or from the start...
    private LNode getNthNode(int n){
	LNode nth = start;
	
	while(nth.next != null && n > 0){
	    //System.out.println(nth.value);
	    nth = nth.next;
	    n--;
	}
	
	return nth;
    }
    
    //helper for add(int index, int value)
    private void addAfter(LNode location, LNode toBeAdded){/*complete this*/  }
    
    //helper for remove(int index) 
    private int remove(LNode target){
	if(target.next == null && target.prev == null){
	}


	else{
	    //last case
	    target.prev.next = target.next;
	    target.next.prev = target.prev;
	}
	return target.value;
    }
    
    //toString()
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
    
    //adds the value to the end of the list
    public boolean add(int value){  /*complete this*/ }
    
    //returns the value of the element at the specified index (0 based)
    public int get(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	return getNthNode(index).value;
    }
    
    //changes the value of the element at the specified index to the new value
    //returns the old value
    public int set(int index, int value){/*complete this*/}
    
    //returns the index of the 1st occurrence of the value in the linked list,
    //returns -1 if specified value not found
    public int indexOf(int value){/*complete this*/}
    
    //removes the element at the specified index
    //returns the value removed
    public int remove(int index){
	return remove(getNthNode(index));
    }
    
    //adds the new value to the specified index 
    //NOTE: the new value is added before (!!!) the value previously occupying
    //the spot (e.g. add(0, 3) adds 3 to the start of the linked list)
    public void add(int index,int value){/*complete this*/}
    
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
