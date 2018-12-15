class Solution {
    public int[] singleNumber(int[] nums) {
        int v = 0;
        for (int i: nums) v ^= i;
        int setbit = 1;
        while ((v & setbit) == 0) setbit <<= 1;
        int a = 0, b = 0;
        for (int i: nums) {
            if ((i & setbit) == 0)
                a ^= i;
            else 
                b ^= i;
        }
        return new int[]{a, b};
    }
}
