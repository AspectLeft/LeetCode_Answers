class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indeg = new int[numCourses];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.add(new ArrayList<>());
        }
        int u, v;
        for (int[] edge: prerequisites) {
            u = edge[1];
            v = edge[0];
            indeg[v]++;
            adjList.get(u).add(v);
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) stack.add(i);
        }
        int index = 0;
        int[] output = new int[numCourses];
        List<Integer> adjs;
        while (!stack.empty()) {
            u = stack.pop();
            output[index] = u;
            index++;
            adjs = adjList.get(u);
            for (int k: adjs) {
                indeg[k]--;
                if (indeg[k] == 0)
                    stack.push(k);
            }
        }
        if (index < numCourses) return new int[0];
        return output;
    }
}
