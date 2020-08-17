class Solution {
    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0) return 0;
        int t = 0;
        PriorityQueue<int[]> takenQueue = new PriorityQueue<>(20, Comparator.comparingInt(c -> -c[0]));
        Arrays.sort(courses, Comparator.comparingInt(c -> c[1]));
        for (int[] course: courses) {
            if (t + course[0] <= course[1]) {
                takenQueue.add(course);
                t += course[0];
            }
            else {
                if (!takenQueue.isEmpty() && takenQueue.peek()[0] > course[0]) {
                    t = t - takenQueue.poll()[0] + course[0];
                    takenQueue.add(course);
                }
            }
        }
        return takenQueue.size();
    }
}
