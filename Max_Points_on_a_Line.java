/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    private long point2Long(Point p) {
        long x = p.x;
        return (x << 32) + p.y;
    }
    
    boolean samePoint(Point p, Point q) {
        return p.x == q.x && p.y == q.y;
    }
    
    class Point2 {
        int x, y;
        int count;
        int index;
        Point2(Point p) {this.x = p.x; this.y = p.y; count = 1;}
    }
    
    class Line {
        Point2 p, q;
        List<Point2> other;
        int count;
        Line(Point2 p, Point2 q) {
            this.p = p;
            this.q = q;
            count = p.count + q.count;
            other = new ArrayList<>();
        }
        
        boolean pass(Point2 r) {
            if ((r.x - p.x) * (long) (r.y - q.y) == (r.x - q.x) * (long) (r.y - p.y)) {
                count += r.count;
                other.add(r);
                return true;
            }
            return false;
        }
    }
    
    Set<Line> lines = new HashSet<>();
    
    private Point2[] refine(Point[] points) {
        Map<Long, Point2> map = new HashMap<>();
        for (Point p: points) {
            Point2 p2 = map.get(point2Long(p));
            if (p2 == null) {
                map.put(point2Long(p), new Point2(p));
            }
            else {
                p2.count++;
            }
        }
        return map.values().toArray(new Point2[map.size()]);
    }
    
    public int maxPoints(Point[] points) {
        if (points == null) return 0;
        if (points.length <= 2) return points.length;
        Point2[] points2 = refine(points);
        for (int i = 0; i < points2.length; ++i) {
            points2[i].index = i;
            boolean[] flag = new boolean[i + 1];
            for (Line line: lines) {
                if (line.pass(points2[i])) {
                    flag[line.p.index] = true;
                    flag[line.q.index] = true;
                    for (Point2 o: line.other)
                        flag[o.index] = true;
                }
            }
            for (int j = 0; j < i; ++j) {
                if (!flag[j]) {
                    lines.add(new Line(points2[j], points2[i]));
                }
            }
        }
        int max = points2[0].count;
        for (Line line: lines)
            max = line.count > max ? line.count : max;
        return max;
    }
}
