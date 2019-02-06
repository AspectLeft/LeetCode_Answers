class Solution {
    private void reverseRange(char[] str, int l, int r) {
        while (l < r) {
            char tmp = str[l];
            str[l] = str[r];
            str[r] = tmp;
            
            l++;
            r--;
        }
    }
    
    public void reverseWords(char[] str) {
        if (str == null || str.length == 0) return;
        reverseRange(str, 0, str.length - 1);
        int l = 0, r;
        while (l < str.length) {
            while (l < str.length && str[l] == ' ') l++;
            if (l >= str.length) return;
            r = l;
            while (r < str.length && str[r] != ' ') r++;
            reverseRange(str, l, r - 1);
            l = r;
        }
    }
}
