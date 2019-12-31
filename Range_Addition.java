class Solution {
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] start = new int[length];
        int[] end = new int[length];
        for (int[] update: updates) {
            start[update[0]] += update[2];
            end[update[1]] += update[2];
        }
        int[] result = new int[length];
        result[0] = start[0];
        for (int i = 1; i < length; ++i) {
            result[i] = result[i - 1] + start[i] - end[i - 1];
        }
        return result;
    }
}
