class Solution {
    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length == 0) return new String[]{};
        int[][] numsWithId = new int[nums.length][2];
        for (int i = 0; i < nums.length; ++i) {
            numsWithId[i][0] = nums[i];
            numsWithId[i][1] = i;
        }
        Arrays.sort(numsWithId, Comparator.comparingInt(o -> -o[0]));
        String[] output = new String[nums.length];
        for (int i = 0; i < numsWithId.length; ++i) {
            if (i == 0) {
                output[numsWithId[i][1]] = "Gold Medal";
            }
            else if (i == 1) {
                output[numsWithId[i][1]] = "Silver Medal";
            }
            else if (i == 2) {
                output[numsWithId[i][1]] = "Bronze Medal";
            }
            else {
                output[numsWithId[i][1]] = String.valueOf(i + 1);
            }
        }
        return output;
    }
}
