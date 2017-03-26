import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] bucket;
    private int capacity;
    private int count;
    
    public RandomizedQueue() {                // construct an empty randomized queue
        capacity = 10;
        bucket = (Item[]) new Object[capacity];
    }
    
    private void resize(int cap) {
        Item[] array = (Item[]) new Object[cap];
        for (int i = 0; i < count; i++) {
            array[i] = bucket[i];
        }
        bucket = array;
        capacity = cap;
    }
    public boolean isEmpty() {                // is the queue empty?
        return 0 == count;
    }
    
    public int size() {                       // return the number of items on the queue
        return count;
    }
    
    public void enqueue(Item item) {          // add the item
        if (null == item) throw new NullPointerException();
        if (count == capacity)
            resize(capacity * 2);
        bucket[count++] = item;
    }
        
    public Item dequeue() {                    // remove and return a random item
        if (isEmpty()) throw new NoSuchElementException("no more elements");
        int idx = StdRandom.uniform(count);
        Item item = bucket[idx];
        bucket[idx] = bucket[--count];
        return item;
    }
    
    public Item sample() {                    // return (but do not remove) a random item
        if (isEmpty()) throw new NoSuchElementException("no more elements");
        int idx = StdRandom.uniform(count);
        return bucket[idx];
    }
    
    private class RandomArrayIterator<Item> implements Iterator<Item> {
        private int[] indices;
        private int cur;
        
        public RandomArrayIterator() {
            indices = new int[count];
            for (int i = 0; i < count; i++) {
                indices[i] = i;
            }
            for (int i = 0; i < count; i++) {
                int idx = StdRandom.uniform(count);
                int tmp = indices[idx];
                indices[idx] = indices[i];
                indices[i] = tmp;
            }            
        }
        
        public boolean hasNext() {
            return cur < indices.length;
        }
        
       public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return (Item) bucket[indices[cur++]];
        }
        
        public void remove() {
            throw new UnsupportedOperationException("Don't support remove from Iterator");
        }
    }
    
    public Iterator<Item> iterator() {        // return an independent iterator over items in random order
        return new RandomArrayIterator<Item>();
    }
    
    public static void main(String[] args) {  // unit testing (optional)
        /*
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        for (int i = 0; i < 50; i++) {
            if (i % 5 == 0) {
                System.out.println("size:" + rq.size());
            }
            rq.enqueue(i + "");
        } 
        for (int i = 0; i < 7; i++) {
            System.out.print(rq.sample()+",");
        }
        System.out.println();
        System.out.println(rq.dequeue());
        rq.enqueue("I");
        
        for (int i = 0; i < rq.size(); i++) {
            System.out.print(rq.sample()+",");
        }
        System.out.println();
        Iterator<String> it = rq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next()+",");
        }
        System.out.println();
        */
        int[] hits = new int[4];
        for (int i = 0; i < 20000; i++) {
            hits[StdRandom.uniform(4)]++;
        }
        System.out.println(hits[0] +"," + hits[1]+"," + hits[2]+"," + hits[3]);
    }
}