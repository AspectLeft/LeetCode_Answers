/**
 * // This is the Master's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface Master {
 *     public int guess(String word) {}
 * }
 */
class Solution {
    int[][] diffs;
    int n;
    
    public void findSecretWord(String[] wordlist, Master master) {
        n = wordlist.length;
        if (n == 1) {
            master.guess(wordlist[0]);
            return;
        }
        diffs = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                diffs[i][j] = diff(wordlist[i], wordlist[j]);
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) set.add(i);
        int tid, d;
        for (int c = 0; c < 9; ++c) {
            tid = nextTester(set, wordlist);
            //System.out.println(tid);
            d = 6 - master.guess(wordlist[tid]);
            if (d == 0) return;
            Set<Integer> newSet = new HashSet<>();
            for (int id: set) {
                if (diffs[id][tid] == d) {
                    newSet.add(id);
                }
            }
            set = newSet;
        }
        for (int id: set) {
            master.guess(wordlist[id]);
            return;
        }
        return;
    }
    
    private int nextTester(Set<Integer> set, String[] wordlist) {
        int id = -1, min = n, e;
        for (int i = 0; i < n; ++i) {
            e = effect(set, i);
            if (e < min) {
                min = e;
                id = i;
            }
        }
        return id;
    }
    
    private int effect(Set<Integer> set, int tid) {
        int[] buckets = new int[7];
        for (int id: set) {
            buckets[diffs[id][tid]]++;
        }
        int e = 0;
        for (int b: buckets) {
            if (b > e) e = b;
        }
        return e;
    }
    
    private int diff(String a, String b) {
        int d = 0;
        for (int i = 0; i < 6; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                d++;
            }
        }
        return d;
    }
}
