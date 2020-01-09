class Solution {
    public int findLonelyPixel(char[][] picture) {
        if (picture == null || picture.length == 0 || picture[0].length == 0)
            return 0;
        int m = picture.length;
        int n = picture[0].length;
        int count = 0;
        for (int i = 0; i < m; ++i) {
            int jj = -1;
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] == 'B') {
                    if (jj == -1) jj = j;
                    else {
                        jj = -1;
                        break;
                    }
                }
            }
            if (jj == -1) continue;
            boolean longly = true;
            for (int ii = 0; ii < m; ++ii) {
                if (ii == i) continue;
                if (picture[ii][jj] == 'B') {
                    longly = false;
                    break;
                }
            }
            if (longly) count++;
        }
        return count;
    }
}
