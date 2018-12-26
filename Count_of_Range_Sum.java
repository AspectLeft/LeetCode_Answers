class Solution {
    class Interval {
        long a, b;
        Interval(long a, long b) {
            this.a = a;
            this.b = b;
        }
        
        private int contains(long v) {
            return (a <= v && v <= b) ? 1 : 0;
        }
    }
    
    private Interval target;
    private int n;
    private int[] nums;
    
    private int countRangeSum(int l, int r) {
        if (l == r) return target.contains(nums[l]);
        int mid = (l + r) / 2;
        int count = countRangeSum(l, mid) + countRangeSum(mid + 1, r);
        long sumL = 0;
        long sumR = 0;
        long[] leftSums = new long[mid - l + 1];
        for (int i = mid; i >= l; --i) {
            sumL += nums[i];
            leftSums[i - l] = sumL;
        }
        Arrays.sort(leftSums);
        sumR = 0;
        for (int j = mid + 1; j <= r; ++j) {
            sumR += nums[j];
            count += countCovered(new Interval(target.a - sumR, target.b - sumR), leftSums);
        }
        return count;
    }
    
    private int countCovered(Interval interval, long[] leftSums) {
        return indexOfFirstMore(leftSums, interval.b) - indexOfFirstLess(leftSums, interval.a) - 1;
    }
    
    private int indexOfFirstLess(long[] leftSums, long a) {
        int l = 0, r = leftSums.length - 1, mid;
        while (l < r) {
            mid = (l + r + 1) / 2;
            if (leftSums[mid] < a) l = mid;
            else r = mid - 1;
        }
        if (leftSums[l] < a) return l;
        else return l - 1;
    }
    
    private int indexOfFirstMore(long[] leftSums, long b) {
        int l = 0, r = leftSums.length - 1, mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (leftSums[mid] > b) r = mid;
            else l = mid + 1;
        }
        if (leftSums[l] > b) return l;
        else return l + 1;
    }
    
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        n = nums.length;
        this.nums = nums;
        target = new Interval(lower, upper);
        
        return countRangeSum(0, n - 1);
    }
}
