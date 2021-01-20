import java.util.*;

/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
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

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] indeg = new int[n + 1];
        int[][] parent = new int[n + 1][2];

        init(n);
        int[] sp = {0, 0};
        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];


            indeg[v]++;
            if (parent[v][0] == 0) {
                parent[v][0] = u;
            }
            else {
                parent[v][1] = u;
                sp[0] = u;
                sp[1] = v;
            }

        }

        for (int[] edge: edges) {
            int u = edge[0], v = edge[1];
            if (u == sp[0] && v == sp[1]) continue;
            if (!union(u, v)) {
                if (sp[0] != 0) return new int[]{parent[sp[1]][0], sp[1]};
                else return edge;
            }
        }


        return sp;


    }

}
// @lc code=end

