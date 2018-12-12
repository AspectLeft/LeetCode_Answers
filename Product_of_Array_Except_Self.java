class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, 1);
        int a = 1;
        for (int i = 0; i < nums.length; ++i) {
            result[i] *= a;
            a *= nums[i];
        }
        a = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            result[i] *= a;
            a *= nums[i];
        }
        return result;
    }
}
