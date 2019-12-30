class HitCounter {
    class Hit {
        int timestamp;
        int count = 1;
        
        Hit(int t) {
            this.timestamp = t;
        }
    }
    
    private Queue<Hit> queue;
    private Map<Integer, Hit> map;
    private int sum;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<>();
        map = new HashMap<>();
        sum = 0;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        if (map.containsKey(timestamp)) {
            map.get(timestamp).count++;
            sum++;
        }
        else {
            Hit hit = new Hit(timestamp);
            map.put(timestamp, hit);
            queue.add(hit);
            sum++;
            
            while (queue.element().timestamp + 5 * 60 <= timestamp) {
                Hit old = queue.remove();
                sum -= old.count;
                map.remove(old.timestamp);
            }
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && queue.element().timestamp + 5 * 60 <= timestamp) {
            Hit old = queue.remove();
            sum -= old.count;
            map.remove(old.timestamp);
        }
        return sum;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
