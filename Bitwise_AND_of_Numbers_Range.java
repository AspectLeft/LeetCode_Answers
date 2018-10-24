class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int diff = m ^ n;
        int cnt = 0;
        while (diff > 0) {
            diff >>= 1;
            cnt++;
        }
        return (m & n & (0x80000000 >> (31 - cnt)));
        
    }
}
