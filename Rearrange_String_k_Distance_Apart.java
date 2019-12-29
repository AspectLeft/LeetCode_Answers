class Solution {
    class CharCount implements Comparable<CharCount> {
        char c;
        int count;
        
        CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
        
        @Override
        public int compareTo(CharCount b) {
            return Integer.compare(b.count, count);
        }
    }
    
    public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        int[] table = new int[26];
        for (char c: s.toCharArray()) {
            table[c - 'a']++;
        }
        for (int count: table) {
            if ((count - 1) * k + 1 > s.length()) return "";
        }
        Queue<CharCount> banned = new LinkedList<>();
        PriorityQueue<CharCount> queue = new PriorityQueue<>();
        for (char c = 'a'; c <= 'z'; ++c) {
            if (table[c - 'a'] == 0) continue;
            queue.add(new CharCount(c, table[c - 'a']));
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            if (queue.isEmpty()) return "";
            CharCount cc = queue.poll();
            builder.append(cc.c);
            cc.count--;
            banned.add(cc);
            if (banned.size() == k) {
                CharCount cc2 = banned.remove();
                if (cc2.count > 0) {
                    queue.add(cc2);
                }
            }
        }
        return builder.toString();
    }
}
