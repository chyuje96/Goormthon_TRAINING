
public class Main {
	public static void main(String[] args) {
		MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(1);
        list.add(2);
        
        list.addLast(3);
        
        list.addFirst(4);
        
        list.add(1, 6);

        System.out.print("List data -> ");
        
        for(Integer next : list){
            System.out.print(next + " ");
        }
        
        System.out.println();
	}
}


