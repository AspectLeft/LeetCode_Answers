class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int a, b, c;
        int i, j;
        int count = 0;
        for (int k = 2; k < nums.length; ++k) {
            c = nums[k];
            i = 0;
            j = k - 1;
            a = nums[i];
            b = nums[j];
            while (i < j) {
                while (i < j && a + b <= c) {
                    i++;
                    a = nums[i];
                }
                if (i == j) break;
                count += j - i;
                j--;
                b = nums[j];
            }
        }
        return count;
    }
}
