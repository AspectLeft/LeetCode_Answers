class Solution {
    
    private void swap(int[] nums, int m, int n) {
        if (m != n) {
            int tmp = nums[m];
            nums[m] = nums[n];
            nums[n] = tmp;
        }
    }

    private void reverse(int[] nums, int m, int n) {
        for (int i = m; 2 * i < m + n; ++i) {
            swap(nums, i, m + n - i);
        }
    }

    private boolean nextPermutation(int[] nums) {
        int length = nums.length;
        if (length <= 1)
            return false;
        int divide;
        for (divide = nums.length - 1; divide > 0; --divide) {
            if (nums[divide - 1] < nums[divide]) {
                break;
            }
        }
        if (divide > 0) {
            int target = length - 1;
            while (nums[divide - 1] >= nums[target]) --target;
            swap(nums, divide - 1, target);
            reverse(nums, divide, length - 1);
            return true;
        }
        else {
            return false;
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0)
            return new ArrayList<>();
        if (nums.length == 1)
            return Collections.singletonList(Collections.singletonList(nums[0]));
        Arrays.sort(nums);
        List<List<Integer>> set = new ArrayList<>();
        do {
            List<Integer> list = new ArrayList<>();
            for (int i: nums) list.add(i);
            set.add(list);
        } while (nextPermutation(nums));

        return set;
    }

}
