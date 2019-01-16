class Solution {
    private boolean[] taken;
    private int counter = 0;
    private int N;
    
    private void dfs(int num) {
        for (int hole = 1; hole <= N; ++hole) {
            if (!taken[hole] && (num % hole == 0 || hole % num == 0)) {
                if (num == 1) {
                    counter++;
                }
                taken[hole] = true;
                dfs(num - 1);
                taken[hole] = false;
            }
        }
    }
    
    public int countArrangement(int N) {
        taken = new boolean[N + 1];
        this.N = N;
        dfs(N);
        
        return counter;
    }
}
