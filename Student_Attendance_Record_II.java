class Solution {
    private static final int m = 1000000000 + 7;
    
    public int checkRecord(int n) {
        if (n == 0) return 0;
        if (n == 1) return 3;
        // end without L, with no A
        int dp1 = 1;
        
        // end without L, with 1 A
        int dp2 = 1;
        
        // end with 1 L, with no A
        int dp3 = 1;
        
        // end with 1 L, with a A;
        int dp4 = 0;
        
        // end with 2 L, with no A
        int dp5 = 0;
        
        // end with 2 L, with a A
        int dp6 = 0;
        
        int t1, t2, t3, t4, t5, t6;
        for (int i = 1; i < n; ++i) {
            t1 = (dp1 + dp3) % m;
            t1 = (t1 + dp5) % m;
            
            t2 = (dp1 + dp2) % m;
            t2 = (t2 + dp3) % m;
            t2 = (t2 + dp4) % m;
            t2 = (t2 + dp5) % m;
            t2 = (t2 + dp6) % m;
            
            t3 = dp1;
            
            t4 = dp2;
            
            t5 = dp3;
            
            t6 = dp4;
            
            dp1 = t1;
            dp2 = t2;
            dp3 = t3;
            dp4 = t4;
            dp5 = t5;
            dp6 = t6;
        }
        int result = (dp1 + dp2) % m;
        result = (result + dp3) % m;
        result = (result + dp4) % m;
        result = (result + dp5) % m;
        result = (result + dp6) % m;
        return result;
    }
}
