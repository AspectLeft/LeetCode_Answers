class Solution {
    class StringWithId {
        int id;
        String s;
        
        StringWithId(int id, String s) {
            this.id = id;
            this.s = s;
        }
        
    }
    
    private String toAbbr(String s) {
        if (s.length() < 4) return s;
        return String.format("%c%d%c", s.charAt(0), s.length() - 2,
                            s.charAt(s.length() - 1));
    }
    
    private int commonPrefix(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length()
              && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }
    
    public List<String> wordsAbbreviation(List<String> dict) {
        List<StringWithId> sil = new ArrayList<>();
        for (int i = 0; i < dict.size(); ++i) {
            sil.add(new StringWithId(i, dict.get(i)));
        }
        
        
        Map<String, List<StringWithId>> abbrListMap = new HashMap<>();
        
        for (StringWithId si: sil) {
            String abbr = toAbbr(si.s);
            List<StringWithId> abbrList = abbrListMap.get(abbr);
            if (abbrList == null) {
                abbrList = new ArrayList<>();
                abbrListMap.put(abbr, abbrList);
            }
            abbrList.add(si);
        }
        
        String[] result = new String[dict.size()];
        
        for (List<StringWithId> abbrList: abbrListMap.values()) {
            Collections.sort(abbrList, Comparator.comparing(o -> o.s));
            for (int i = 0; i < abbrList.size(); ++i) {
                int prefix = 0;
                StringWithId si = abbrList.get(i);
                if (i > 0) {
                    StringWithId prev = abbrList.get(i - 1);
                    prefix = Math.max(prefix, commonPrefix(si.s, prev.s));
                }
                if (i < abbrList.size() - 1) {
                    StringWithId next = abbrList.get(i + 1);
                    prefix = Math.max(prefix, commonPrefix(si.s, next.s));
                }
                
                result[si.id] = si.s.substring(0, prefix) 
                    + toAbbr(si.s.substring(prefix));
            }
        }
        
        return Arrays.asList(result);
    }
}
