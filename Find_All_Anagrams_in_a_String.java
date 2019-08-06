class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> indices = new ArrayList<>();
        if (s.length() < p.length()) return indices;
        int[] plate = new int[26], pivot = new int[26];
        int index = 0;
        int stride = p.length();
        int diff = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < stride; ++i) {
            plate[chars[i] - 'a']++;
            pivot[p.charAt(i) - 'a']++;
        }
        for (int i = 0; i < plate.length; ++i) {
            diff += Math.abs(plate[i] - pivot[i]);
        }
        if (diff == 0) indices.add(index);

        for (index = 1; index + stride <= chars.length; ++index) {
            int c = chars[index - 1] - 'a';
            plate[c]--;
            if (plate[c] >= pivot[c]) {
                diff--;
            }
            else {
                diff++;
            }
            
            c = chars[index + stride - 1] - 'a';
            plate[c]++;
            if (plate[c] <= pivot[c]) {
                diff--;
            }
            else {
                diff++;
            }
            
            if (diff == 0) indices.add(index);
        }
        return indices;
    }


}
