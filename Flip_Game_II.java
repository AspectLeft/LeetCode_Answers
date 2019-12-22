class Solution {
    public boolean canWin(String s) {
        return can(s.toCharArray());
    }
    
    private boolean can(char[] table) {
        boolean terminate = true;
        for (int i = 1; i < table.length; ++i) {
            if (table[i] == '+' && table[i - 1] == '+') {
                terminate = false;
                break;
            }
        }
        if (terminate) return false;
        for (int i = 1; i < table.length; ++i) {
            if (table[i] == '+' && table[i - 1] == '+') {
                table[i] = '-';
                table[i - 1] = '-';
                boolean counter = can(table);
                table[i] = '+';
                table[i - 1] = '+';
                if (!counter) return true;
            }
        }
        return false;
    }
}
