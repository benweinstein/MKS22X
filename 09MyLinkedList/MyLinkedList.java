public class MyLinkedList{
    
    //inner LNode class
    private class LNode{
        LNode next, prev;
	int value;
	
	//constructor:
	public LNode(int value){
	    this.value = value;
	}

	public LNode(int value, LNode next){
	    this.value = value;
	    this.next = next;
	}
	
	//toString()
	public String toString(){
	    return value + " ";
	}
    }

    //instance vars
    private LNode start;
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
    private void addAfter(LNode location, LNode toBeAdded){
	toBeAdded.prev = location;
	toBeAdded.next = location.next;
	location.next = toBeAdded;
	toBeAdded.next.prev = toBeAdded;
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
    public boolean add(int value){
	//if list is empty:
	if(size() == 0){
	    start = new LNode(value);
	}
	else{ //other possibility:
	    LNode oldEnd = getNthNode(size() - 1);
	    LNode newEnd = new LNode(value);
	    
	    oldEnd.next = newEnd;
	    newEnd.prev = oldEnd;
	}
	
	size++;
	
	return true;
    }
    
    //returns the value of the element at the specified index (0 based)
    public int get(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	return getNthNode(index).value;
    }
    
    //changes the value of the element at the specified index to the new value
    //returns the old value
    public int set(int index, int value){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	LNode beingReplaced = getNthNode(index);
	int holder = beingReplaced.value;

	beingReplaced.value = value;

	return holder;
    }
    
    //returns the index of the 1st occurrence of the value in the linked list,
    //returns -1 if specified value not found
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

	//otherwise...
	return -1;
    }
    
    //removes the element at the specified index
    //returns the value removed
    public int remove(int index){
	if(index < 0 || index >= size()){
	    throw new IndexOutOfBoundsException();
	}

	int holder = 0;

	//removing only element of the list:
	if(size() == 1){
	    holder = start.value;

	    start = null;	    

	    size--;
	    return holder;
	}

	//removing start node:
	if(index == 0){
	    holder = start.value;

	    start = start.next;
	    start.prev = null;

	    size--;
	    return holder;
	}

	//removing end node:
	if(index == size() - 1){
	    LNode end = getNthNode(size() - 1);
	    holder = end.value;
	    
	    end.prev.next = null;

	    size--;
	    return holder;
	}

	//any other case:
	LNode x = getNthNode(index);
	holder = x.value;

	x.prev.next = x.next;
	x.next.prev = x.prev;

	size--;
	return holder;
    }
    
    //adds the new value to the specified index 
    //NOTE: the new value is added before (!!!) the value previously occupying
    //the spot (e.g. add(0, 3) adds 3 to the start of the linked list)
    public void add(int index,int value){
	if(index < 0 || index > size()){
	    throw new IndexOutOfBoundsException();
	}
	
	//cases: add to start, add to end, add to middle, add to empty

	//handling end and empty cases:
	if(size() == 0 || index == size()){
	    add(value);
	    return;
	}

	//handling start case:
	if(index == 0){
	    LNode newStart = new LNode(value, start);
	    start = newStart;
	    start.next.prev = start;
	    
	    size++;
		
	    return;
	}

	//add to middle (a.k.a. all other cases):
	LNode n = getNthNode(index);
	LNode p = getNthNode(index - 1);
	LNode toBeAdded = new LNode(value);

	n.prev = toBeAdded;
	toBeAdded.next = n;

	p.next = toBeAdded;
	toBeAdded.prev = p;

	size++;
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

	System.out.println(l.indexOf(66)); //0
	System.out.println(l.indexOf(234)); //2
	System.out.println(l.indexOf(24123)); //-1

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
