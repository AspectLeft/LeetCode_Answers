class Solution {
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) return 0;
        int[] maxes = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        int[] mins = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        
        for (int v: nums) {
            maxes[3] = v;
            for (int i = 3; i > 0; --i) {
                if (maxes[i] > maxes[i - 1]) {
                    swap(maxes, i, i - 1);
                }
                else break;
            }
            
            mins[2] = v;
            for (int i = 2; i > 0; --i) {
                if (mins[i] < mins[i - 1]) {
                    swap(mins, i, i - 1);
                }
                else break;
            }
        }
        
        return Math.max(maxes[0] * maxes[1] * maxes[2], 
                        maxes[0] * mins[0] * mins[1]);
    }
}
