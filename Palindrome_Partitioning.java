class Solution {
    private boolean[][] isPalindrome;
    
    public List<List<String>> partition(String s) {
        if (s == null || s.equals("")) return new ArrayList<>();
        
        isPalindrome = new boolean[s.length()][s.length()];
        for (int sum = 0; sum <= 2 * (s.length() - 1); ++sum) {
            int l = sum / 2, r = (sum + 1) / 2;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                isPalindrome[l][r] = true;
                l--;
                r++;
            }
        }
        
        return partition(s.toCharArray(), s.length() - 1);
        
    }
    
    private List<List<String>> partition(char[] a, int r) {
        if (r == 0) {
            List<List<String>> result = new ArrayList<>();
            result.add(new ArrayList<String>(){{add(new String(a, 0, 1));}});
            return result;
        }
        List<List<String>> result = new ArrayList<>();
        for (int i = r; i > 0; --i) {
            if (isPalindrome[i][r]) {
                List<List<String>> sub = partition(a, i - 1);
                String tail = new String(a, i, r - i + 1);
                for (List<String> l: sub) {
                    l.add(tail);
                    result.add(l);
                }
            }
        }
        if (isPalindrome[0][r])
            result.add(new ArrayList<String>(){{add(new String(a, 0, r + 1));}});
        return result;
    }
}
