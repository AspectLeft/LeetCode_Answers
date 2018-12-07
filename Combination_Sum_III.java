class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        return comb3(k, n, 0);
    }
    
    private List<List<Integer>> comb3(int k, int n, int lb) {
        List<Integer> comb;
        List<List<Integer>> output = new ArrayList<>();
        if (k == 1) {
            comb = new ArrayList<>();
            comb.add(n);
            if (n > lb)
                output.add(comb);
            return output;
        }
        
        for (int i = Math.max(lb + 1, n - (9 + 9 - k + 2) * (k - 1) / 2); (2 * i + k - 1) * k / 2 <= n && i < 10; ++i) {
            for (List<Integer> head: comb3(k - 1, n - i, i)) {
                head.add(i);
                output.add(head);
            }
        }
        return output;
    }
}
