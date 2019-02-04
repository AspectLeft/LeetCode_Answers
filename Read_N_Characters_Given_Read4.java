/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    
    private char[] cache = new char[4];
    private int start = 4;
    private int ub = 4;
    
    public int read(char[] buf, int n) {
        int i = 0;
        while (i < n) {
            if (start < ub) {
                buf[i] = cache[start];
                start++;
                i++;
            }
            else {
                ub = read4(cache);
                start = 0;
                if (ub == 0)
                    break;
            }
        }
        return i;
    }
}
