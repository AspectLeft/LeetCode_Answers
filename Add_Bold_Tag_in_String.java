class Solution {
    public String addBoldTag(String s, String[] dict) {
        if (s == null || s.isEmpty() || dict.length == 0) return s;
        boolean[] bold = new boolean[s.length()];
        for (String word: dict) {
            int start = s.indexOf(word, 0);
            while (start >= 0 && start < s.length()) {
                if (start >= 0) {
                    for (int i = start; i < start + word.length(); ++i) {
                        bold[i] = true;
                    }
                }
                start++;
                start = s.indexOf(word, start);
            }
        }
        StringBuilder builder = new StringBuilder();
        int i = 0;
        boolean inBold = false;
        while (i < s.length()) {
            if (bold[i]) {
                if (!inBold) {
                    builder.append("<b>");
                }
                inBold = true;
            }
            else {
                if (inBold) {
                    builder.append("</b>");
                }
                inBold = false;
                
            }
            builder.append(s.charAt(i));
            i++;
        }
        if (inBold) {
            builder.append("</b>");
        }
        return builder.toString();
    }
}
