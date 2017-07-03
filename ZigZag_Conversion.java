public class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        int l = s.length();
        String[] subStrings = new String[numRows];
        int currentRow = 0;
        boolean dir = true;
        for (int i = 0; i < numRows; ++i) {
            subStrings[i] = "";
        }
        for (int i = 0; i < l; ++i) {
            subStrings[currentRow] += s.substring(i, i + 1);
            if (dir)
            {
                currentRow += 1;
                if (currentRow == numRows - 1) {
                    dir = false;
                }
            }
            else {
                currentRow -= 1;
                if (currentRow == 0) {
                    dir = true;
                }
            }
        }
        String result = "";
        for (String subString: subStrings) {
            result += subString;
        }
        return result;
    }
}
