package doublyll;

public class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    public Node() {
        this.next = null;
        this.prev = null;
        this.data = null;
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
