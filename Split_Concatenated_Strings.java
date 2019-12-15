class Solution {
    private String[] strs;
    private String[] sup;
    private String[] rev;
    private int maxStep;
    
    public String splitLoopedString(String[] strs) {
        if (strs == null) return "";
        List<String> list = new ArrayList<>();
        for (String s: strs) {
            if (s != null && s.length() != 0) list.add(s);
        }
        if (strs.length > list.size()) {
            strs = new String[list.size()];
            if (strs.length == 0) return "";
            for (int i = 0; i < strs.length; ++i) {
                strs[i] = list.get(i);
            }
        }
        
        this.strs = strs;
        sup = new String[strs.length];
        rev = new String[strs.length];
        for (int i = 0; i < strs.length; ++i) {
            rev[i] = new StringBuilder(strs[i]).reverse().toString();
            sup[i] = supper(strs[i], rev[i]);
            maxStep += strs[i].length();
        }
        
        int bestI = 0;
        int bestJ = 0;
        boolean bestFlip = false;
        
        for (int i = 0; i < strs.length; ++i) {
            for (int j = 0; j < strs[i].length(); ++j) {
                if (larger(bestI, bestJ, bestFlip, i, j, false)) {
                    bestI = i;
                    bestJ = j;
                    bestFlip = false;
                }
                
                if (larger(bestI, bestJ, bestFlip, i, j, true)) {
                    bestI = i;
                    bestJ = j;
                    bestFlip = true;
                }
            }
        }
        
        StringBuilder builder = new StringBuilder();
        String bestS = bestFlip ? rev[bestI] : strs[bestI];
        builder.append(bestS.substring(bestJ));
        for (int i = (bestI + 1) % strs.length; i != bestI; i = (i + 1) % strs.length) {
            builder.append(sup[i]);
        }
        System.out.println(bestI);
        System.out.println(bestJ);
        System.out.println(bestFlip);
        builder.append(bestS.substring(0, bestJ));
        return builder.toString();
    }
    
    private boolean larger(int bestI, int bestJ, boolean bestFlip, 
                          int i, int j, boolean flip) {
        String bestS = bestFlip ? rev[bestI] : strs[bestI];
        String s = flip ? rev[i] : strs[i];
        
        int originBestI = bestI;
        String originBestS = bestS;
        int originI = i;
        String originS = s;
        
        
        for (int step = 0; step < maxStep; ++step) {
            if (bestS.charAt(bestJ) > s.charAt(j)) return false;
            if (bestS.charAt(bestJ) < s.charAt(j)) return true;
            
            step++;
            bestJ++;
            if (bestJ == bestS.length()) {
                bestJ = 0;
                bestI++;
                if (bestI == strs.length) {
                    bestI = 0;
                }
                if (bestI == originBestI) {
                    bestS = originBestS;
                }
                else {
                    bestS = sup[bestI];
                }
            }
            
            j++;
            if (j == s.length()) {
                j = 0;
                i++;
                if (i == strs.length) {
                    i = 0;
                }
                if (i == originI) {
                    s = originS;
                }
                else {
                    s = sup[i];
                }
            }
        }
        return false;
    }
    
    private String supper(String s, String t) {
        if (s.compareTo(t) < 0) {
            return t;
        }
        return s;
    }
}
