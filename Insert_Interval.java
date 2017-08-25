/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    private boolean interleaving(Interval i1, Interval i2) {
        return !(i1.end < i2.start || i2.end < i1.start);
    }

    private Interval merge(Interval i1, Interval i2) {
        return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end, i2.end));
    }


    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> newIntervals = new ArrayList<>();
        if (intervals.size() == 0) {
            newIntervals.add(newInterval);
            return newIntervals;
        }
        boolean inserted = false;
        for (Interval i: intervals) {
            if (interleaving(i, newInterval)) {
                newInterval = merge(newInterval, i);
            }
            else {
                if (i.end < newInterval.start) {
                    newIntervals.add(i);
                }
                else {
                    if (!inserted) {
                        newIntervals.add(newInterval);
                        inserted = true;
                    }
                    newIntervals.add(i);                    
                }
            }
            
        }
        if (!inserted) {
            newIntervals.add(newInterval);
        }
        return newIntervals;
    }

}
