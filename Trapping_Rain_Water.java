class Solution {
    
    public int trap(int[] height) {
        
        if (height.length <= 2)
            return 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int max = 0;
        for (int i = 1; i < height.length - 1; ++i) {
            if (height[i - 1] > max) {
                max = height[i - 1];
            }
            leftMax[i] = max;
        }
        max = 0;
        for (int i = height.length - 2; i > 0; --i) {
            if (height[i + 1] > max) {
                max = height[i + 1];
            }
            rightMax[i] = max;
        }
        int volume = 0;
        for (int i = 1; i < height.length - 1; ++i) {
            volume += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return volume;
    }

}
