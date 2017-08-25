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
    
    public List<Interval> merge(List<Interval> intervals) {
        int size = intervals.size();
        if (size <= 1) return intervals;
        intervals.sort(Comparator.comparingInt(o -> o.start));
        List<Interval> newIntervals = new LinkedList<>();
        int j = 1;
        Interval interval1 = intervals.get(0);
        while (j < size) {
            Interval interval2 = intervals.get(j);
            j++;
            if (interval1.end >= interval2.start)
                interval1.end = Math.max(interval1.end, interval2.end);
            else {
                newIntervals.add(interval1);
                interval1 = interval2;
            }
        }

        newIntervals.add(interval1);

        return newIntervals;
    }

}
