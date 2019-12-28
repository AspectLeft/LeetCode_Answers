class SnakeGame {
    private int w, h;
    private int[][] food;
    int x = 0, y = 0;
    int i = 0;
    int score = 0;
    
    private boolean[][] occupied;
    LinkedList<int[]> snake = new LinkedList<>();
    
    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public SnakeGame(int width, int height, int[][] food) {
        this.w = width;
        this.h = height;
        this.food = food;
        
        this.occupied = new boolean[h][w];
        occupied[0][0] = true;
        snake.addFirst(new int[]{0, 0});
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        switch (direction) {
            case "U":
                x--;
                break;
            case "D":
                x++;
                break;
            case "L":
                y--;
                break;
            case "R":
                y++;
                break;
        }
        if (!(0 <= x && x < h && 0 <= y && y < w)) return -1;
        if (i < food.length && x == food[i][0] && y == food[i][1]) {
            occupied[x][y] = true;
            snake.addFirst(new int[]{x, y});
            score++;
            i++;
            return score;
        }
        int[] tail = snake.removeLast();
        occupied[tail[0]][tail[1]] = false;
        
        if (occupied[x][y]) {
            return -1;
        }
        occupied[x][y] = true;
        snake.addFirst(new int[]{x, y});
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
