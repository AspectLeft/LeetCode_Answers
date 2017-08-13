public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        int diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int l = i + 1, r = nums.length - 1, tmp;
                while (l < r) {
                    tmp = nums[l] + nums[r] + nums[i];
                    if (Math.abs(tmp - target) < diff) {
                        result = tmp;
                        diff = Math.abs(tmp - target);
                    }
                    if (tmp == target)
                        return target;
                    else if (tmp > target)
                        r--;
                    else
                        l++;
                }

            }
        }
        return result;
    }
}
