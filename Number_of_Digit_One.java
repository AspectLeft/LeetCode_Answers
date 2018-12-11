class Solution {
    public int countDigitOne(int n) {
        long m = n;
        long divider = 10;
        long c = 0;
        while (m >= divider / 10) {
            c += m / divider * divider / 10;
            if (m % divider >= divider / 10 * 2) {
                c += divider / 10;
            }
            else if (m % divider >= divider / 10) {
                c += m % divider - divider / 10 + 1;
            }
            divider *= 10;
        }
        return (int) c;
    }
}
