class Solution {
    class Match {
        boolean isLeaf = false;
        int v;
        Match l, r;
        
        Match(int v) {
            this.isLeaf = true;
            this.v = v;
            this.l = this.r = null;
        }
        
        Match(Match l, Match r) {
            this.v = l.v;
            this.l = l;
            this.r = r;
        }
        
        
    }
    
    private void matchToString(StringBuilder builder, Match match) {
        if (match.isLeaf) {
            builder.append(match.v);
            return;
        }
        builder.append('(');
        matchToString(builder, match.l);
        builder.append(',');
        matchToString(builder, match.r);
        builder.append(')');
    }
    
    
    public String findContestMatch(int n) {
        Match[] matches = new Match[n + 1];
        for (int i = 1; i <= n; ++i) {
            matches[i] = new Match(i);
        }
        for (int range = n; range > 1; range /= 2) {
            for (int i = 1; i <= range / 2; ++i) {
                matches[i] = new Match(matches[i], matches[range + 1 - i]);
            }
        }
        
        StringBuilder builder = new StringBuilder();
        matchToString(builder, matches[1]);
        return builder.toString();
    }
}
