class LRUCache {
    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        ListNode(int k, int v) {this.key = k; this.val = v;};
    }
    
    
    private final int capacity;
    private Map <Integer, ListNode> map = new HashMap<>();
    private ListNode head, tail;
    private int count = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        ListNode node = map.get(key);
        if (node == null) return -1;
        ListNode p = node.next, q = node.prev;
        if (node != head) {
            if (q != null)
                q.next = p;
            if (p != null)
                p.prev = q;
            else tail = q;
            node.next = head;
            head.prev = node;
            node.prev = null;
            head = node;
        }
        
        
        return node.val;
    }
    
    public void put(int key, int value) {
        ListNode node = map.get(key);
        if (node == null) {
            ListNode newNode = new ListNode(key, value);
            map.put(key, newNode);
            newNode.next = head;
            if (head != null) head.prev = newNode;
            else tail = newNode;
            head = newNode;
            newNode.prev = null;
            count++;
            if (count > capacity) 
                overflow();
            return;
        }
        node.val = value;
        get(key);
    }
    
    private void overflow() {
        ListNode p = tail.prev;
        p.next = null;
        tail.prev = null;
        ListNode tmp = tail;
        tail = p;
        count--;
        map.remove(tmp.key);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
