class Solution {
    public int videoStitching(int[][] clips, int T) {
        if (T == 0) return 0;
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int c1 = Integer.compare(o1[0], o2[0]);
                if (c1 != 0) return c1;
                return Integer.compare(o2[1], o1[1]);
            }
        });
        if (clips[0][0] != 0) return -1;
        int frontier = clips[0][1] + 1, i = 1, j;
        int count = 1;
        int candidate;
        while (frontier <= T) {
            if (i >= clips.length) return -1;
            candidate = i;
            if (clips[candidate][0] >= frontier) return -1;
            for (j = i; j < clips.length; ++j) {
                if (clips[j][0] >= frontier) break;
                if (clips[j][1] > clips[candidate][1]) {
                    candidate = j;
                }
            }
            i = j;
            frontier = clips[candidate][1] + 1;
            count++;
        }
        return count;
    }
}
