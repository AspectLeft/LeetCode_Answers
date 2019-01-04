class RandomizedCollection {
    private int[] values;
    private int size;
    private Map<Integer, Set<Integer>> indices;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        values = new int[20];
        size = 0;
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
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean ret = true;
        Set<Integer> index = indices.get(val);
        if (index != null) ret = false;
        else {
            index = new HashSet<>();
            indices.put(val, index);
        }
        if (size == values.length) doubleSpace();
        values[size] = val;
        index.add(size++);
        return ret;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> index = indices.get(val);
        if (index == null) return false;
        
        Iterator<Integer> iterator = index.iterator();
        int i = iterator.next();
        if (i == size - 1) {
            iterator.remove();
            size--;
            if (index.isEmpty()) indices.remove(val);
            return true;
        }
        values[i] = values[size - 1];
        iterator.remove();
        if (index.isEmpty()) indices.remove(val);
        
        Set<Integer> index2 = indices.get(values[size - 1]);
        index2.remove(size - 1);
        index2.add(i);
        
        size--;
        return true;
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return values[random.nextInt(size)];
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
