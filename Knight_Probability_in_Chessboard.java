class Solution {
    private static final int[][] DIRS = new int[][] {
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1, 2},
        {1, -2}, {1, 2},
        {2, -1}, {2, 1}
    };
    
    private boolean inBound(int x, int y, int N) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
    
    public double knightProbability(int N, int K, int r, int c) {
        double[][] current = new double[N][N];
        double[][] next = new double[N][N];
        current[r][c] = 1.0;
        double sum = 1.0;
        for (int i = 0; i < K; ++i) {
            sum = 0.0;
            for (int x = 0; x < N; ++x) {
                for (int y = 0; y < N; ++y) {
                    next[x][y] = 0.0;
                    for (int[] dir: DIRS) {
                        if (!inBound(x + dir[0], y + dir[1], N)) continue;
                        next[x][y] += current[x + dir[0]][y + dir[1]] / 8;
                    }
                    sum += next[x][y];
                }
            }
            double[][] tmp = next;
            next = current;
            current = tmp;
        }
        
        return sum;
    }
}
