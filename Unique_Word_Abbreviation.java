class ValidWordAbbr {
    Map<String, String> map = new HashMap<>();
    
    public ValidWordAbbr(String[] dictionary) {
        Set<String> set = new HashSet<>(Arrays.asList(dictionary));
        for (String word: set) {
            String abbr = toAbbr(word);
            if (map.containsKey(abbr)) {
                map.put(abbr, "");
            }
            else {
                map.put(abbr, word);
            }
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = toAbbr(word);
        String word2 = map.get(abbr);
        return word2 == null || word2.equals(word);
    }
    
    private String toAbbr(String word) {
        if (word.length() < 3) return word;
        return String.format("%c%d%c", word.charAt(0), word.length() - 2,
                             word.charAt(word.length() - 1));
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */
