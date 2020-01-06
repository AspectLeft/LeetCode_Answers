class Solution {
    private int sign(int v) {
        if (v > 0) return 1;
        if (v < 0) return -1;
        return 0;
    }
    
    class Edge {
        int dx;
        int dy;
        
        Edge(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }
    
    private boolean counterclock(List<Edge> edges) {
        Edge prev = edges.get(edges.size() - 1);
        for (Edge edge: edges) {
            if (prev.dx * edge.dy - prev.dy * edge.dx < 0) return false;
            prev = edge;
        }
        return true;
    }
    
    private boolean clock(List<Edge> edges) {
        Edge prev = edges.get(edges.size() - 1);
        for (Edge edge: edges) {
            if (prev.dx * edge.dy - prev.dy * edge.dx > 0) return false;
            prev = edge;
        }
        return true;
    }
    
    public boolean isConvex(List<List<Integer>> points) {
        List<Edge> edges = new ArrayList<>();
        List<Integer> prev = points.get(points.size() - 1);
        for (List<Integer> point: points) {
            edges.add(new Edge(point.get(0) - prev.get(0), 
                               point.get(1) - prev.get(1)));
            prev = point;
        }
        return clock(edges) || counterclock(edges);
    }
}
