class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDeg = new int[numCourses];
        Stack<Integer> topNodes = new Stack<>();
        List<List<Integer>> adjList = new ArrayList<>();
        int edgeCnt = prerequisites.length;
        for (int i = 0; i < numCourses; ++i) {
            adjList.add(new ArrayList<>());
        }
        for (int[] pair: prerequisites) {
            adjList.get(pair[1]).add(pair[0]);
            inDeg[pair[0]]++;
        }
        for (int i = 0; i < numCourses; ++i) {
            if (inDeg[i] == 0) topNodes.add(i);
        }
        int node;
        while (!topNodes.empty()) {
            node = topNodes.pop();
            for (int next: adjList.get(node)) {
                inDeg[next]--;
                edgeCnt--;
                if (inDeg[next] == 0)
                    topNodes.push(next);
            }
        }
        return edgeCnt == 0;
    }
}
