class Solution {
    private class EdgeComparator implements Comparator<int[]> {
        private int[] p0;
        
        EdgeComparator(int[] p0) {
            this.p0 = p0;
        }
        
        public void setP0(int[] p0) {
            this.p0 = p0;
        }
        
        @Override
        public int compare(int[] p1, int[] p2) {
            int y1 = p1[1] - p0[1];
            int x1 = p1[0] - p0[0];
            int y2 = p2[1] - p0[1];
            int x2 = p2[0] - p0[0];
            
            int m1 = x1 * x1 + y1 * y1;
            int m2 = x2 * x2 + y2 * y2;
            
            if (x1 * x2 >= 0 && x1 * x1 * m2 == x2 * x2 * m1)
                return Integer.compare(m1, m2);

            int c = - Double.compare(x1 * Math.sqrt(m2), x2 * Math.sqrt(m1));
            if (c != 0) return c;
            return Integer.compare(m1, m2);
        }
    }
    
    private class DirComparator implements Comparator<int[]> {
        private int[] p0;
        
        DirComparator(int[] p0) {
            this.p0 = p0;
        }
        
        public void setP0(int[] p0) {
            this.p0 = p0;
        }
        
        @Override
        public int compare(int[] p1, int[] p2) {
            int y1 = p1[1] - p0[1];
            int x1 = p1[0] - p0[0];
            int y2 = p2[1] - p1[1];
            int x2 = p2[0] - p1[0];

            
            return Integer.compare(y1 * x2, y2 * x1);
        }
    }
    
    public int[][] outerTrees(int[][] points) {
        if (points == null || points.length < 4) return points;
        int[] p0 = points[0];
        for (int[] p: points) {
            if (p[1] < p0[1] || (p[1] == p0[1] && p[0] < p0[0])) {
                p0 = p;
            }
        }
        EdgeComparator ec =  new EdgeComparator(p0);
        Arrays.sort(points, ec);
        for (int[] p: points) {
            //System.out.println(String.format("[%d, %d]", p[0], p[1]));
        }
        
        Stack<int[]> stack = new Stack<>();
        DirComparator dc = new DirComparator(p0);
        
        for (int[] p3: points) {
            while (stack.size() > 1) {
                int[] p2 = stack.pop();
                int[] p1 = stack.pop();
                stack.push(p1);
                stack.push(p2);
                
                dc.setP0(p1);
                if (dc.compare(p2, p3) > 0) {
                    stack.pop();
                }
                else {
                    break;
                }
            }
            stack.push(p3);
        }
        int[] p01 = points[points.length - 1];
        for (int i = points.length - 2; i > 0; --i) {
            dc.setP0(p0);
            if (dc.compare(points[i], p01) == 0) {
                stack.push(points[i]);
            }
            else break;
        }
        
        int[][] output = new int[stack.size()][2];
        for (int i = 0; i < output.length; ++i) {
            output[i] = stack.pop();
        }
        return output;
    }
}
