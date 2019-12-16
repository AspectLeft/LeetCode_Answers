class Solution {
    
    private boolean valid(char c) {
        switch (c) {
            case '0':
            case '1':
            case '9':
            case '8':
            case '6':
                return true;
        }
        return false;
    }

    private char counterpart(char c) {
        if (c == '6') return '9';
        if (c == '9') return '6';
        return c;
    }
    
    private int validCount(int l) {
        int count = 4;
        for (int i = 1; i < l; ++i) {
            count *= 5;
        }
        return count;
    }
    
    private int lessOrEqualValidCount(String s, int i) {
        if (i == s.length()) return 1;
        char c = s.charAt(i);
        int count = 0;
        switch (c) {
            case '9':
                count += lessOrEqualValidCount(s, i + 1);
                count += 4 * freeValidCount(s, i + 1);
                return count;
            case '8':
                count += lessOrEqualValidCount(s, i + 1);
                count += 3 * freeValidCount(s, i + 1);
                return count;
            case '7':
                count += 3 * freeValidCount(s, i + 1);
                return count;
            case '6':
                count += lessOrEqualValidCount(s, i + 1);
                count += 2 * freeValidCount(s, i + 1);
                return count;
            case '5':
            case '4':
            case '3':
            case '2':
                return 2 * freeValidCount(s, i + 1);
            case '1':
                count += lessOrEqualValidCount(s, i + 1);
                count += freeValidCount(s, i + 1);
                return count;
            case '0':
                return lessOrEqualValidCount(s, i + 1);
        }
        return 0;
    }
    
    private int freeValidCount(String s, int i) {
        if (i == s.length()) return 1;
        return 5 * freeValidCount(s, i + 1);
    }
    
    private int lessOrEqualSameCount(String s, int i) {
        if (i > (s.length() - 1) / 2) return 1;
        
        
        
        
        char c = s.charAt(i);
        if (s.length() % 2 == 1 && i == s.length() / 2) {
            if (c >= '8') return 3;
            if (c >= '1') return 2;
            return 1;
        }
        int count = 0;
        switch (c) {
            
            case '9':
                count += lessOrEqualSameCount(s, i + 1);
                count += 4 * freeSameCount(s, i + 1);
                return count;
            case '8':
                count += lessOrEqualSameCount(s, i + 1);
                count += 3 * freeSameCount(s, i + 1);
                return count;
            case '7':
                count += 3 * freeSameCount(s, i + 1);
                return count;
            case '6':
                count += lessOrEqualSameCount(s, i + 1);
                count += 2 * freeSameCount(s, i + 1);
                return count;
            case '5':
            case '4':
            case '3':
            case '2':
                return 2 * freeSameCount(s, i + 1);
            case '1':
                count += lessOrEqualSameCount(s, i + 1);
                count += freeSameCount(s, i + 1);
                return count;
            case '0':
                return lessOrEqualSameCount(s, i + 1);
        }
        return 0;
    }
    
    private int freeSameCount(String s, int i) {
        if (s.equals("00")) {
            //System.out.println("length " + s.length());
            //System.out.println("(l - 1) / 2 : " + (s.length() - 1) / 2);
            //System.out.println("i " + i);
        }
        if (i > (s.length() - 1) / 2) return 1;
        if (s.length() % 2 == 1 && i == s.length() / 2) {
            return 3;
        }
        return 5 * freeSameCount(s, i + 1);
    }
    
    private boolean halfValid(String s) {
        if (s.length() % 2 == 1) {
            switch (s.charAt(s.length() / 2)) {
                case '0':
                case '1':
                case '8':
                    break;
                default:
                    return false;
            }
        }
        for (int i = 0; i <= (s.length() - 1) / 2; ++i) {
            if (!valid(s.charAt(i))) return false;
        }
        return true;
    }
    
    
    private boolean isStrobo(char a, char b) {
        return (a == '0' && b == '0')
            || (a == '1' && b == '1')
            || (a == '6' && b == '9')
            || (a == '8' && b == '8')
            || (a == '9' && b == '6');
    }
    
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return false;
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!isStrobo(num.charAt(l), num.charAt(r)))
                return false;
            
            l++;
            r--;
        }
        
        return true;
    }
    
    public int confusingNumberII(int N) {
        if (N < 2) return 0;
        String s = String.valueOf(N);
        int validNumber = lessOrEqualValidCount(s, 0) - 1;
        int sameNumber = lessOrEqualSameCount(s, 0) - 1;
        if (s.length() > 1) {
            String sub = s.substring(1);
            int more = freeSameCount(sub, 0) - 1;
            // System.out.println(more);
            sameNumber += more;
        }
        
        if (halfValid(s)) {
            StringBuilder builder = new StringBuilder();
            builder.append(s.substring(0, (s.length() - 1) / 2 + 1));
            for (int i = s.length() / 2 - 1; i >= 0; --i) {
                builder.append(counterpart(s.charAt(i)));
            }
            if (builder.toString().compareTo(s) > 0) {
                sameNumber -= 1;
            }
        }
        
        return validNumber - sameNumber;
    }
    
    private int getSameNumber(String N) {
        
        if (N.length() == 1 && N.charAt(0) < '2') return 0;
        String s = String.valueOf(N);
        int sameNumber = lessOrEqualSameCount(s, 0) - 1;
        if (s.length() > 1) {
            String sub = s.substring(1);
            int more = freeSameCount(sub, 0) - 1;
            sameNumber += more;
        }
        
        if (halfValid(s)) {
            StringBuilder builder = new StringBuilder();
            builder.append(s.substring(0, (s.length() - 1) / 2 + 1));
            for (int i = s.length() / 2 - 1; i >= 0; --i) {
                builder.append(counterpart(s.charAt(i)));
            }
            if (builder.toString().compareTo(s) > 0) {
                sameNumber -= 1;
            }
        }
        
        return sameNumber;
    }
    
    private boolean larger(String low, String high) {
        if (low.length() > high.length()) return true;
        if (low.length() < high.length()) return false;
        for (int i = 0; i < low.length(); ++i) {
            if (low.charAt(i) > high.charAt(i)) return true;
            if (low.charAt(i) < high.charAt(i)) return false;
        }
        return false;
    }
    
    public int strobogrammaticInRange(String low, String high) {
        if (larger(low, high)) return 0;
        return getSameNumber(high) - getSameNumber(low) 
            + (isStrobogrammatic(low) ? 1 : 0);
    }
}
