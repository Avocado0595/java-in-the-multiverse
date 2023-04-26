package doublyll;

public class DoublyLL<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLL() {
        this.head = null;
        this.tail = null;
    }

    public void append(T data) {
        Node<T> node = new Node<T>(data);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            return;
        }
        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
        this.tail.next = null;
    }

    public void printNodes() {
        // Node current will point to head
        Node<T> current = this.head;
        if (head == null) {
            System.out.println("Doubly linked list is empty");
            return;
        }
        System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            // Print each node and then go to next.
            System.out.println(current.data);
            current = current.next;
        }
    }
}
