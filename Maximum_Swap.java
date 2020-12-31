class Solution {
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] arr = s.toCharArray();
        int[] rangeMax = new int[arr.length];
        rangeMax[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; --i) {
            rangeMax[i] = arr[i] > arr[rangeMax[i + 1]] ? i : rangeMax[i + 1];
            // System.out.println(rangeMax[i]);
        }
        for (int i = 0; i < arr.length; ++i) {
            if (rangeMax[i] > i && arr[i] < arr[rangeMax[i]]) {
                char tmp = arr[i];
                arr[i] = arr[rangeMax[i]];
                arr[rangeMax[i]] = tmp;
                break;
            }
        }
        return Integer.valueOf(new String(arr));
    }
}
