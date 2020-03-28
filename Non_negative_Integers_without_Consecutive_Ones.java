class Solution {
    private int[] f;
    
    private int bitLength(int num) {
        int l = 0;
        while (num != 0) {
            l++;
            num = num >> 1;
        }
        return l;
    }
    
    public int findIntegers(int num) {
        if (num < 3) return num + 1;
        f = new int[1 + bitLength(num)];
        f[0] = 1;
        f[1] = 2;
        f[2] = 3;
        for (int k = 3; k < f.length; ++k) {
            f[k] = f[k - 1] + f[k - 2];
        }
        return find(num);
    }
    
    private int find(int n) {
        if (n < 3) return n + 1;
        int l = bitLength(n);
        int s = f[l - 1];
        int m = n;
        m &= ~(1 << (l - 1));
        // System.out.println(m);
        
        if ((m & (1 << (l - 2))) != 0) {
            return s + f[l - 2];
        }
        else {
            m &= ~(1 << (l - 2));
            //System.out.println(m);
            s += find(m);
            return s;
        }
    }
}
