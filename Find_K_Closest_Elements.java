class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> result = new LinkedList<>();
        if (x <= arr[0]) {
            for (int i = 0; i < k; ++i) {
                result.add(arr[i]);
            }
            return result;
        }
        if (x >= arr[arr.length - 1]) {
            for (int i = arr.length - k; i < arr.length; ++i) {
                result.add(arr[i]);
            }
            return result;
        }
        
        int l = 0, r = arr.length - 1;
        int mid;
        while (l + 1 < r) {
            mid = (l + r) / 2;
            if (arr[mid] < x) {
                l = mid;
            }
            else {
                r = mid;
            }
        }
        for (int i = 0; i < k; ++i) {
            if (r == arr.length || (l >= 0 && x - arr[l] <= arr[r] - x)) {
                result.addFirst(arr[l]);
                l--;
            }
            else {
                result.addLast(arr[r]);
                r++;
            }
        }
        
        return result;
    }
}
