class Solution {
        
    private void swap(int[] nums, int l, int r) {
        if (l != r) {
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
    }

    
    public void sortColors(int[] nums) {
        int length = nums.length;
        if (length == 0) return;
        int x1 = 0, x2 = 0;
        for (int i = 0; i < length; ++i) {
            switch (nums[i]) {
                case 0:
                    if (x1 < x2) { //1 exists
                        swap(nums, i, x1);
                    }
                    x1++;
                    if (i > x2) { //2 exists
                        swap(nums, i, x2);
                    }
                    x2++;
                    break;
                case 1:
                    if (i > x2) { //2 exists
                        swap(nums, i, x2);
                    }
                    x2++;
                    break;
                case 2:
                    break;
            }
        }
    }

}
