/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class SummaryRanges {

    /** Initialize your data structure here. */
    public SummaryRanges() {
        
    }
    
    class MyInterval {
        int start;
        int end;
        MyInterval() { start = 0; end = 0; }
        MyInterval(int v) { start = end = v; }
        MyInterval(int s, int e) { start = s; end = e; }

        @Override
        public boolean equals(Object o) {
            return o == this || o instanceof MyInterval && (cover(this, ((MyInterval) o).start) || cover((MyInterval) o, this.start));
        }
    }

    private static boolean cover(MyInterval interval, int v) {
        return interval.start <= v && v <= interval.end;
    }

    private TreeMap<MyInterval, MyInterval> rbt = new TreeMap<>(new Comparator<MyInterval>() {
        @Override
        public int compare(MyInterval o1, MyInterval o2) {
            if (o1.equals(o2)) return 0;
            if (o1.end < o2.start) return -1;
            return 1;
        }
    });

    public void addNum(int val) {
        MyInterval myInterval = rbt.get(new MyInterval(val));
        if (myInterval != null) return;
        MyInterval left = null, right = null;
        if (val > Integer.MIN_VALUE)
            left = rbt.remove(new MyInterval(val - 1));
        if (val < Integer.MAX_VALUE)
            right = rbt.remove(new MyInterval(val + 1));
        MyInterval newInterval = new MyInterval(val);
        if (left != null) newInterval.start = left.start;
        if (right != null) newInterval.end = right.end;

        rbt.put(newInterval, newInterval);
    }


    public List<Interval> getIntervals() {
        List<Interval> output = new ArrayList<>();
        for (MyInterval myInterval: rbt.keySet())
            output.add(new Interval(myInterval.start, myInterval.end));
        return output;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
