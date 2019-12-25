/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    class NodeWithOffset {
        TreeNode node;
        int offset;
        
        NodeWithOffset(TreeNode node, int offset) {
            this.node = node;
            this.offset = offset;
        }
    }
    
    class ListWithOffset {
        List<Integer> list;
        int offset;
        
        ListWithOffset(List<Integer> list, int offset) {
            this.list = list;
            this.offset = offset;
        }
    }
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Map<Integer, ListWithOffset> map = new HashMap<>();
        LinkedList<ListWithOffset> results = new LinkedList<>();
        Queue<NodeWithOffset> queue = new LinkedList<>();
        queue.add(new NodeWithOffset(root, 0));
        while (!queue.isEmpty()) {
            NodeWithOffset nWO = queue.remove();
            if (nWO.node.left != null) {
                queue.add(new NodeWithOffset(nWO.node.left, nWO.offset - 1));
            }
            if (nWO.node.right != null) {
                queue.add(new NodeWithOffset(nWO.node.right, nWO.offset + 1));
            }
            ListWithOffset lWO = map.get(nWO.offset);
            if (lWO == null) {
                lWO = new ListWithOffset(new ArrayList<>(), nWO.offset);
                if (!results.isEmpty() && lWO.offset < results.getFirst().offset) {
                    results.addFirst(lWO);
                }
                else {
                    results.addLast(lWO);
                }
                map.put(nWO.offset, lWO);
            }
            lWO.list.add(nWO.node.val);
        }
        List<List<Integer>> output = new ArrayList<>();
        for (ListWithOffset lWO: results) {
            output.add(lWO.list);
        }
        return output;
    }
}
