class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        int c1 = A[0], c2 = B[0];
        boolean v1 = true, v2 = true;
        for (int i = 1; i < A.length; ++i) {
            if (v1) {
                if (A[i] != c1 && B[i] != c1) {
                    v1 = false;
                }
            }
            if (v2) {
                if (A[i] != c2 && B[i] != c2) {
                    v2 = false;
                }
            }
            if (!v1 && !v2) return -1;
        }
        int min = A.length;
        if (v1) {
            int count = 0;
            for (int a: A) if (a != c1) count++;
            if (count < min) min = count;
            
            count = 0;
            for (int b: B) if (b != c1) count++;
            if (count < min) min = count;
        }
        if (v2) {
            int count = 0;
            for (int a: A) if (a != c2) count++;
            if (count < min) min = count;
            
            count = 0;
            for (int b: B) if (b != c2) count++;
            if (count < min) min = count;
        }
        return min;
    }
}
