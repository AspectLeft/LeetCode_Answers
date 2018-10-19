class MinStack {
    int[] table;
    int tail;
    int[] min;

    /** initialize your data structure here. */
    public MinStack() {
        table = new int[20];
        tail = 0;
        min = new int[21];
        min[0] = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (tail == table.length) {
            doubleSize();
        }
        if (x <= min[tail]) min[tail + 1] = x;
        else min[tail + 1] = min[tail];
        table[tail] = x;
        tail++;
    }
    
    public void pop() {
        tail--;
    }
    
    public int top() {
        return table[tail - 1];
    }
    
    public int getMin() {
        return min[tail];
    }
    
    private void doubleSize() {
        int[] newTable = new int[2 * table.length];
        int[] newMin = new int[2 * table.length + 1];
        for (int i = 0; i < tail; ++i) {
            newTable[i] = table[i];
            newMin[i] = min[i];
        }
        newMin[tail] = min[tail];
        min = newMin;
        table = newTable;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
