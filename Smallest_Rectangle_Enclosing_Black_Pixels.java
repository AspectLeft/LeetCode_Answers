class Solution {
    public int minArea(char[][] image, int x, int y) {
        int m = image.length;
        int n = image[0].length;
        
        int x1 = searchX1(image, 0, x);
        int x2 = searchX2(image, x, m - 1);
        int y1 = searchY1(image, 0, y);
        int y2 = searchY2(image, y, n - 1);
        
        return (x2 - x1 + 1) * (y2 - y1 + 1);
    }
    
    private int searchX1(char[][] image, int xl, int xr) {
        while (xl < xr) {
            int mid = (xl + xr) / 2;
            if (checkRow(image, mid)) {
                xr = mid;
            }
            else {
                xl = mid + 1;
            }
        }
        return xl;
    }
    
    private int searchX2(char[][] image, int xl, int xr) {
        while (xl < xr) {
            int mid = (xl + xr + 1) / 2;
            if (checkRow(image, mid)) {
                xl = mid;
            }
            else {
                xr = mid - 1;
            }
        }
        return xl;
    }
    
    private int searchY1(char[][] image, int yl, int yr) {
        while (yl < yr) {
            int mid = (yl + yr) / 2;
            if (checkCol(image, mid)) {
                yr = mid;
            }
            else {
                yl = mid + 1;
            }
        }
        return yl;
    }
    
    private int searchY2(char[][] image, int yl, int yr) {
        while (yl < yr) {
            int mid = (yl + yr + 1) / 2;
            if (checkCol(image, mid)) {
                yl = mid;
            }
            else {
                yr = mid - 1;
            }
        }
        return yl;
    }
    
    private boolean checkRow(char[][] image, int x) {
        for (int y = 0; y < image[x].length; ++y) {
            if (image[x][y] == '1') return true;
        }
        return false;
    }
    
    private boolean checkCol(char[][] image, int y) {
        for (int x = 0; x < image.length; ++x) {
            if (image[x][y] == '1') return true;
        }
        return false;
    }
}
