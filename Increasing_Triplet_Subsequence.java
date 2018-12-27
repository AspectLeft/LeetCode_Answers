class Solution {
    public boolean increasingTriplet(int[] nums) {
        int min1 = 0, min2 = 0;
        int size = 0;
        for (int n: nums) {
            switch (size) {
                case 0:
                    min1 = n;
                    size++;
                    break;
                case 1:
                    if (n > min1) {
                        min2 = n;
                        size++;
                    }
                    else if (n < min1) {
                        min1 = n;
                    }
                    break;
                case 2:
                    if (n < min1)
                        min1 = n;
                    else if (n > min1 && n < min2)
                        min2 = n;
                    else if (n > min2) return true;
            }
        }
        return false;
    }
}
