import java.util.*;

/*
 * @lc app=leetcode id=1727 lang=java
 *
 * [1727] Largest Submatrix With Rearrangements
 */

// @lc code=start
public class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] oneSticks = new int[m][n];
        oneSticks[0] = matrix[0];
        for (int i = 1; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == 1) {
                    oneSticks[i][j] = oneSticks[i - 1][j] + 1;
                }
            }
        }

        int answer = 0;

        for (int[] row: oneSticks) {
            Arrays.sort(row);
            for (int j = 0; j < n; ++j) {
                answer = Math.max(answer, row[j] * (n - j));
            }
        }

        return answer;
    }
}
// @lc code=end