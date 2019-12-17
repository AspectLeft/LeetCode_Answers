class Solution {
    public int minMeetingRooms(int[][] intervals) {
        
        if (intervals == null || intervals.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        int count = 0;
        for (int[] interval: intervals) {
            if (queue.isEmpty() || queue.peek() > interval[0]) {
                count++;
            }
            else {
                queue.poll();
            }
            queue.offer(interval[1]);
        }
        return count;
    }
}
