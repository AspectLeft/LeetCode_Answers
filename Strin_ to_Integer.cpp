class Solution {
public:
    bool isDigit(char c) {
        return (c >= '0' && c <= '9');
    }
    
    
    int myAtoi(string str) {
        int length = str.size();
        if (length == 0)
            return 0;
        bool sign;
        int start = 0;
        while (str[start] == ' ') {
            start++;
        }
        if (str[start] == '-') {
            sign = true;
            start += 1;
        }
        else if (str[start] == '+') {
            sign = false;
            start += 1;
        }
        else if (isDigit(str[start])) {
            sign = false;
        }
        
        int result = 0;
        int newR = 0;
        int lsb;
        bool overflow = false;
        for (int i = start; i < length; ++i) {
            if (isDigit(str[i])) {
                lsb = str[i] - '0';
                newR = result * 10 + lsb;
                if (newR % 10 != lsb) {   //overflow
                    //if ((i + 1 >= length || !isDigit(str[i + 1])) && newR == -2147483648 && sign) {
                    //    return -2147483648;
                   // }
                    if (sign) {
                        return -2147483648;
                    }
                    else {
                        return 2147483647;
                    }
                }
                result = newR;
            }
            else {
                break;
            }
        }
        
        if (sign)
            result = -result;
        return result;
    }
    
    
};
