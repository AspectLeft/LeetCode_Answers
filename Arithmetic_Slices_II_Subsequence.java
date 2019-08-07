class Solution {
    
    private boolean subtractOverflow(int a, int b) {
        long d = (long) a - b;
        return d > Integer.MAX_VALUE || d < Integer.MIN_VALUE;
    }
    
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        List<Map<Integer, Integer>> seqMapList = new ArrayList<>();
        int N = A.length;
        int result = 0;
        for (int i = 0; i < N; ++i) {
            Map<Integer, Integer> seqMap = new HashMap<>();
            for (int j = 0; j < i; ++j) {
                if (subtractOverflow(A[i], A[j])) continue;
                int diff = A[i] - A[j];
                int c2 = seqMapList.get(j).getOrDefault(diff, 0);
                seqMap.put(diff, seqMap.getOrDefault(diff, 0)
                        + c2 + 1);
                result += c2;
            }
            seqMapList.add(seqMap);
        }
        return result;
    }
}
