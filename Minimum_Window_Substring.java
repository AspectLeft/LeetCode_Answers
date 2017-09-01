class Solution {

    public String minWindow(String s, String t) {
        if (t.equals("") || s.equals("")) return "";
        int[] table = new int[256];
        boolean[] valid = new boolean[256];
        Arrays.fill(valid, false);
        int tLength = t.length();
        char c;
        for (int i = 0; i < tLength; ++i) {
            c = t.charAt(i);
            table[c] += 1;
            valid[c] = true;
        }
        int sLength = s.length();
        int l = 0, r= -1;
        int result_l = 0, result_r = sLength;
        int sum = tLength;
        while (r < sLength && l < sLength) {
            if (sum == 0) {
                if (r - l < result_r - result_l) {
                    result_l = l;
                    result_r = r;
                }
                c = s.charAt(l);
                if (valid[c]) {
                    if (table[c]++ == 0) {
                        sum++;
                    }
                }
                l++;
                if (l > r) r = l;
            }
            else {
                r++;
                if (r >= sLength) break;
                c = s.charAt(r);
                if (valid[c]) {
                    if (table[c]-- > 0) {
                        sum--;
                    }
                }
            }


/*
            System.out.println(sum);
            System.out.print(l);
            System.out.print(", ");
            System.out.println(r);*/
        }

        if (result_r == sLength) return "";
        return s.substring(result_l, result_r + 1);
    }

}
