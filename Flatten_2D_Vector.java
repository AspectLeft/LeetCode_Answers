class Vector2D {
    private int m, n;
    private int x, y;
    private boolean next;
    private int[][] v;

    public Vector2D(int[][] v) {
        this.m = 0;
        this.n = 0;
        this.x = 0;
        this.y = 0;
        this.next = true;
        this.v = v;
        
        if (v == null) return;
        this.m = v.length;
        
        updateXN();
    }
    
    public int next() {
        int value = v[x][y];
        y++;
        if (y == n) {
            y = 0;
            x++;
            updateXN();
        }
        return value;
    }
    
    private void updateXN() {
        while (x < m && (v[x] == null || v[x].length == 0)) x++;
        if (x == m) {
            this.next = false;
            return;
        }
        this.n = v[x].length;
    }
    
    public boolean hasNext() {
        return this.next;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(v);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
