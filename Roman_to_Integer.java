public class Solution {
    public int romanToInt(String s) {
        int integer = 0;
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            switch (s.charAt(i)) {
                case 'M':
                    integer += 1000;
                    break;
                case 'D':
                    integer += 500;
                    break;
                case 'C':
                    if (i < length - 1) {
                        char next = s.charAt(i + 1);
                        if (next == 'M' || next == 'D') {
                            integer -= 100;
                            break;
                        }
                    }
                    integer += 100;
                    break;
                case 'L':
                    integer += 50;
                    break;
                case 'X':
                    if (i < length - 1) {
                        char next = s.charAt(i + 1);
                        if (next == 'L' || next == 'C') {
                            integer -= 10;
                            break;
                        }
                    }
                    integer += 10;
                    break;
                case 'V':
                    integer += 5;
                    break;
                case 'I':
                    if (i < length - 1) {
                        char next = s.charAt(i + 1);
                        if (next == 'V' || next == 'X') {
                            integer -= 1;
                            break;
                        }
                    }
                    integer += 1;
                    break;
            }
        }


        return integer;
    }
}
