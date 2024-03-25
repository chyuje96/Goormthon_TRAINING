import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MyLinkedList<E> implements Iterable<E>{

    private Node<E> head;  // 노드의 첫 부분
    private Node<E> tail;  // 노드의 마지막 부분
    private int size; // 요소의 개수

    public MyLinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void clear(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    public int size(){
        return size;
    }


    @Override
    public String toString() {
        if(head == null) { //  head가 null이면
            return "[]"; // 빈 배열
        }

        Object[] array = new Object[size]; // 현재 size만큼 배열 생성

        int index = 0;
        Node<E> n = head;
        while (n != null) {
            array[index] = (E) n.data; // 배열에 노드값을 저장
            index++;
            n = n.next;
        }

        return Arrays.toString(array); // 배열을 String으로 반환
    }

    // switch 메소드
    public void set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> replace_node = search(index); // search 메소드를 이용

        replace_node.data = null; // 교체할 노드의 요소를 변경
        replace_node.data = value;
    }

    // get 메소드
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return search(index).data;
    }

    // 마지막 요소 remove
    public E removeLast() {
        return remove(size - 1);
    }

    // 값으로 삭제하는 메소드
    public boolean remove(Object value) {
        if (head == null) { // 삭제할 요소가 없으면
            throw new RuntimeException(); // 에러
        }

        Node<E> prev_node = null; // 이전 노드
        Node<E> del_node = null; // 삭제 노드
        Node<E> next_node = null; // 다음 노드

        Node<E> i = head;

        while (i != null) { // 노드의 next를 순회하면서 해당 값을 찾음
            if (Objects.equals(i.data, value)) { // 노드의 값과 매개변수 값이 같으면
                del_node = i; // 삭제 노드에 요소를 대입
                break;
            }

            prev_node = i; // prev 정보가 없기 때문에 이전 노드에 요소를 일일히 대입
            i = i.next;
        }

        if (del_node == null) { // 찾은 요소가 없다면
            return false;
        }

        if (del_node == head) { // 삭제하려는 노드가 head라면
            removeFirst(); // 첫번째 요소를 삭제하므로, removeFirst()를 사용
            return true;
        }

        next_node = del_node.next; // 다음 노드에 삭제 노드 next의 요소를 대입

        del_node.next = null; // 삭제 요소 데이터 모두 제거
        del_node.data = null;

        size--; // 요소가 삭제 되었으므로, 크기 감소

        prev_node.next = next_node; // 이전 노드가 다음 노드를 참조하도록 바꿈

        return true;
    }

    // 특정 요소를 삭제하는 메소드
    public E remove(int index) {
        if (index < 0 || index >= size) { // index가 0보다 작거나 size 보다 크거나 같으면
            throw new IndexOutOfBoundsException(); // 에러
        }

        if (index == 0) { // index가 0이면
            return removeFirst(); // removeFirst 메서드 실행
        }

        Node<E> prev_node = search(index - 1); // 삭제할 위치의 이전 노드 저장
        Node<E> del_node = prev_node.next;  // 삭제할 위치의 노드 저장
        Node<E> next_node = del_node.next; // 삭제할 위치의 다음 노드 저장
        E returnValue = del_node.data;  // 삭제될 첫번째 요소의 데이터를 저장
        
        del_node.next = null; // 삭제 노드의 내부 요소를 모두 삭제
        del_node.data = null;

        size--; // 요소가 삭제 되었으니 크기 감소
        
        prev_node.next = next_node; // 이전 노드가 다음 노드를 가리키도록 변경

        return returnValue; // 마지막으로 삭제된 요소를 반환
    }

    // 첫 요소 삭제
    public E removeFirst() {
        if (head == null) { // 삭제할 요소가 아무것도 없으면
            throw new IndexOutOfBoundsException(); // 에러
        }

        E returnValue = head.data; // 삭제될 첫번째 요소의 데이터를 백업

        Node<E> first = head.next; // 두번째 노드를 임시 저장

        head.next = null; // 첫번째 노드의 내부 요소를 모두 삭제
        head.data = null;

        head = first; // head가 다음 노드를 가리키도록 변경

        size--; // 요소가 삭제 되었으니 크기 감소

        if (head == null) { // 만일 리스트의 유일한 값을 삭제해서 빈 리스트가 되면
            tail = null; // tail도 null 처리
        }

        return returnValue; // 마지막으로 삭제된 요소를 반환
    }

    public E remove() {
        return removeFirst();
    }

    // 중간 삽입
    public void add(int index, E value) {
        if (index < 0 || index >= size) { // 인덱스가 0보다 작거나 size 보다 같거나 크면
            throw new IndexOutOfBoundsException(); // 에러
        }
        
        if (index == 0) { // 추가하려는 index가 0이면
            addFirst(value); // addFirst 호출
            return;
        }
        if (index == size - 1) { // 추가하려는 index가 size - 1와 같으면 
            addLast(value); // addLast 호출
            return;
        }

        Node<E> prev_node = search(index - 1); // 추가하려는 위치의 이전 노드 얻음
        Node<E> next_node = prev_node.next; // 추가하려는 위치의 다음 노드 얻음
        Node<E> newNode = new Node<>(value, next_node); // 새 노드 생성 (바로 다음 노드와 연결)

        size++; // size 증가

        prev_node.next = newNode; // 이전 노드를 새 노드와 연결
    }

    public boolean add(E data) {
        addLast(data);
        return true;
    }

    // 요소를 마지막에 추가
    public void addLast(E data) {
        Node<E> last = tail; // 먼저 가장 뒤의 요소를 가져옴

        Node<E> newNode = new Node<>(data, null); // 새 노드 생성

        size++; // size 증가

        tail = newNode; // tail을 변경

        if (last == null) { // 최초로 요소가 add 된 것이면
            head = newNode; // head와 tail이 가리키는 요소는 같게 된다.
        } else {
            last.next = newNode; // last 변수에 추가된 새 노드를 가리키도록 변경
        }
    }

    // 요소를 처음에 추가
    void addFirst(E data){
        Node<E> first = head; // 가장 앞의 요소를 가져옴
        Node<E> newNode = new Node<>(data, first); // 2. 새 노드 생성

        size++; // size 증가
        
        head = newNode; // head를 변경

        if(first == null){ // 최초로 요소가 add 된 것이라면 
            tail = newNode; // head 와 tail 이 카리키는 요소를 같게 설정
        }

    }

    // Node 찾는 메소드
    private Node<E> search(int index){
        Node<E> n = head;
        
        for(int i = 0; i < index; i++){
            n = n.next;
        }
        
        return n;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator(head);
    }

    // CustomIterator 클래스는 Iterator 인터페이스를 구현
    private class CustomIterator implements Iterator<E> {
        Node current;

        public CustomIterator(Node head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = (E)current.data;
            current = current.next;
            return data;
        }
    }

    private static class Node<E>{
        private E data;
        private Node<E> next;

        Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }

    }
}