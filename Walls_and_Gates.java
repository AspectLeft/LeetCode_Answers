class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        List<int[]> frontier = new ArrayList<>();
        int m = rooms.length;
        int n = rooms[0].length;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (rooms[i][j] == 0) {
                    frontier.add(new int[]{i, j});
                }
            }
        }
        while (frontier.size() > 0) {
            List<int[]> nextFrontier = new ArrayList<>();
            int x, y;
            for (int[] pair: frontier) {
                x = pair[0];
                y = pair[1];
                if (x > 0 && rooms[x - 1][y] == Integer.MAX_VALUE) {
                    rooms[x - 1][y] = rooms[x][y] + 1;
                    nextFrontier.add(new int[]{x - 1, y});
                }
                if (x < m - 1 && rooms[x + 1][y] == Integer.MAX_VALUE) {
                    rooms[x + 1][y] = rooms[x][y] + 1;
                    nextFrontier.add(new int[]{x + 1, y});
                }
                if (y > 0 && rooms[x][y - 1] == Integer.MAX_VALUE) {
                    rooms[x][y - 1] = rooms[x][y] + 1;
                    nextFrontier.add(new int[]{x, y - 1});
                }
                if (y < n - 1 && rooms[x][y + 1] == Integer.MAX_VALUE) {
                    rooms[x][y + 1] = rooms[x][y] + 1;
                    nextFrontier.add(new int[]{x, y + 1});
                }
            }
            frontier = nextFrontier;
        }
    }
}
