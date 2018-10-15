class Solution {
    private int[] plate = new int[32];
    
    public int singleNumber(int[] nums) {
        for (int n: nums) {
            addPlate(n);
        }
        return plateToInt();
    }
    
    private void addPlate(int n) {
        for (int i = 0; i < 32; ++i) {
            plate[i] = (plate[i] + ((n >> i) & 1)) % 3;
        }
    }
    
    private int plateToInt() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            n |= (plate[i] << i);
        }
        return n;
    }
}
