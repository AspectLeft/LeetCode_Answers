class Solution {
public:
    int numSubarrayProductLessThanK(vector<int>& nums, int k) {
        if (nums.size() == 0) return 0;
        int p = 1;
        int i = 0, j = 0;
        int count = 0;
        if (k < 2) {
            return 0;
        }
        while (i < nums.size()) {
            while (j < nums.size() && p < k) {
                p *= nums[j];
                j++;
            }
            if (p >= k) {
                count += j - i - 1;
            }
            else {
                count += j - i;
            }
            p /= nums[i];
            i++;
        }
        return count;
    }
};
