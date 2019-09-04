class Solution {
    private static final int[][] dirs = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    
    
    public boolean isRobotBounded(String instructions) {
        int[] pos = new int[]{0, 0};
        int dir = 0;
        
        
        for (int i = 0; i < instructions.length(); ++i) {
            switch (instructions.charAt(i)) {
                case 'G':
                    pos[0] += dirs[dir][0];
                    pos[1] += dirs[dir][1];
                    break;
                case 'L':
                    dir = (dir + 1) % 4;
                    break;
                case 'R':
                    dir = (dir + 3) % 4;
                    break;
            }
        }
        
        return (pos[0] == 0 && pos[1] == 0) || dir != 0;
    }
}
