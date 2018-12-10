class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> output = new ArrayList<>();
        int c1 = 0, count = 0;
        for (int i: nums) {
            if (count == 0)
                c1 = i;
            if (i == c1)
                count += 2;
            else count --;
        }
        count = 0;
        for (int i: nums)
            if (i == c1)
                count++;
        if (3 * count > nums.length)
            output.add(c1);
        
        int c2 = 0;
        count = 0;
        for (int i : nums) {
            if (i == c1) continue;
            if (count == 0)
                c2 = i;
            if (i == c2)
                count++;
            else count--;
        }
        if (c2 == c1) return output;
        count = 0;
        for (int i: nums)
            if (i == c2)
                count++;
        if (3 * count > nums.length)
            output.add(c2);
        
        return output;
    }
}
