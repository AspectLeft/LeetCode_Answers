/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    private Map<Integer, UndirectedGraphNode> table = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        UndirectedGraphNode newNode = table.get(node.label);
        if (newNode != null) {
            return newNode;
        }
        newNode = new UndirectedGraphNode(node.label);
        table.put(node.label, newNode);
        for (UndirectedGraphNode next: node.neighbors) {
            newNode.neighbors.add(cloneGraph(next));
        }
        return newNode;
    }
}
