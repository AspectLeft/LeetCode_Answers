class Solution {
    private String[] table = new String[26];
    
    public boolean wordPattern(String pattern, String str) {
        Arrays.fill(table, null);
        String[] list = str.split(" ");
        if (pattern.length() != list.length) return false;
        
        char[] arr = pattern.toCharArray();
        
        Map<String, Character> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; ++i) {
            if (map.get(list[i]) == null && table[arr[i] - 'a'] == null) {
                map.put(list[i], arr[i]);
                table[arr[i] - 'a'] = list[i];
            }
            else if (map.get(list[i]) == null || table[arr[i] - 'a'] == null)
                return false;
            else if (map.get(list[i]) != arr[i] || !list[i].equals(table[arr[i] - 'a']))
                return false;
        }
        return true;
    }
}
