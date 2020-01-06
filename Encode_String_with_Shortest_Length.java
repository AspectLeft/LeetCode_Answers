class Solution {
    private Integer getAtom(String s) {
        for (int l = 1; l <= s.length() / 2; ++l) {
            if (isAtom(s, s.substring(0, l))) return l;
        }
        return null;
    }
    
    private boolean isAtom(String s, String atom) {
        if (s.length() % atom.length() != 0) return false;
        for (int i = 0; i < s.length(); i += atom.length()) {
            if (!atom.equals(s.substring(i, i + atom.length()))) return false;
        }
        return true;
    }
    
    public String encode(String s) {
        int n = s.length();
        String[][] opt = new String[n][n];
        for (int i = 0; i < n; ++i) {
            opt[i][i] = s.substring(i, i + 1);
        }
        
        for (int w = 2; w <= n; ++w) {
            for (int i = 0; i + w <= n; ++i) {
                int j = i + w - 1;
                String sub = s.substring(i, j + 1);
                
                opt[i][j] = sub;
                
                int bestMid = j;
                int bestL = opt[i][j].length();
                for (int mid = i; mid < j; ++mid) {
                    int l = opt[i][mid].length() + opt[mid + 1][j].length();
                    if (l < bestL) {
                        bestMid = mid;
                        bestL = l;
                    }
                }
                if (bestMid != j) {
                    opt[i][j] = opt[i][bestMid] + opt[bestMid + 1][j];
                }
                
                
                
                Integer l = getAtom(sub);
                if (l == null) continue;
                int k = sub.length() / l;
                int encodedL = opt[i][i + l - 1].length();
                if (String.valueOf(k).length() + 2 + encodedL < opt[i][j].length()) {
                    opt[i][j] = String.format("%d[%s]", k, opt[i][i + l - 1]);
                }
            }
        }
        
        return opt[0][n - 1];
    }
}
