class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) return false;
        if (sx == tx && sy == ty) return true;
        if (tx > ty) {
            int k = (tx - sx) / ty;
            if (k == 0) return false;
            tx -= k * ty;
            return reachingPoints(sx, sy, tx, ty);
        }
        else if (ty > tx) {
            int k = (ty - sy) / tx;
            if (k == 0) return false;
            ty -= k * tx;
            return reachingPoints(sx, sy, tx, ty);
        }
        return false;
    }
}
