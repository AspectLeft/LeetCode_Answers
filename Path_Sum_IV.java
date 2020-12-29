class Solution {
    class TreeMetric {
        int pathSum;
        int pathCount;
    }
    
    
    public int pathSum(int[] nums) {
        int[] tree = new int[16];
        Arrays.fill(tree, -1);
        for (int num: nums) {
            tree[(1 << (num / 100 - 1)) + ((num % 100) / 10 - 1)] = num % 10;
        }
        // return findMetric(tree, 1).pathSum;
        return dfs(tree, 1, 0);
    }
    
    private boolean isNull(int[] tree, int i) {
        return i >= tree.length || tree[i] == -1;
    }
    
    private int dfs(int[] tree, int i, int prevSum) {
        if (isNull(tree, i)) return 0;
        if (isNull(tree, i << 1) && isNull(tree, (i << 1) + 1)) return prevSum + tree[i];
        return dfs(tree, i << 1, prevSum + tree[i]) + dfs(tree, (i << 1) + 1, prevSum + tree[i]);
    }
    
    
    private TreeMetric findMetric(int[] tree, int i) {
        if (i >= tree.length) return null;
        if (tree[i] == -1) return null;
        TreeMetric treeMetric = new TreeMetric();
        
        TreeMetric left = findMetric(tree, i << 1);
        TreeMetric right = findMetric(tree, (i << 1) + 1);
        if (left == null && right == null) {
            treeMetric.pathSum = tree[i];
            treeMetric.pathCount = 1;
            return treeMetric;
        }
        if (left != null && right != null) {
            treeMetric.pathSum = left.pathSum + left.pathCount * tree[i]
                + right.pathSum + right.pathCount * tree[i];
            treeMetric.pathCount = left.pathCount + right.pathCount;
            return treeMetric;
        }
        if (left != null) {
            right = left;
        }
        treeMetric.pathSum = right.pathSum + right.pathCount * tree[i];
        treeMetric.pathCount = right.pathCount;
        return treeMetric;
        
    }
}
