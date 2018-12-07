class Solution {
    private Random random = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        return select(nums, 0, nums.length - 1, k - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        swap(nums, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; ++i) {
            if (nums[i] > pivotValue) {
                swap(nums, i, storeIndex);
                storeIndex++;
            }
        }
        swap(nums, storeIndex, right);
        return storeIndex;
    }
    
    private int select(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];
        int pivotIndex = random.nextInt(right - left + 1) + left;
        pivotIndex = partition(nums, left, right, pivotIndex);
        if (k == pivotIndex) return nums[k];
        else if (k < pivotIndex) return select(nums, left, pivotIndex - 1, k);
        else return select(nums, pivotIndex + 1, right, k);
    }
}
