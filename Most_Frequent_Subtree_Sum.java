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
    Map<Integer, Integer> map;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[]{};
        map = new HashMap<>();
        updateSum(root);
        int maxFreq = 1;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > maxFreq) maxFreq = entry.getValue();
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == maxFreq) {
                result.add(entry.getKey());
            }
        }
        int[] output = new int[result.size()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = result.get(i);
        }
        return output;
    }
    
    private int updateSum(TreeNode root) {
        int sum = root.val;
        if (root.left != null) sum += updateSum(root.left);
        if (root.right != null) sum += updateSum(root.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}
