/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n == 1) return 0;
        Stack<Integer> todo = new Stack<>();
        for (int i = 0; i < n; ++i) {
            todo.push(i);
        }
        while (todo.size() >= 2) {
            int a = todo.pop();
            int b = todo.pop();
            if (knows(a, b)) {
                todo.push(b);
            }
            else {
                todo.push(a);
            }
        }
        return verify(todo.pop(), n);
    }
    
    private int verify(int a, int n) {
        for (int b = 0; b < n; ++b) {
            if (a == b) continue;
            if (knows(a, b)) return -1;
            if (!knows(b, a)) return -1;
        }
        return a;
    }
}
