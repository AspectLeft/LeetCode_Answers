class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> output = new ArrayList<>();
        if (s.length() <= 10) return output;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int hashCode = 0;
        char c;
        int code = 0;
        for (int i = 0; i < 10; ++i) {
            c = s.charAt(i);
            switch (c) {
                case 'A':
                    code = 0;
                    break;
                case 'C':
                    code = 1;
                    break;
                case 'G':
                    code = 2;
                    break;
                case 'T':
                    code = 3;
                    break;
            }
            hashCode |= (code << (2 * i));
        }
        map.put(hashCode, 1);
        for (int i = 10; i < s.length(); ++i) {
            hashCode >>= 2;
            c = s.charAt(i);
            switch (c) {
                case 'A':
                    code = 0 << 18;
                    break;
                case 'C':
                    code = 1 << 18;
                    break;
                case 'G':
                    code = 2 << 18;
                    break;
                case 'T':
                    code = 3 << 18;
                    break;
            }
            hashCode |= code;
            Integer integer = map.get(hashCode);
            if (integer == null) map.put(hashCode, 1);
            else if (integer == 1) {
                output.add(s.substring(i - 9, i + 1));
                map.put(hashCode, 2);
            }
        }
        return output;
    }
}
