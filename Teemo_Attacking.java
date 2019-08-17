class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0) return 0;
        if (timeSeries.length == 1) return duration;
        int result = duration;
        for (int i = 1; i < timeSeries.length; ++i) {
            result += Math.min(duration, timeSeries[i] - timeSeries[i - 1]);
        }
        return result;
    }
}
