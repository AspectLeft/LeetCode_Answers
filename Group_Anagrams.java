class Solution {
 
    public List<List<String>> groupAnagrams(String[] strs) {
        char[] a;
        Map<String, List<String>> map = new HashMap<>();


        for (String s: strs) {
            a = s.toCharArray();
            Arrays.sort(a);
            String key = new String(a);
            if (map.containsKey(key)) {
                map.get(key).add(s);
            }
            else {
                map.put(key, new ArrayList<>(Collections.singletonList(s)));
            }
        }
        return new ArrayList<>(map.values());
    }


}
