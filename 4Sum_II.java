class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        if (A == null || A.length == 0) return 0;
        Map<Integer, Integer> abMap = getMap(A, B);
        int result = 0;
        for (int c: C) {
            for (int d: D) {
                result += abMap.getOrDefault(-(c + d), 0);
            }
        }
        return result;
    }
    
    private Map<Integer, Integer> getMap(int[] A, int[] B) {
        Map<Integer, Integer> abMap = new HashMap<>();
        int ab;
        for (int a: A) {
            for (int b: B) {
                ab = a + b;
                abMap.put(ab, abMap.getOrDefault(ab, 0) + 1);
            }
        }
        return abMap;
    }
}
