class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) return new ArrayList<>();
        return getSkyline(buildings, 0, buildings.length - 1);
    }
    
    private List<int[]> getSkyline(int[][] buildings, int left, int right) {
        List<int[]> output = new ArrayList<>();
        if (left == right) {
            int[] node1 = {buildings[left][0], buildings[left][2]};
            int[] node2 = {buildings[left][1], 0};
            output.add(node1);
            output.add(node2);
            return output;
        }
        int mid = left + (right - left) / 2;
        List<int[]> leftPart = getSkyline(buildings, left, mid), rightPart = getSkyline(buildings, mid + 1, right);
        
        //Conquer
        Set<Integer> set = new HashSet<>();
        List<Integer> segments = new ArrayList<>();
        for (int[] segment: leftPart) {
            set.add(segment[0]);
        }
        for (int[] segment: rightPart) {
            set.add(segment[0]);
        }
        segments.addAll(set);
        Collections.sort(segments);
        
        Map<Integer, Integer> height = new HashMap<>();
        for (int segment: segments) {
            height.put(segment, 0);
        }
        
        Map<Integer, Integer> segmentIndices = new HashMap<>();
        for (int i = 0; i < segments.size(); ++i) {
            segmentIndices.put(segments.get(i), i);
        }
        
        
        int l, r;
        int l_index, r_index;
        for (int j = 0; j < leftPart.size() - 1; ++j) {
            l = (leftPart.get(j))[0];
            r = (leftPart.get(j + 1))[0];
            l_index = segmentIndices.get(l);
            r_index = segmentIndices.get(r);
            for (int i = l_index; i < r_index; ++i) {
                int segment = segments.get(i);
                if (height.get(segment) < (leftPart.get(j))[1]) {
                    height.put(segment, (leftPart.get(j))[1]);
                }
            }
        }
        
        
        for (int j = 0; j < rightPart.size() - 1; ++j) {
            l = (rightPart.get(j))[0];
            r = (rightPart.get(j + 1))[0];
            l_index = segmentIndices.get(l);
            r_index = segmentIndices.get(r);
            for (int i = l_index; i < r_index; ++i) {
                int segment = segments.get(i);
                if (height.get(segment) < (rightPart.get(j))[1]) {
                    height.put(segment, (rightPart.get(j))[1]);
                }
            }
        }
        
        
        int prevHeight = 0, h;
        for (int segment: segments) {
            h = height.get(segment);
            if (h != prevHeight) {
                int[] node = {segment, h};
                output.add(node);
                prevHeight = h;
            }
        }
        
        return output;
    }
}
