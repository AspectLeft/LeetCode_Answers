class StringIterator {
    private static class CharCount {
        char c;
        int count;
        
        CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }
    
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
    
    private LinkedList<CharCount> list;

    public StringIterator(String compressedString) {
        list = new LinkedList<>();
        int i = 0;
        CharCount cc = null;
        char c;
        while (i < compressedString.length()) {
            c = compressedString.charAt(i);
            if (!isDigit(c)) {
                int count = 0;
                i++;
                char origin_c = c;
                c = compressedString.charAt(i);
                while (i < compressedString.length()) {
                    c = compressedString.charAt(i);
                    if (!isDigit(c)) break;
                    count *= 10;
                    count += c - '0';
                    i++;
                }
                cc = new CharCount(origin_c, count);
                list.addLast(cc);
            }
        }
    }
    
    public char next() {
        if (list.isEmpty()) return ' ';
        CharCount cc = list.getFirst();
        cc.count--;
        if (cc.count == 0) list.removeFirst();
        return cc.c;
    }
    
    public boolean hasNext() {
        return !list.isEmpty();
    }
}

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator obj = new StringIterator(compressedString);
 * char param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
