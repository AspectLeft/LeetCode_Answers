class Solution {
    public int[] constructArray(int n, int k) {
        int[] arr = new int[n];
        for (int i = 0; i < n - k - 1; ++i) {
            arr[i] = i + 1;
        }
        for (int i = n - k - 1, j = i + 1; i < n; i += 2, j++) {
            arr[i] = j;
        }
        for (int i = n - k, j = n; i < n; i += 2, j--) {
            arr[i] = j;
        }
        return arr;
    }
}
