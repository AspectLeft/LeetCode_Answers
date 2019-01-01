class Solution {
    
    
    
    private class Pair implements Comparable<Pair> {
        int i1, i2;
        long u, v;

        Pair(int i1, int i2) {
            this.i1 = i1;
            this.i2 = i2;
            u = nums1[i1];
            v = nums2[i2];
        }

        @Override
        public int compareTo(Pair o) {
            return Long.compare(u + v, o.u + o.v);
        }

        @Override
        public boolean equals(Object o) {
            return o != null && o instanceof Pair && i1 == ((Pair) o).i1 && i2 == ((Pair) o).i2;
        }
        
        
        @Override
        public int hashCode() {
            return Arrays.hashCode(new int[]{i1, i2});
        }
    }

    private int[] nums1, nums2;
    private int length1, length2;

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> output = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return output;
        length1 = nums1.length;
        length2 = nums2.length;
        this.nums1 = nums1;
        this.nums2 = nums2;
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        Set<Pair> dirty = new HashSet<>();
        queue.add(new Pair(0, 0));
        dirty.add(new Pair(0, 0));
        while (!queue.isEmpty() && output.size() < k) {
            Pair pair = queue.poll();
            output.add(new int[]{(int) pair.u, (int) pair.v});
            if (pair.i1 < length1 - 1) {
                Pair next = new Pair(pair.i1 + 1, pair.i2);
                if (!dirty.contains(next)) {
                    queue.add(next);
                    dirty.add(next);
                }
            }
            if (pair.i2 < length2 - 1) {
                Pair next = new Pair(pair.i1, pair.i2 + 1);
                if (!dirty.contains(next)) {
                    queue.add(next);
                    dirty.add(next);
                }
            }
        }
        return output;

    }

}
