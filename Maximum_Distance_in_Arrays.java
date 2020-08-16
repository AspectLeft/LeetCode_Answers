class Solution {
    public int maxDistance(List<List<Integer>> arrays) {
//         if (arrays == null || arrays.size() < 2) return 0;
//         List<Integer> minList = arrays.stream()
//             .map(Collections::min).collect(Collectors.toList());
//         List<Integer> maxList = arrays.stream()
//             .map(Collections::max).collect(Collectors.toList());
//         List<Integer> leftMax = new ArrayList<>(arrays.size());
//         leftMax.add(maxList.get(0));
//         for (int i = 1; i < maxList.size(); ++i) {
//             leftMax.add(Math.max(leftMax.get(i - 1), maxList.get(i)));
//         }
//         List<Integer> rightMax = new ArrayList<>(arrays.size());
//         rightMax.add(maxList.get(maxList.size() - 1));
//         for (int i = maxList.size() - 2; i >= 0; --i) {
//             rightMax.add(Math.max(rightMax.get(maxList.size() - 2 - i), maxList.get(i)));
//         }
        
//         int md = Math.max(rightMax.get(rightMax.size() - 2) - minList.get(0),
//                          leftMax.get(leftMax.size() - 2) - minList.get(minList.size() - 1));
//         for (int i = 1; i <= minList.size() - 2; ++i) {
//             md = Math.max(md, Math.max(leftMax.get(i - 1), rightMax.get(rightMax.size() - 2 - i)) - minList.get(i));
//         }
//         return md;
        
        if (arrays == null || arrays.size() < 2) return 0;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        
        int i1 = 0, i2 = 0;
        int j1 = 0, j2 = 0;
        
        int min, max;
        List<Integer> array;
        for (int i = 0; i < arrays.size(); ++i) {
            array = arrays.get(i);
            min = array.get(0);
            max = array.get(array.size() - 1);
            
            if (min < min1) {
                min2 = min1;
                i2 = i1;
                
                min1 = min;
                i1 = i;
            }
            else if (min < min2) {
                min2 = min;
                i2 = i;
            }
            
            if (max > max1) {
                max2 = max1;
                j2 = j1;
                
                max1 = max;
                j1 = i;
            }
            else if (max > max2) {
                max2 = max;
                j2 = i;
            }
        }
        if (i1 != j1) return max1 - min1;
        return Math.max(max1 - min2, max2 - min1);
        
    }
}
