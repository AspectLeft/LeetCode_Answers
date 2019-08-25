class Solution {
    public String optimalDivision(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        if (nums.length == 1) return String.valueOf(nums[0]);
        if (nums.length == 2) return String.format("%d/%d", nums[0], nums[1]);
        StringBuilder builder = new StringBuilder();
        builder.append(nums[0]);
        builder.append("/(");
        builder.append(nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            builder.append('/');
            builder.append(nums[i]);
        }
        
        builder.append(')');
        
        return builder.toString();
    }
}
