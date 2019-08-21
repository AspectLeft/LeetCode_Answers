class Solution {
    public String findLongestWord(String s, List<String> d) {
        if (d == null || d.size() == 0) return "";
        String result = "";
        for (String w: d) {
            if (w.length() < result.length()) continue;
            if (isSubseq(w, s)) {
                if (w.length() > result.length()
                   || w.compareTo(result) < 0) {
                    result = w;
                }
            }
        }
        return result;
    }
    
    private boolean isSubseq(String a, String b) {
        if (a.length() == 0) return true;
        int i = 0, j = 0;
        while (i < a.length()) {
            while (j < b.length() && a.charAt(i) != b.charAt(j)) j++;
            if (j == b.length()) return false;
            i++;
            j++;
        }
        return true;
    }
}
