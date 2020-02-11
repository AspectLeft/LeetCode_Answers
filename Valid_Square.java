class Solution {
    private boolean equal2(int[] p1, int[] p2) {
        return p1[0] * 2 == p2[0] && p1[1] * 2 == p2[1];
    }
    
    private int square(int v) {
        return v * v;
    }
    
    private int dist2(int[] p1, int[] p2) {
        return square(p1[0] - p2[0]) + square(p1[1] - p2[1]);
    }
    
    private boolean equal(int[] p1, int[] p2) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }
    
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] ps = new int[][]{p1, p2, p3, p4};
        
        int d = dist2(p1, p4);
        int d2 = dist2(p1, p2);
        if (d2 > d) {
            int[] tmp = p2;
            p2 = p4;
            p4 = tmp;
            d = d2;
        }
        int d3 = dist2(p1, p3);
        if (d3 > d) {
            int[] tmp = p3;
            p3 = p4;
            p4 = tmp;
            d = d3;
        }
        
        if (equal(p1, p4)) return false;
        
        //System.out.println(String.format("p4:(%d,%d)", p4[0], p4[1]));
        
        
        int two_x0 = p1[0] + p4[0];
        int two_y0 = p1[1] + p4[1];
        
        int two_dx = 2 * p4[0] - two_x0;
        int two_dy = 2 * p4[1] - two_y0;
        
        
        int[] two_p5 = new int[]{two_x0 - two_dy, two_y0 + two_dx};
        int[] two_p6 = new int[]{two_x0 + two_dy, two_y0 - two_dx};
        
        
        // System.out.println(String.format("2p5:(%d,%d)", two_p5[0], two_p5[1]));
        // System.out.println(String.format("2p6:(%d,%d)", two_p6[0], two_p6[1]));
        
        
        return (equal2(p2, two_p5) && equal2(p3, two_p6))
            || (equal2(p2, two_p6) && equal2(p3, two_p5));
        
        
    }
}
