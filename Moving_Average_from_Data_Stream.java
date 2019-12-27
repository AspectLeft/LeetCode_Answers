class MovingAverage {
    Queue<Integer> window;
    int size;
    double sum;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.window = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }
    
    public double next(int val) {
        if (window.size() == this.size) {
            int v = window.remove();
            sum -= v;
        }
        window.add(val);
        sum += val;
        return sum / window.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
