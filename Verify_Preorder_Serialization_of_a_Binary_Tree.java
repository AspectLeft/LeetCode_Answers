class Solution {
    String[] arr;
    
    private boolean isNS(int l, int n) {
        if (l >= arr.length) return false;
        if (n == 1) {
            if (l == arr.length - 1) return arr[l].equals("#");
            return !arr[l].equals("#") && isNS(l + 1, 2);
        }
        if (arr[l].equals("#")) return isNS(l + 1, n - 1);
        return isNS(l + 1, n + 1);
    }
    
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return false;
        arr = preorder.split(",");
        
        return isNS(0, 1);
    }
}
