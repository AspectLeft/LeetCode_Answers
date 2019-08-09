class Solution {
    public boolean circularArrayLoop(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int negative = 1;
        for (int i = 0; i < n; ++i) {
            negative = nums[i] < 0 ? -1 : 1;
            nums[i] = nums[i] % n;
        }
        
        int i = 0, j, k, stride, step, prev;
        boolean fail;
        for (; i < n; ++i) {
            if (nums[i] < -n || nums[i] > n - 1) {
                continue;
            }
            j = i;
            negative = nums[i] < 0 ? -1 : 1;
            fail = false;
            while (0 == (nums[j] / n)) {
                prev = j;
                j = (j + nums[j]) % n;
                if (j < 0) j += n;
                nums[prev] += negative * n;
                if (negative != (nums[j] < 0 ? -1 : 1)) {
                    fail = true;
                    break;
                }
            }
            if (!(fail || (negative * 2 == (nums[j] / n)))) {
                step = 0;
                k = j;
                while (true) {
                    stride = nums[k] % n;
                    k = (k + stride) % n;
                    if (k < 0) k += n;
                    step++;
                    if (k == j) {
                        if (step > 1) {
                            System.out.println("success");
                            System.out.println(k);
                            return true;
                        }
                        break;
                    }
                }
            }
            j = i;
            while (negative == (nums[j] / n)) {
                nums[j] += negative * n;
                j = (j + nums[j]) % n;
                if (j < 0) j += n;
            }
        }
        return false;
    }
}
