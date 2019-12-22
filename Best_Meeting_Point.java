class Solution {
    private Random random = new Random();
    
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        List<Integer> xs = new ArrayList<>();
        List<Integer> ys = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    xs.add(i);
                    ys.add(j);
                }
            }
        }
        if (xs.size() == 0) return 0;
        int[] xArr = new int[xs.size()];
        int[] yArr = new int[ys.size()];
        for (int i = 0; i < xArr.length; ++i) {
            xArr[i] = xs.get(i);
            yArr[i] = ys.get(i);
        }
        int xm = select(xArr, 0, xArr.length - 1, xArr.length / 2);
        int ym = select(yArr, 0, yArr.length - 1, yArr.length / 2);
        int result = 0;
        for (int i = 0; i < xArr.length; ++i) {
            result += Math.abs(xArr[i] - xm) + Math.abs(yArr[i] - ym);
        }
        return result;
    }
    
    // Quick select
    private int select(int[] nums, int l, int r, int k) {
        if (l == r && k == 0) return nums[l];
        int i = random.nextInt(r - l + 1) + l;
        swap(nums, l, i);
        int pivot = nums[l];
        int low = l, high = r;
        do {
            while (low < high && nums[high] >= pivot) --high;
            if (low < high) {
                nums[low] = nums[high];
                low++;
            }
            while (low < high && nums[low] <= pivot) ++low;
            if (low < high) {
                nums[high] = nums[low];
                --high;
            }
        } while (low != high);
        nums[low] = pivot;
        if (low - l == k) return nums[low];
        else if (low - l > k) return select(nums, l, low - 1, k);
        else return select(nums, low + 1, r, k - (low - l + 1));
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
