class Solution {
    
    public List<List<Integer>> combine(int n, int k) {
        if (n < k || k < 0 || n <= 0) return new ArrayList<>();
        if (k == 0) return Collections.singletonList(new ArrayList<>());
        if (n == k) {
            List<Integer> list = new ArrayList<>(n);
            for (int i = 1; i <= n; ++i) list.add(i);
            return Collections.singletonList(list);
        }
        List<List<Integer>> c1 = combine(n - 1, k), c2 = combine(n - 1, k - 1);
        List<List<Integer>> result = new ArrayList<>(c1);
        for (List<Integer> list: c2) {
            list.add(n);
            result.add(list);
        }
        return result;
    }
}
