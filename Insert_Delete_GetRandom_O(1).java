class RandomizedSet {
    
    private int[] values;
    private int size;
    private Map<Integer, Integer> indices;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        size = 0;
        values = new int[20];
        indices = new HashMap<>();
        random = new Random();
    }
    
    private void doubleSpace() {
        int[] newvalues = new int[values.length << 1];
        for (int i = 0; i < values.length; ++i) {
            newvalues[i] = values[i];
        }
        values = newvalues;
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (indices.get(val) != null) return false;
        int index = size;
        if (index == values.length) doubleSpace();
        // System.out.println(index);
        values[index] = val;
        size++;
        indices.put(val, index);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer index = indices.get(val);
        if (index == null) return false;
        values[index] = values[--size];
        indices.put(values[index], index);
        indices.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        // System.out.println(size);
        return values[random.nextInt(size)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
