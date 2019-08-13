class Solution {
    private Random random = new Random();
    
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int m = median(nums);
        int moves = 0;
        for (int v: nums) {
            moves += Math.abs(v - m);
        }
        return moves;
    }
    
    private int median(int[] nums) {
        return select(nums, 0, nums.length - 1, nums.length / 2);
    }
    
    // Quick select
    private int select(int[] nums, int l, int r, int k) {
        if (l == r && k == 0) return nums[l];
        int i = random.nextInt(r - l + 1) + l;
        swap(nums, l, i);
        int pivot = nums[l];
        int low = l, high = r;
        do {
            while (low < high && nums[high] >= pivot) --high;
            if (low < high) {
                nums[low] = nums[high];
                low++;
            }
            while (low < high && nums[low] <= pivot) ++low;
            if (low < high) {
                nums[high] = nums[low];
                --high;
            }
        } while (low != high);
        nums[low] = pivot;
        if (low - l == k) return nums[low];
        else if (low - l > k) return select(nums, l, low - 1, k);
        else return select(nums, low + 1, r, k - (low - l + 1));
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
