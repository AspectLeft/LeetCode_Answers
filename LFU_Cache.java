class LFUCache {
    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        FreqQueue queue;
        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    class FreqQueue {
        int freq;
        FreqQueue prev;
        FreqQueue next;
        
        ListNode head = null;
        ListNode tail = null;
        int length = 0;
        
        FreqQueue(int freq) {
            this.freq = freq;
        }
        
        void enQueue(ListNode node) {
            if (head == null) {
                head = tail = node;
            }
            else {
                node.prev = null;
                node.next = head;
                head.prev = node;
                head = node;
            }
            length++;
        }
        
        ListNode deQueue() {
            length--;
            ListNode node = tail;
            if (head == tail) {
                head = tail = null;
                return node;
            }
            tail = tail.prev;
            tail.next = null;
            node.prev = null;
            return node;
        }
        
        void remove(ListNode node) {
            length--;
            if (head == tail) {
                head = tail = null;
                return;
            }
            ListNode p = node.prev, q = node.next;
            node.prev = null;
            node.next = null;
            if (p != null) {
                p.next = q;
            }
            else {
                head = q;
            }
            if (q != null) {
                q.prev = p;
            }
            else {
                tail = p;
            }
        }
        
    }
    
    private final int capacity;
    private Map <Integer, ListNode> map = new HashMap<>();
    private FreqQueue head, tail;
    private int count = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (capacity <= 0) return -1;
        ListNode node = map.get(key);
        if (node == null) return -1;
        
        FreqQueue queue = node.queue;
        queue.remove(node);
        
        FreqQueue q = queue.next;
        if (q == null) {
            q = new FreqQueue(queue.freq + 1);
            queue.next = q;
            q.prev = queue;
            tail = q;
            q.enQueue(node);
        }
        else if (q.freq == queue.freq + 1) {
            q.enQueue(node);
        }
        else {
            FreqQueue newQ = new FreqQueue(queue.freq + 1);
            newQ.enQueue(node);
            queue.next = newQ;
            newQ.prev = queue;
            newQ.next = q;
            q.prev = newQ;
            q = newQ;
        }
        node.queue = q;
        // System.out.print("q.freq " + q.freq);
        
        if (queue.length == 0) {
            FreqQueue p = queue.prev;
            if (p != null) {
                p.next = q;
            }
            else {
                head = q;
            }
            q.prev = p;
        }
        
        return node.val;
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) return;
        ListNode node = map.get(key);
        if (node == null) {
            count++;
            if (count > capacity) 
                overflow();
            ListNode newNode = new ListNode(key, value);
            newNode.queue = new FreqQueue(0);
            newNode.queue.enQueue(newNode);
            map.put(key, newNode);
            newNode.queue.next = head;
            if (head != null) head.prev = newNode.queue;
            else tail = newNode.queue;
            head = newNode.queue;
            newNode.queue.prev = null;
            get(key);
            return;
        }
        node.val = value;
        get(key);
    }
    
    private void overflow() {
        ListNode node = head.deQueue();
        // System.out.println(node.val);
        if (head.length == 0) {
            FreqQueue q = head.next;
            if (q == null) {
                tail = null;
            }
            head.next = null;
            head = q;
        }
        map.remove(node.key);
        count--;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
