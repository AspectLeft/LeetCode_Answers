/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    private int index;
    private Stack<NestedInteger> stack;
    private char[] arr;
    
    private int parseInt() {
        boolean sign = true;
        if (arr[index] == '-') {
            sign = false;
            index++;
        }
        int v = 0;
        while (index < arr.length && arr[index] >= '0' && arr[index] <= '9') {
            v = v * 10 - (arr[index] - '0');
            index++;
        }
        if (sign) v = -v;
        return v;
    }
    
    
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        index = 0;
        stack = new Stack<>();
        arr = s.toCharArray();
        if (arr[0] != '[') {
            return new NestedInteger(parseInt());
        }
        NestedInteger ni = null;
        while (index < arr.length) {
            if (arr[index] == '[') {
                stack.push(new NestedInteger());
                index++;
            }
            else if (arr[index] == '-' || arr[index] >= '0' && arr[index] <= '9') {
                stack.peek().add(new NestedInteger(parseInt()));
            }
            else if (arr[index] == ']') {
                ni = stack.pop();
                if (!stack.empty()) stack.peek().add(ni);
                index++;
            }
            else {
                index++;
            }
        }
        return ni;
    }
}
