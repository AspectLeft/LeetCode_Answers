class Solution {
    public int minMutation(String start, String end, String[] bank) {
        List<String> level = Arrays.asList(start);
        List<String> pool = Arrays.asList(bank);
        int count = 0;
        while (!level.isEmpty()) {
            List<String> newLevel = new ArrayList<>();
            List<String> newPool = new ArrayList<>();
            
            for (String s: level) {
                if (s.equals(end)) {
                    return count;
                }
                for (String next: pool) {
                    if (diff(s, next) == 1) {
                        newLevel.add(next);
                    }
                    else {
                        newPool.add(next);
                    }
                }
            }
            
            level = newLevel;
            pool = newPool;
            count++;
        }
        
        return -1;
    }
    
    private int diff(String a, String b) {
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}
