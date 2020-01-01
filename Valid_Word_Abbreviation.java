class Solution {
    private boolean isDigit(char c) {
        return 0 <= c && c <= '9';
    }
    
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while (i < abbr.length()) {
            while (i < abbr.length() && !isDigit(abbr.charAt(i))) {
                if (j >= word.length()) {
                    // System.out.println(1);
                    return false;
                }
                if (abbr.charAt(i) != word.charAt(j)) {
                    
//                     System.out.println(abbr.charAt(i));
//                     System.out.println(word.charAt(j));
                    return false;
                }
                i++;
                j++;
            }
            if (i == abbr.length()) break;
            int num = 0;
            if (i < abbr.length() && abbr.charAt(i) == '0') return false;
            while (i < abbr.length() && isDigit(abbr.charAt(i))) {
                num *= 10;
                num += (abbr.charAt(i) - '0');
                i++;
            }
            // System.out.println(num);
            j += num;
        }
        // System.out.println(j);
        return j == word.length();
    }
}
