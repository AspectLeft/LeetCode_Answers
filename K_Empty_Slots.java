import java.util.Stack;

/*
 * @lc app=leetcode id=683 lang=java
 *
 * [683] K Empty Slots
 */

// @lc code=start
class Solution {
    public int kEmptySlots(int[] bulbs, int k) {
        // TreeSet<Integer> map = new TreeSet<>();
        // for (int i = 0; i < bulbs.length; ++i) {
        //     int mid = bulbs[i];
        //     map.add(mid);
        //     Integer l = map.lower(mid);
        //     Integer r = map.higher(mid);
        //     if (l != null) {
        //         if (mid - l - 1 == k) {
        //             return i + 1;
        //         }
        //     }
        //     if (r != null) {
        //         if (r - mid - 1 == k) {
        //             return i + 1;
        //         }
        //     }
        // }
        // return -1;

        int n = bulbs.length;
        int[] days = new int[n];
        for (int i = 0; i < n; ++i) {
            days[bulbs[i] - 1] = i + 1;
        }

        // Monotonous stack
        Stack<Integer> stack = new Stack<>();
        int ans = n + 1;

        for (int x = 0; x < n; ++x) {
            while (!stack.isEmpty() && days[stack.peek()] > days[x]) {
                stack.pop();
            }
            if (!stack.isEmpty() && x - stack.peek() - 1 == k) {
                ans = Math.min(ans, days[x]);
            }
            stack.push(x);
        }

        stack.clear();
        for (int x = n - 1; x >= 0; --x) {
            while (!stack.isEmpty() && days[stack.peek()] > days[x]) {
                stack.pop();
            }
            if (!stack.isEmpty() && stack.peek() - x - 1 == k) {
                ans = Math.min(ans, days[x]);
            }
            stack.push(x);
        }

        return ans == n + 1 ? -1 : ans;
    }
}
// @lc code=end

