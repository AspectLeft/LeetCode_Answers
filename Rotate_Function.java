class Solution {
    // F(k + 1) = F(k) + S - n * B_k[n - 1]
    // B_k[i] = A[(i + n - k) % n]
    public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) return 0;
        int S = 0;
        for (int v: A) S += v;
        int n = A.length;
        int F = 0;
        for (int i = 0; i < n; ++i)
            F += i * A[i];
        int max = F;
        for (int k = 0; k < n - 1; ++k) {
            F += S - n * A[n - 1 - k];
            if (F > max) max = F;
        }
        return max;
    }
}
