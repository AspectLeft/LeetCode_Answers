class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length < 2) return new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 1; i < nums.length; ++i) {
            Set<List<Integer>> newSet = new HashSet<>();
            for (List<Integer> sub: set) {
                if (nums[i] >= sub.get(sub.size() - 1)) {
                    List<Integer> newSub = new ArrayList<>(sub);
                    newSub.add(nums[i]);
                    newSet.add(newSub);
                }
            }
            set.addAll(newSet);
            for (int j = 0; j < i; ++j) {
                if (nums[j] <= nums[i]) {
                    List<Integer> newSub = new ArrayList<>();
                    newSub.add(nums[j]);
                    newSub.add(nums[i]);
                    set.add(newSub);
                }
            }
        }
        return new ArrayList<>(set);
    }
}
