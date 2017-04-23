import java.util.*;

public class MyDeque{
    private String[] data;
    private int back; //add to back
    private int front; //remove from front
    private int size; //number of elements
    
    public MyDeque(){
	//back to 0, front to 0, size to 0
	data = new String[10]; //arbitrary size
    }

    //important to note: 'first' means wherever front index is...
    //'last' means wherever back index is--this is how I'm interpreting it
    //at least

    //adding to front (moves to the left first, THEN places)
    public void addFirst(String s){
	if(s.equals(null)){
	    throw new NullPointerException();
	}

	//for resizing the array:
	if(size != 0 && front == back){
	    resize();
	}

	front--;
	if(front < 0){ //for circling around
	    front = data.length - 1;
	}

	data[front] = s;
	
	size++;	
    }

    //adding to back (places, THEN moves to the right) 
    public void addLast(String s){
	if(s.equals(null)){
	    throw new NullPointerException();
	}

	if(size != 0 && front == back){
	    resize();
	}

	data[back] = s;
	back++;
	
	if(back == data.length){ //for circling around
	    back = 0;
	}
	
	size++;
    }

    //private helper method that resizes data when it's full:
    private void resize(){
	int newLength = data.length * 2 + 1;
	String[] newData = new String[newLength];

	int counter = 0;
	int f = front;
	while(counter < data.length){
	    if(f == data.length){ //for circling around
		f = 0;
	    }
	    
	    newData[counter] = data[f];
	    f++;
	    counter++;
	}

	front = 0;
	back = data.length;

	data = newData;
    }
       
    //removing from front
    //opposite of adding, so remove first, then move right
    public String removeFirst(){
	if(size == 0){
	    throw new NoSuchElementException();
	}

	String holder = data[front];
	front++;
	if(front == data.length){ //for circling around
	    front = 0;
	}

	size--;
	return holder;	
    }

    //removing from back
    //opposite of adding, so move left first, then remove
    public String removeLast(){
	if(size == 0){
	    throw new NoSuchElementException();
	}

	back--;
	if(back < 0){
	    back = data.length - 1;
	}

	size--;
	return data[back];
    }
    
    //getting from front
    public String getFirst(){
	if(size == 0){
	    throw new NoSuchElementException();
	}

	return data[front];
    }

    //getting from back
    public String getLast(){
	if(size == 0){
	    throw new NoSuchElementException();
	}
	
	int b = back - 1;
	if(b < 0){ //for circling around
	    b = data.length - 1;
	}
       	
	return data[b];
    }

    //toString()
    public String toString(){
	String ans = "";
	int f = front; 
	for(int i = 0; i < size; i++){
	    if(f == data.length){ //for circling around
		f = 0; 
	    }
	    
	    ans += data[f] + ", ";
	    f++;
	}
	
	return ans; 	
    }

    //TESTS
    public static void main(String[] args){
	MyDeque d = new MyDeque();
	d.addFirst("1");
	d.addFirst("2");
	d.addLast("3");
	d.addLast("4");
	d.addFirst("5");
	d.addFirst("6");
	d.addFirst("7");
	d.addLast("8");
	d.addLast("9");
	d.addLast("10");
	d.addFirst("11 -- resize me plz");
	System.out.println(d); //11--resize plz, 7, 6, 5, 2, 1, 3, 4, 8, 9, 10 
	System.out.println(d.data.length); //21

	System.out.println(d.getLast()); //10
	System.out.println(d.removeLast()); //10
	d.removeLast(); //9
	d.removeFirst(); //11--resize plz
	System.out.println(d.getFirst()); //7
	System.out.println(d.getLast()); //8
	System.out.println(d); //7, 6, 5, 2, 1, 3, 4, 8, 9, 10

	//for exceptions 
	MyDeque empty = new MyDeque();
	//empty.getFirst(); //no such element
	//empty.removeFirst(); //no such element
	//empty.addFirst(null); //null pointer
    }
}
