/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    private int encode(int x, int y) {
        return 100000 * x + y;
    }
    
    private Set<Integer> visited = new HashSet<>();
    
    public void cleanRoom(Robot robot) {
        dfs(robot, 0, 0);
    }
    
    private void turnAround(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
    }
    
    private void dfs(Robot robot, int x, int y) {
        visited.add(encode(x, y));
        robot.clean();
        
        if (!visited.contains(encode(x - 1, y)) && robot.move()) {
            dfs(robot, x - 1, y);
            turnAround(robot);
            robot.move();
            turnAround(robot);
        }
        
        robot.turnLeft();
        if (!visited.contains(encode(x, y - 1)) && robot.move()) {
            robot.turnRight();
            dfs(robot, x, y - 1);
            robot.turnRight();
            robot.move();
            turnAround(robot);
        }
        
        robot.turnLeft();
        if (!visited.contains(encode(x + 1, y)) && robot.move()) {
            turnAround(robot);
            dfs(robot, x + 1, y);
            robot.move();
            turnAround(robot);
        }
        
        robot.turnLeft();
        if (!visited.contains(encode(x, y + 1)) && robot.move()) {
            robot.turnLeft();
            dfs(robot, x, y + 1);
            robot.turnLeft();
            robot.move();
            turnAround(robot);
        }
        
        robot.turnLeft();
    }
}
