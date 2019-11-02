class Solution {
    public double new21Game(int N, int K, int W) {
        if (N >= K + W - 1) return 1.0;
        if (K == 0) {
            return 1.0;
        }
        double[] p = new double[N + 1];
        p[0] = 1.0;
        int n;
        for (n = 1; n <= K; ++n) {
            /*
            for (int w = 1; w <= W && w <= n; ++w) {
                p[n] += p[n - w];
            }
            p[n] /= W;
            */
            if (n == 1) {
                p[n] = 1.0 / W;
                continue;
            }
            
            p[n] = p[n - 1] * W;
            p[n] += p[n - 1];
            if (W <= n - 1) {
                p[n] -= p[n - W - 1];
            }
            p[n] /= W;
            
            //System.out.println(p[n]);
        }
        double result = p[K];
        //System.out.println(result);
        for (; n <= N; ++n) {
            /*
            for (int w = n - K + 1; w <= W && w <= n; ++w) {
                p[n] += p[n - w];
            }
            p[n] /= W;
            */
            
            p[n] = p[n - 1] * W;
            if (W <= n - 1) {
                p[n] -= p[n - W - 1];
            }
            p[n] /= W;
            
            result += p[n];
            //System.out.println(result);
        }
        return result;
    }
}
