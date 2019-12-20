class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        boolean larger = true;
        int tmp;
        for (int i = 1; i < nums.length; ++i) {
            if (larger != (nums[i] >= nums[i - 1])) {
                tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            }
            larger = !larger;
        }
    }
}
