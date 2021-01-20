/*
 * @lc app=leetcode id=684 lang=java
 *
 * [684] Redundant Connection
 */

// @lc code=start
class Solution {
    int n;
    int[] rank;
    int[] parent;
    int count;

    private void init(int n) {
        this.n = n;
        this.count = n;
        rank = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parent[i] = i;
        }
    }

    
    private int find(int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    
    private boolean union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i == j) return false;
        
        if (rank[i] > rank[j]) {
            parent[j] = i;
        }
        else if (rank[i] < rank[j]) {
            parent[i] = j;
        }
        else {
            parent[j] = i;
            rank[i]++;
        }
        count--;
        return true;
    }


    public int[] findRedundantConnection(int[][] edges) {
        init(edges.length);

        for (int[] edge: edges) {
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }

        return null;
    }
}
// @lc code=end

