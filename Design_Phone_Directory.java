class PhoneDirectory {
    Set<Integer> assigned;
    int maxN;
    Queue<Integer> available;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        assigned = new HashSet<>();
        maxN = maxNumbers;
        available = new LinkedList<>();
        for (int i = 0; i < maxNumbers; ++i) {
            available.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (available.isEmpty()) return -1;
        int n = available.remove();
        assigned.add(n);
        return n;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !assigned.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (!assigned.contains(number)) return;
        assigned.remove(number);
        available.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
