class Solution {
    private int f(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
    
    private void reverse(int[] result) {
        int i = 0, j = result.length - 1;
        while (i < j) {
            int tmp = result[i];
            result[i] = result[j];
            result[j] = tmp;
            i++;
            j--;
        }
    }
    
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length < 2) return nums;
        int[] result = new int[nums.length];
        
        if (a == 0) {
            for (int i = 0; i < nums.length; ++i) {
                result[i] = f(nums[i], a, b, c);
            }
            if (b < 0) reverse(result);
            return result;
        }
        
        double div = -1.0 * b / 2 / a;
        // System.out.println(div);
        
        if (div <= nums[0]) {
            for (int i = 0; i < nums.length; ++i) {
                result[i] = f(nums[i], a, b, c);
            }
            if (a < 0) reverse(result);
            return result;
        }
        if (div >= nums[nums.length - 1]) {
            for (int i = 0; i < nums.length; ++i) {
                result[nums.length - 1 - i] = f(nums[i], a, b, c);
            }
            if (a < 0) reverse(result);
            return result;
        }
        else {
            int l = 0, r = nums.length - 1;
            while (l < r) {
                int mid = (l + r + 1) / 2;
                if (div >= nums[mid]) {
                    l = mid;
                }
                else {
                    r = mid - 1;
                }
            }
            // System.out.println(l);
            r = l + 1;
            
            if (a > 0) {
                
                for (int i = 0; i < result.length; ++i) {
                    if (r == result.length) {
                        result[i] = f(nums[l], a, b, c);
                        l--;
                        continue;
                    }
                    if (l < 0) {
                        result[i] = f(nums[r], a, b, c);
                        r++;
                        continue;
                    }
                    int c1 = f(nums[l], a, b, c), c2 = f(nums[r], a, b, c);
                    if (c1 < c2) {
                        result[i] = c1;
                        l--;
                    }
                    else {
                        result[i] = c2;
                        r++;
                    }
                }
            }
            else {
                for (int i = result.length - 1; i >= 0; --i) {
                    if (r == result.length) {
                        result[i] = f(nums[l], a, b, c);
                        l--;
                        continue;
                    }
                    if (l < 0) {
                        result[i] = f(nums[r], a, b, c);
                        r++;
                        continue;
                    }
                    int c1 = f(nums[l], a, b, c), c2 = f(nums[r], a, b, c);
                    if (c1 > c2) {
                        result[i] = c1;
                        l--;
                    }
                    else {
                        result[i] = c2;
                        r++;
                    }
                }
            }
            
            
        }
        return result;
    }
}
