class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        int prev_old = Integer.MAX_VALUE, prev_new = 0, pivot = Integer.MAX_VALUE;
        int sum = 0, downlength = 0;
        for (int i = 0; i < ratings.length; ++i) {
            if (ratings[i] < prev_old) {
                downlength++;
                sum += downlength;
                prev_new = 1;
                if (pivot <= downlength) {
                    sum++;
                }
            }
            else if (ratings[i] == prev_old) {
                downlength = 1;
                prev_new = 1;
                sum++;
                pivot = Integer.MAX_VALUE;
            }
            else {
                downlength = 0;
                prev_new++;
                sum += prev_new;
                pivot = prev_new;
            }
            
            prev_old = ratings[i];
            
        }
        return sum;
    }
    
    
}
