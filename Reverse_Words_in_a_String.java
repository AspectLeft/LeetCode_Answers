public class Solution {
    private void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    private void reverse(char[] a, int l, int r) {
        for (int i = l, j = r; i < j; ++i, --j) 
            swap(a, i, j);
    }
    
    private void move(char[] a, int l, int r, int tail) {
        for (int i = 0; i < r - l; ++i) {
            a[tail + i] = a[l + i];
        }
    }
    
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] a = s.toCharArray();
        int tail = 0, l = 0, r;
        while (l < a.length) {
            while (l < a.length && a[l] == ' ') l++;
            r = l;
            while (r < a.length && a[r] != ' ') r++;
            if (l < a.length) {
                reverse(a, l, r - 1);
                move(a, l, r, tail);
                tail += (r - l);
                if (tail < a.length) a[tail] = ' ';
                tail++;
                l = r;
            }
        }
        if (tail == 0) return "";
        reverse(a, 0, tail - 2);
        return new String(a, 0, tail - 1);
    }
}
