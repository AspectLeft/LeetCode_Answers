class Solution {
    class IntCnt implements Comparable<IntCnt> {

        int value, count;

        IntCnt(int v) {
            value = v;
            count = 1;
        }

        @Override
        public int compareTo(IntCnt o) {
            return Integer.compare(o.count, count);
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Map<Integer, IntCnt> map = new HashMap<>();
        for (int v: nums) {
            if (map.get(v) == null)
                map.put(v, new IntCnt(v));
            else
                map.get(v).count++;
        }
        PriorityQueue<IntCnt> heap = new PriorityQueue<>(map.values());
        List<Integer> output = new ArrayList<>();
        for (int i = 0; i < k; ++i)
            output.add(heap.poll().value);
        return output;
    }
}
