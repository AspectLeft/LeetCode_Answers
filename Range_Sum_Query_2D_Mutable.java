class NumMatrix {
    class RangeSum {
        int r1, c1, r2, c2;
        int sum;
        List<RangeSum> children;
        
        RangeSum(int r1, int c1, int r2, int c2, int[][] mat) {
            this.r1 = r1;
            this.r2 = r2;
            this.c1 = c1;
            this.c2 = c2;
            children = new ArrayList<>();
            if (r1 == r2 && c1 == c2) {
                sum = mat[r1][c1];
                return;
            }
            if (r1 == r2) {
                int mid = (c1 + c2) / 2;
                RangeSum child1 = new RangeSum(r1, c1, r2, mid, mat);
                RangeSum child2 = new RangeSum(r1, mid + 1, r2, c2, mat);
                children.add(child1);
                children.add(child2);
                sum = child1.sum + child2.sum;
                return;
            }
            if (c1 == c2) {
                int mid = (r1 + r2) / 2;
                RangeSum child1 = new RangeSum(r1, c1, mid, c2, mat);
                RangeSum child2 = new RangeSum(mid + 1, c1, r2, c2, mat);
                children.add(child1);
                children.add(child2);
                sum = child1.sum + child2.sum;
                return;
            }
            int m1 = (r1 + r2) / 2, m2 = (c1 + c2) / 2;
            RangeSum child1 = new RangeSum(r1, c1, m1, m2, mat);
            RangeSum child2 = new RangeSum(r1, m2 + 1, m1, c2, mat);
            RangeSum child3 = new RangeSum(m1 + 1, c1, r2, m2, mat);
            RangeSum child4 = new RangeSum(m1 + 1, m2 + 1, r2, c2, mat);
            children.add(child1);
            children.add(child2);
            children.add(child3);
            children.add(child4);
            sum = child1.sum + child2.sum + child3.sum + child4.sum;
        }
        
        public void update(int r, int c, int delta) {
            if (r1 <= r && r <= r2 && c1 <= c && c <= c2) {
                sum += delta;
                for (RangeSum child: children) {
                    child.update(r, c, delta);
                }
            }
        }
        
        public int query(int qr1, int qc1, int qr2, int qc2) {
            if (qr1 <= r1 && r2 <= qr2 && qc1 <= c1 && c2 <= qc2) {
                return sum;
            }
            if (qr2 < r1 || r2 < qr1 || qc2 <= c1 && c2 < qc1) {
                return 0;
            }
            int s = 0;
            for (RangeSum child: children) {
                s += child.query(qr1, qc1, qr2, qc2);
            }
            return s;
        }
    }
    
    int[][] mat;
    int m, n;
    RangeSum root;

    public NumMatrix(int[][] matrix) {
        this.mat = matrix;
        if (mat == null || mat.length == 0 || mat[0].length == 0) return;
        this.m = mat.length;
        this.n = mat[0].length;
        root = new RangeSum(0, 0, m - 1, n - 1, mat);
    }
    
    public void update(int row, int col, int val) {
        int delta = val - mat[row][col];
        mat[row][col] = val;
        root.update(row, col, delta);
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return root.query(row1, col1, row2, col2);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */
