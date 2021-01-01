class Solution {
    public int flipLights(int n, int m) {
        if (m == 0) return 1;
        if (n == 0) return 1;
        if (n == 1) return 2;
        
        
        
        if (n >= 3) {
            if (m == 1) {
                return 4;
            }
            if (m == 2) {
                return 7;
            }
            if (m >= 4 && m % 2 == 0) {
                return 8;
            }
            if (m >= 3 && m % 2 == 1) {
                return 8;
            }
            
        }
        
        // n == 2;
        if (m == 1) {
            return 3;
        }
        return 4;
    }
}
