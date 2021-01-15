class Solution {
    public String nextClosestTime(String time) {
        
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < time.length(); ++i) {
            if (i == 2) continue;
            set.add(time.charAt(i));
        }
        
        char m2 = time.charAt(4);
        List<Character> digits = new ArrayList<>(set);
        Collections.sort(digits);
        for (char c: digits) {
            if (c > m2) {
                return time.substring(0, 4) + c;
            }
        }
        
        char m1 = time.charAt(3);
        for (char c: digits) {
            if (c > '5') break;
            if (c > m1) {
                return time.substring(0, 3) + c + digits.get(0);
            }
        }
        
        char h1 = time.charAt(0), h2 = time.charAt(1);
        for (char c: digits) {
            if (h1 == '2' && c > '3') break;
            if (c > h2) {
                return time.substring(0, 1) + c + ":" + digits.get(0) + digits.get(0);
            }
        }
        
        if (h1 == '0') {
            if (digits.size() == 1 || digits.get(1) > '2') return "00:00";
            
            return digits.get(1) + "0:00";
        }
        char min = digits.get(0);
        if (h1 == '1') {
            if (digits.size() == 1 || digits.get(1) > '2') return "11:11";
            
            
            return String.format("%c%c:%c%c", digits.get(1), min, min, min);
        }
        return String.format("%c%c:%c%c", min, min, min, min);
    }
}
