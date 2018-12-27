class Solution {
    
    Map<String, List<String>> adjList = new HashMap<>();
    Map<String, boolean[]> visited = new HashMap<>();
    
    private int V;
    private List<String> output = null;
    
    private void dfs(String u, LinkedList<String> path) {
        if (output != null) return;
        path.addLast(u);
        if (path.size() == V + 1) {
            output = new LinkedList<String>(path);
        }
        List<String> list = adjList.get(u);
        boolean[] vi = visited.get(u);
        if (list == null) {
            path.removeLast();
            return;
        }
        for (int i = 0; i < list.size(); ++i) {
            if (!vi[i]) {
                vi[i] = true;
                dfs(list.get(i), path);
                vi[i] = false;
            }
        }
        path.removeLast();
    }
    
    public List<String> findItinerary(String[][] tickets) {
        V = tickets.length;
        for (String[] edge: tickets) {
            List<String> list = adjList.get(edge[0]);
            if (list == null) {
                list = new ArrayList<>();
                list.add(edge[1]);
                adjList.put(edge[0], list);
            }
            else {
                list.add(edge[1]);
            }
        }
        for (List<String> list: adjList.values()) {
            Collections.sort(list);
        }
        for (Map.Entry<String, List<String>> entry: adjList.entrySet()) {
            visited.put(entry.getKey(), new boolean[entry.getValue().size()]);
        }
        LinkedList<String> path = new LinkedList<>();
        dfs("JFK", path);
        return output;
    }
}
