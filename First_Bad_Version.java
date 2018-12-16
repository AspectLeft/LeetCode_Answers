/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    private int f1(int n) {
        
        int i, step;
        for (i = 1, step = 1; i <= n; i += step, step++) {
            if (isBadVersion(i)) {
                for (int j = i - step + 1; j <= i; ++j)
                    if (isBadVersion(j)) return j;
            }
        }
        for (int j = i - step + 1; j <= n; ++j) {
            if (isBadVersion(j))
                return j;
        }
        return n;
    }
    
    private int f2(int l, int r) {
        if (l == r) return l;
        int mid = l + (r - l) / 2;
        if (isBadVersion(mid))
            return f2(l, mid);
        return f2(mid + 1, r);
    }
    
    
    public int firstBadVersion(int n) {
        if (n == 1) return 1;
        double d = Math.log(n) / Math.log(Math.E);
        d = d * d;
        int k = (int) d;
        if (isBadVersion(k))
            return f1(n);
        return f2(1, n);
    }
}
