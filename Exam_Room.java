

public class ExamRoom {
    private int N;
    private int n = 0;
    TreeSet<int[]> intervals;
    TreeSet<Integer> taken;
    Map<Integer, int[]> leftIntervals;
    Map<Integer, int[]> rightIntervals;
    int v1;

    public ExamRoom(int N) {
        this.N = N;
        intervals = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int l1 = (o1[1] - o1[0]) & (~1);
                int l2 = (o2[1] - o2[0]) & (~1);
                int v = - Integer.compare(l1, l2);
                if (v != 0) return v;
                return Integer.compare(o1[0], o2[0]);
            }
        });
        taken = new TreeSet<>();
        leftIntervals = new HashMap<>();
        rightIntervals = new HashMap<>();
    }

    public int seat() {
        if (n == 0) {
            v1 = 0;
            taken.add(0);
            n++;
            return 0;
        }
        if (n == 1) {
            int v2 = N - 1;
            int r = v2;
            if (v1 >= v2 - v1) {
                v2 = v1;
                v1 = 0;
                r = 0;
            }
            int[] interval = new int[]{v1, v2};
            leftIntervals.put(v2, interval);
            rightIntervals.put(v1, interval);
            intervals.add(interval);
            taken.add(r);
            n++;
            return r;
        }
        int[] interval = intervals.first();
        int l = taken.first(), r = taken.last();
        int v = (interval[0] + interval[1]) / 2;
        int choice = 1;
        int d = v - interval[0];
        if (l >= d) {
            v = 0;
            d = l;
            choice = 0;
        }
        if (N - 1 - r > d) {
            v = N - 1;
            d = N - 1 - r;
            choice = 3;
        }
        if (choice == 1) {
            int[] leftInterval = new int[]{interval[0], v};
            int[] rightInterval = new int[]{v, interval[1]};
            intervals.remove(interval);
            intervals.add(leftInterval);
            intervals.add(rightInterval);
            leftIntervals.put(v, leftInterval);
            rightIntervals.put(v, rightInterval);
            rightIntervals.put(leftInterval[0], leftInterval);
            leftIntervals.put(rightInterval[1], rightInterval);

            taken.add(v);
            n++;
            return v;
        }
        if (choice == 0) {
            int[] rightInterval = new int[]{v, l};
            intervals.add(rightInterval);
            leftIntervals.put(l, rightInterval);
            rightIntervals.put(v, rightInterval);

            taken.add(v);
            n++;
            return v;
        }
        // choice == 2
        int[] leftInterval = new int[]{r, v};
        intervals.add(leftInterval);
        leftIntervals.put(v, leftInterval);
        rightIntervals.put(r, leftInterval);

        taken.add(v);
        n++;
        return v;
    }

    public void leave(int p) {
        n--;
        if (n == 0) {
            taken.remove(p);
            return;
        }
        if (n == 1) {
            int[] interval = leftIntervals.get(p);
            if (interval == null) {
                interval = rightIntervals.get(p);
                v1 = interval[1];
                rightIntervals.remove(p);
                leftIntervals.remove(v1);
            }
            else {
                v1 = interval[0];
                leftIntervals.remove(p);
                rightIntervals.remove(v1);
            }
            intervals.remove(interval);
            taken.remove(p);
            return;
        }
        if (p == taken.first()) {
            int[] interval = rightIntervals.get(p);
            intervals.remove(interval);
            leftIntervals.remove(interval[1], interval);
            rightIntervals.remove(p, interval);
            taken.remove(p);
            return;
        }
        if (p == taken.last()) {
            int[] interval = leftIntervals.get(p);
            intervals.remove(interval);
            leftIntervals.remove(p, interval);
            rightIntervals.remove(interval[0], interval);
            taken.remove(p);
            return;
        }
        int[] leftInterval = leftIntervals.get(p);
        int[] rightInterval = rightIntervals.get(p);
        intervals.remove(leftInterval);
        intervals.remove(rightInterval);
        int[] interval = new int[]{leftInterval[0], rightInterval[1]};
        intervals.add(interval);

        leftIntervals.remove(p);
        rightIntervals.remove(p);

        leftIntervals.put(rightInterval[1], interval);
        rightIntervals.put(leftInterval[0], interval);

        taken.remove(p);
    }
}


/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
