class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length < 2) return true;
        int n = stones.length;
        List<Set<Integer>> steps = new ArrayList<>();
        Set<Integer> init = new HashSet<>();
        init.add(1);
        steps.add(init);
        Map<Integer, Integer> stoneIndices = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            steps.add(new HashSet<>());
            
            stoneIndices.put(stones[i], i);
        }
        
        
        for (int i = 0; i < n - 1; ++i) {
            for (int stride: steps.get(i)) {
                Integer nextStoneIndex = stoneIndices.get(stones[i] + stride);
                if (nextStoneIndex != null) {
                    Set<Integer> step = steps.get(nextStoneIndex);
                    if (stride > 1) step.add(stride - 1);
                    step.add(stride);
                    step.add(stride + 1);
                }
            }
        }
        /*
        for (int i = 1; i < n; ++i) {
            Set<Integer> step = new HashSet<>();
            for (int prev = 0; prev < i; ++prev) {
                if (steps.get(prev).contains(stones[i] - stones[prev])) {
                    int stride = stones[i] - stones[prev];
                    step.add(stride - 1);
                    step.add(stride);
                    step.add(stride + 1);
                }
            }
            steps.add(step);
        }
        */
        return !steps.get(n - 1).isEmpty();
    }
}
