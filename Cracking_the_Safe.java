class Solution {
    int n, k;
    
    public String crackSafe(int n, int k) {
        this.n = n;
        this.k = k;
        if (n == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < k; ++i) builder.append(i);
            return builder.toString();
        }
        if (k == 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; ++i) builder.append('0');
            return builder.toString();
        }
        StringBuilder builder = new StringBuilder();
        Set<String> set = new HashSet<>();
        int target = 1;
        for (int i = 0; i < n; ++i) target *= k;
        target += n - 1;
        for (int i = 0; i < n; ++i) builder.append('0');
        set.add(builder.toString());
        dfs(builder, set, target);
        return builder.toString();
    }
    
    
    private boolean dfs(StringBuilder builder, Set<String> set, int target) {
        if (builder.length() == target) return true;
        for (int i = 0; i < k; ++i) {
            builder.append(i);
            String tail = builder.substring(builder.length() - n);
            if (set.contains(tail)) {
                builder.deleteCharAt(builder.length() - 1);
                continue;
            }
            else {
                set.add(tail);
                if (dfs(builder, set, target)) {
                    return true;
                }
                else {
                    set.remove(tail);
                    builder.deleteCharAt(builder.length() - 1);
                }
            }
        }
        return false;
    }
    
    
}
