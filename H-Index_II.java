class Solution {
    int n;
    
    private int hIndex(int[] citations, int l, int r) {
        if (l == r) {
            if (citations[l] >= n - l) return n - l;
            return 0;
        }
        if (l + 1 == r) {
            if (citations[l] >= n - l) return n - l;
            if (citations[r] >= n - r) return n - r;
            return 0;
        }
        int mid = (l + r) / 2;
        if (citations[mid] >= n - mid) return hIndex(citations, l, mid);
        return hIndex(citations, mid + 1, r);
    }
    
    public int hIndex(int[] citations) {
        n = citations.length;
        if (n == 0) return 0;
        return hIndex(citations, 0, citations.length - 1);
    }
}
