class Solution {
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) return 0;
        if (chars.length == 1) return 1;
        int i = 0, j = 0, k, length;
        int n = chars.length;
        char pivot;
        while (i < n) {
            pivot = chars[i];
            length = 0;
            while (i < n && chars[i] == pivot) {
                i++;
                length++;
            }
            chars[j] = pivot;
            j++;
            if (length > 1) {
                for (char c: String.valueOf(length).toCharArray()) {
                    chars[j] = c;
                    j++;
                }
            }
        }
        return j;
    }
}
