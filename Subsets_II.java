class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<Integer> distinctNums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                distinctNums.add(nums[i]);
                counts.add(1);
            }
            else {
                int l = distinctNums.size();
                counts.set(l - 1, counts.get(l - 1) + 1);
            }
        }

        int length = distinctNums.size();
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<>());
        int num, count;
        for (int i = 0; i < length; ++i) {
            num = distinctNums.get(i);
            count = counts.get(i);
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> subset: subsets) {
                for (int c = 0; c <= count; ++c) {
                    List<Integer> s = new ArrayList<>(subset);
                    for (int j = 0; j < c; ++j) {
                        s.add(num);
                    }
                    tmp.add(s);

                }
            }
            subsets = tmp;
        }
        return subsets;
    }




}
