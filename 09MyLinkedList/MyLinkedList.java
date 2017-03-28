public class MyLinkedList{
    //instance vars
    private LNode start;
    private int size;

    //default constructor:
    public MyLinkedList(){
	//defaults to null for start, 0 for size
    }

    //add something to the start of a linked list	
    public void add(int element){
	LNode newStart = new LNode(element, start);
	start = newStart;
	size++;
    }
    
    //toString
    public String toString(){
	String ans = "";
	LNode current = start;

	while(current != null){
	    ans += current.value + " ";
	    current = current.next;
	}

	return ans;
    }

    //inner LNode class
    private class LNode{
	private int value;
	private LNode next;

	//constructor:
	public LNode(int val, LNode nex){
	    value = val;
	    next = nex;
	}
    }

    //TESTS
    public static void main(String[] args){
	System.out.println("Hi");
    }
}
