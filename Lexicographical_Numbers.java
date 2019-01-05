class Solution {
    private List<Integer> output;
    private int n;
    
    private void dfs(int v) {
        if (v <= n) output.add(v);
        else return;
        v *= 10;
        for (int i = 0; i <= 9; ++i)
            dfs(v + i);
    }
    
    public List<Integer> lexicalOrder(int n) {
        output = new ArrayList<>();
        this.n = n;
        for (int v = 1; v <= 9; ++v)
            dfs(v);
        return output;
    }
}
