class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) return 0;
        int length = triangle.size();
        if (length == 1) return triangle.get(0).get(0);
        int[] opt = new int[length];
        opt[0] = triangle.get(0).get(0);
        for (int i = 1; i < length; ++i) {
            List<Integer> line = triangle.get(i);
            int a = opt[0], b = opt[1];
            opt[0] += line.get(0);
            for (int j = 1; j < i; ++j) {
                opt[j] = line.get(j) + (a < b ? a : b);
                a = b;
                b = opt[j + 1];
            }
            opt[i] = a + line.get(i);
        }
        int m = opt[0];
        for (int p: opt)
            if (m > p)
                m = p;
        return m;
    }
}
