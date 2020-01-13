class Solution {
    private int dist(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }
    
    
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        if (nuts.length == 0) return 0;
        int base = 0;
        for (int[] nut: nuts) {
            base += 2 * dist(nut, tree);
        }
        
        int result = Integer.MAX_VALUE;
        for (int[] nut: nuts) {
            result = Math.min(result, base - dist(nut, tree) + dist(nut, squirrel));
        }
        
        return result;
    }
}
