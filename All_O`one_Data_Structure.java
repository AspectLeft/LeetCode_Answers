class AllOne {
    private Map<String, Integer> map;
    private Map<Integer, Node> values;
    
    class Node {
        Integer value;
        Set<String> keys;
        Node prev, next;
        Node() { keys = null; next = null; prev = null; }
        Node(Integer v, Set<String> ks) {
            value = v;
            keys = ks;
        }
    }
    
    private Node head;
    private Node tail;
    
    private void insertAfter(Node p, Node node) {
        Node q = p.next;
        p.next = node;
        node.prev = p;
        node.next = q;
        q.prev = node;
    }
    
    private void insertBefore(Node q, Node node) {
        Node p = q.prev;
        p.next = node;
        node.prev = p;
        node.next = q;
        q.prev = node;
    }
    
    private void remove(Node node) {
        Node p = node.prev, q = node.next;
        p.next = q;
        q.prev = p;
        values.remove(node.value);
    }

    /** Initialize your data structure here. */
    public AllOne() {
        map = new HashMap<>();
        values = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.next = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        Integer value = map.get(key);
        if (value == null) {
            map.put(key, 1);
            
            Node node = values.get(1);
            if (node == null) {
                node = new Node(1, new HashSet<>());
                insertAfter(head, node);
                values.put(1, node);
            }
            node.keys.add(key);
            
        }
        else {
            map.put(key, value + 1);
            
            Node node = values.get(value);
            Node q = node.next;
            node.keys.remove(key);
            if (node.keys.isEmpty()) remove(node);
            
            node = values.get(value + 1);
            if (node == null) {
                node = new Node(value + 1, new HashSet<>());
                insertBefore(q, node);
                values.put(value + 1, node);
            }
            node.keys.add(key);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        Integer value = map.get(key);
        if (value == null) return;
        if (value == 1) {
            map.remove(key);
            Node node = values.get(1);
            node.keys.remove(key);
            if (node.keys.isEmpty()) remove(node);
        }
        else {
            map.put(key, value - 1);
            Node node = values.get(value);
            Node p = node.prev;
            node.keys.remove(key);
            if (node.keys.isEmpty()) remove(node);
            
            node = values.get(value - 1);
            if (node == null) {
                node = new Node(value - 1, new HashSet<>());
                insertAfter(p, node);
                values.put(value - 1, node);
            }
            node.keys.add(key);
        }
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail) return "";
        return tail.prev.keys.iterator().next();
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail) return "";
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
