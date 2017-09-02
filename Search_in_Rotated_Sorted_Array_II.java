class Solution {
    
    private int find(int[] nums, int target, int m, int n) {
        if (m > n) return -1;
        int mid = (m + n) / 2;
        if (target == nums[mid])
            return mid;
        else if (target > nums[mid])
            return find(nums, target, mid + 1, n);
        else return find(nums, target, m, mid - 1);

    }

    private boolean search(int[] nums, int target, int l, int r) {
        if (l > r) return false;
        if (l == r) return find(nums, target, l, l) >= 0;
        if (nums[l] < nums[r])
            return find(nums, target, l, r) >= 0;
        int ll = l, rr = r;
        int mid = 0;
        while (ll < rr) {
            mid = (ll + rr) / 2;
            if (nums[mid] > nums[mid + 1]) {
                ll = mid + 1;
                break;
            }
            else if (nums[mid] > nums[r]) {
                ll = mid + 1;
            }
            else if (nums[mid] == nums[r] && nums[l] == nums[r]) {
                return search(nums, target, l, mid) || search(nums, target, mid + 1, r);
            }
            else {
                rr = mid;
            }
        }
        return find(nums, target, ll, r) >= 0 || find(nums, target, l, ll - 1) >= 0;
    }

    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        return search(nums, target, 0, nums.length - 1);
    }

}
