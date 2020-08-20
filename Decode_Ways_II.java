class Solution {
    private static final int MOD = 1000000007;
    
    private boolean is1to9(char c) {
        return '1' <= c && c <= '9';
    }
    
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 1;
        
        long[] table = new long[s.length() + 1];
        table[0] = 1;
        char c = s.charAt(0);
        if (is1to9(c)) {
            table[1] = 1;
        }
        else if (c == '*') {
            table[1] = 9;
        }
        else {
            return 0;
        }
        
        char prev;
        for (int i = 1; i < s.length(); ++i) {
            table[i + 1] = 0;
            c = s.charAt(i);
            prev = s.charAt(i - 1);
            if (is1to9(c)) {
                table[i + 1] = (table[i + 1] + table[i]) % MOD;
            }
            else if (c == '*') {
                table[i + 1] = (table[i + 1] + 9 * table[i]) % MOD;
            }
            
            if (prev == '*') {
                if ('0' <= c && c <= '6') {
                    table[i + 1] = (table[i + 1] + 2 * table[i - 1]) % MOD;
                }
                else if ('7' <= c && c <= '9') {
                    table[i + 1] = (table[i + 1] + table[i - 1]) % MOD;
                }
                else if ('*' == c) {
                    table[i + 1] = (table[i + 1] + 15 * table[i - 1]) % MOD;
                }
            }
            else {
                if (c == '*') {
                    if (prev == '1') {
                        table[i + 1] = (table[i + 1] + 9 * table[i - 1]) % MOD;
                    }
                    else if (prev == '2') {
                        table[i + 1] = (table[i + 1] + 6 * table[i - 1]) % MOD;
                    }
                }
                else {
                    int code = (prev - '0') * 10 + (c - '0');
                    if (code >= 10 && code <= 26) {
                        table[i + 1] = (table[i + 1] + table[i - 1]) % MOD;
                    }
                }
            }
        }
        
        return (int) table[s.length()];
    }
}
