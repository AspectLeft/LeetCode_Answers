class MyCircularQueue {
    private int capacity;
    private int start;
    private int size;
    private int[] queue;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.capacity = k;
        this.start = 0;
        this.size = 0;
        this.queue = new int[k];
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (size == capacity) return false;
        queue[(start + size) % capacity] = value;
        size++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (size == 0) return false;
        start = (start + 1) % capacity;
        size--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (size == 0) return -1;
        return queue[start];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (size == 0) return -1;
        return queue[(start + size - 1) % capacity];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
