class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        if (heaters == null || heaters.length == 0) return Integer.MAX_VALUE;
        if (heaters.length == 1) {
            int r = 0;
            for (int house: houses) {
                r = Math.max(r, Math.abs(house - heaters[0]));
            }
            return r;
        }
        Arrays.sort(heaters);
        Arrays.sort(houses);
        int r = 0;
        int right = 0;
        for (int house: houses) {
            while (right < heaters.length && house > heaters[right]) right++;
            if (right == 0) {
                r = Math.max(r, heaters[right] - house);
            }
            else if (right == heaters.length) {
                r = Math.max(r, house - heaters[right - 1]);
            }
            else {
                r = Math.max(r, Math.min(heaters[right] - house, house - heaters[right - 1]));
            }
        }
        return r;
    }
}
