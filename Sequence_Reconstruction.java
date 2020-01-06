class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        if (n == 1) {
            if (seqs.isEmpty()) return false;
            for (List<Integer> seq: seqs) {
                if (seq.size() > 1) return false;
            }
            for (List<Integer> seq: seqs) {
                if (!seq.isEmpty()) return true;
            }
            return false;
        }
        
        for (List<Integer> seq: seqs) {
            for (int v: seq) {
                if (!(1 <= v && v <= n)) return false;
            }
        }
        
        int[] indeg = new int[n + 1];
        HashSet<Integer>[] adjList = new HashSet[n + 1];
        for (int i = 1; i <= n; ++i) {
            adjList[i] = new HashSet<>();
        }
        for (List<Integer> seq: seqs) {
            for (int i = 1; i < seq.size(); ++i) {
                
                int prev = seq.get(i - 1);
                if (adjList[prev].contains(seq.get(i))) continue;
                adjList[prev].add(seq.get(i));
                indeg[seq.get(i)]++;
            }
        }
        List<Integer> frontier = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (indeg[i] == 0) frontier.add(i);
            if (frontier.size() > 1) {
                return false;
            }
        }
        if (frontier.size() != 1) return false;
        int index = 0;
        while (!frontier.isEmpty()) {
            if (frontier.size() > 1) return false;
            int v = frontier.get(0);
            frontier.clear();
            if (v != org[index]) return false;
            index++;
            for (int next: adjList[v]) {
                indeg[next]--;
                if (indeg[next] == 0) frontier.add(next);
                if (frontier.size() > 1) return false;
            }
        }
        return index == n;
    }
}
