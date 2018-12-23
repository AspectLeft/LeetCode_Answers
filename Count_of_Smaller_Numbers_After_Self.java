class Solution {
    class Node {
        int value;
        int pos;
        Node(int v, int p) {
            value = v;
            pos = p;
        }
    }

    private int lowbit(int x) { return x&(-x); }

    private void modify(int pos, int delta) {
        while (pos <= n) {
            inverse[pos] += delta;
            pos += lowbit(pos);
        }
    }

    private int sum(int end) {
        int s = 0;
        while (end > 0) {
            s += inverse[end];
            end -= lowbit(end);
        }
        return s;
    }

    private int n;
    private int[] count; // refined nums
    private int[] inverse;
    private Node[] d;


    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        n = nums.length;
        count = new int[n + 1];
        inverse = new int[n + 1]; // the BIT

        d = new Node[n + 1];
        for (int i = 0; i < n; ++i) {
            d[i + 1] = new Node(nums[i], i + 1);
        }

        Arrays.sort(d, 1, n + 1, Comparator.comparingInt(o -> o.value));

        int id = 1;
        count[d[1].pos] = 1;
        for (int i = 2; i <= n; ++i) {
            if (d[i].value == d[i - 1].value)
                count[d[i].pos] = id;
            else
                count[d[i].pos] = ++id;
        }

        LinkedList<Integer> output = new LinkedList<>();
        for (int i = n; i >= 1; --i) {
            modify(count[i], 1);
            output.addFirst(sum(count[i] - 1));
        }
        return output;
    }
}
