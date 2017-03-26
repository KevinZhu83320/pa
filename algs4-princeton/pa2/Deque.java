import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head, tail;
    private int count;
    
    private static class Node<Item> {
        private Item item;
        private Node<Item> pre;
        private Node<Item> next;
        
        public Node(Item item, Node<Item> pre, Node<Item> next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
    public Deque() {                          // construct an empty deque
        
    }
    
    public boolean isEmpty() {                 // is the deque empty?
        return null == head;
    }
    
    public int size() {                        // return the number of items on the deque
        return count;
    }
    
    public void addFirst(Item item) {         // add the item to the front
        if (null == item) throw new NullPointerException();
        Node<Item> newHead = new Node<Item>(item, null, head);
        if (null != head)
            head.pre = newHead;
        head = newHead;
        if (null == tail)
            tail = head;
        count++;
    }
    
    public void addLast(Item item) {          // add the item to the end
        if (null == item) throw new NullPointerException("trying to add null item");
        Node<Item> newTail = new Node<Item>(item, tail, null);
        if (null != tail)
            tail.next = newTail;
        tail = newTail;
        if (null == head)
            head = tail;
        count++;
    }
    
    public Item removeFirst() {                // remove and return the item from the front
        if (isEmpty()) throw new NoSuchElementException("trying to remove from an empty deque");
        Item res = head.item;
        head = head.next;
        if (null != head)
            head.pre = null;
        else 
            tail = null;
        count--;
        return res;
    }
    
    public Item removeLast() {                // remove and return the item from the end
        if (isEmpty()) throw new NoSuchElementException("trying to remove from an empty deque");
        Item res = tail.item;
        tail = tail.pre;
        if (null != tail)
            tail.next = null;
        else
            head = null;
        count--;
        return res;
    }
    
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> cur;
        public DequeIterator() {
            cur = (Node<Item>) head;
        }
        public boolean hasNext() {
            return null != cur;
        }
        
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            Item res = cur.item;
            cur = cur.next;
            return res;
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Don't support remove from Iterator");
        }
    }
    
    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new DequeIterator<Item>();
    }
    
    public static void main(String[] args) {   // unit testing (optional)
        Deque<String> deque = new Deque<String>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addLast("c");
        System.out.println("size:"+deque.size());
        deque.walkThroughDeque(deque);
        deque.removeFirst();
        System.out.println("size:"+deque.size());
        deque.walkThroughDeque(deque);
        deque.addLast("d");
        System.out.println("size:"+deque.size());
        deque.walkThroughDeque(deque);
        deque.removeLast();
        System.out.println("size:"+deque.size());
        deque.walkThroughDeque(deque);
        deque.removeLast();
        System.out.println("size:"+deque.size());
        deque.walkThroughDeque(deque);
        System.out.println("size:"+deque.size());
        deque.removeLast();
        System.out.println("size:"+deque.size());
    }
    private void walkThroughDeque(Deque<Item> deque) {
        Iterator<Item> it = deque.iterator();
        while (it.hasNext()) {
            System.out.print(it.next()+",");
        }
        System.out.println();
    }
}