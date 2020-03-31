class Solution {
    private boolean canPlace(int[] flowerbed, int i) {
        if (flowerbed[i] == 1) return false;
        if (i > 0 && flowerbed[i - 1] == 1) return false;
        if (i < flowerbed.length - 1 && flowerbed[i + 1] == 1) return false;
        return true;
    }
    
    
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        for (int i = 0; i < flowerbed.length; ++i) {
            if (canPlace(flowerbed, i)) {
                flowerbed[i] = 1;
                n--;
                if (n == 0) return true;
            }
        }
        return false;
    }
}
