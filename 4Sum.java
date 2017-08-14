public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        List<List<Integer>> solutions = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 4)
            return solutions;
        for (int i = 0; i < nums.length - 3; ++i) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length - 2; ++j) {
                int b = nums[j];
                int l = j + 1, r = nums.length - 1, c, d, tmp;
                while (l < r) {
                    c = nums[l];
                    d = nums[r];
                    tmp = a + b + c + d;
                    if (tmp == target) {
                        ArrayList<Integer> solution = new ArrayList<>();
                        solution.add(a);
                        solution.add(b);
                        solution.add(c);
                        solution.add(d);
                        solutions.add(solution);
                        l++;
                        r--;
                    }
                    else if (tmp < target) {
                        l++;
                    }
                    else {
                        r--;
                    }
                }
            }
        }

        
        Set<List<Integer>> set = new HashSet<>();
        set.addAll(solutions);
        solutions.clear();
        solutions.addAll(set);
        return solutions;
    }
}
