class Solution {
    public String predictPartyVictory(String senate) {
        LinkedList<Character> list = new LinkedList<>();
        int r = 0, d = 0;
        for (int i = 0; i < senate.length(); ++i) {
            list.addLast(senate.charAt(i));
            if (senate.charAt(i) == 'R') {
                r++;
            }
            else {
                d++;
            }
        }
        
        int banR = 0, banD = 0;
        
        char c;
        while (true) {
            c = list.removeFirst();
            if (c == 'R') {
                if (banR > 0) {
                    banR--;
                    continue;
                }
                if (d == 0) return "Radiant";
                banD++;
                d--;
            }
            else {
                if (banD > 0) {
                    banD--;
                    continue;
                }
                if (r == 0) return "Dire";
                banR++;
                r--;
            }
            list.addLast(c);
        }
    }
}
