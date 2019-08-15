class Solution {
    public int totalHammingDistance(int[] nums) {
        int[] zero = new int[32];
        int i;
        for (int v: nums) {
            for (i = 0; i < 32; ++i) {
                if ((v & (1 << i)) == 0) zero[i]++;
            }
        }
        int result = 0, n = nums.length;
        for (i = 0; i < 32; ++i) {
            result += zero[i] * (n - zero[i]);
        }
        return result;
    }
}
