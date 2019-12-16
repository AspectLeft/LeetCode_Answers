class Solution {
    private String getKey(String s) {
        int delta = s.charAt(0) - 'a';
        if (delta == 0) return s;
        StringBuilder builder = new StringBuilder();
        char c;
        for (int i = 0; i < s.length(); ++i) {
            c = s.charAt(i);
            c -= delta;
            if (c < 'a') c += 26;
            builder.append(c);
        }
        return builder.toString();
    }
    
    
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s: strings) {
            String key = getKey(s);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }
        return new ArrayList<>(map.values());
    }
}
