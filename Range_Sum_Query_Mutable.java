class NumArray {
    int n;
    int[] tree;
    int[] nums;
    
    private void pushUp(int root) {
        tree[root] = tree[root << 1] + tree[root << 1 | 1];
    }
    
    private void build(int root, int l, int r) {
        if (l == r) { tree[root] = nums[l - 1]; return; }
        int m = (l + r) >> 1;
        build(root << 1, l, m);
        build(root << 1 | 1, m + 1, r);
        pushUp(root);
    }
    
    private void update(int i, int val, int root, int l, int r) {
        if (l == r) {
            tree[root] = val;
            return;
        }
        int m = (l + r) >> 1;
        if (i <= m) update(i, val, root << 1, l, m);
        else update(i, val, root << 1 | 1, m + 1, r);
        pushUp(root);
    }
    
    private int query(int L, int R, int root, int l, int r) {
        if (L <= l && r <= R) return tree[root];
        int m = (l + r) >> 1, ret = 0;
        if (L <= m) ret += query(L, R, root << 1, l, m);
        if (R > m) ret += query(L, R, root << 1 | 1, m + 1, r);
        return ret;
    }

    public NumArray(int[] nums) {
        this.nums = nums;
        if (nums != null) n = nums.length;
        if (n > 0) {
            tree = new int[n * 4 + 2];
            build(1, 1, n);
        }
    }
    
    public void update(int i, int val) {
        update(i + 1, val, 1, 1, n);
    }
    
    public int sumRange(int i, int j) {
        return query(i + 1, j + 1, 1, 1, n);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
