class Solution {
    private int needsToKey(List<Integer> needs) {
        return needs.stream().reduce(0, (a, b) -> a * 10 + b);
    }
    
    private int buildKey(List<Integer> needs, int m) {
        return needsToKey(needs) * 100 + m;
    }
    
    private Map<Integer, Integer> memoi = new HashMap<>();
    
    private List<Integer> price = null;
    private List<List<Integer>> special = null;
    
    private int kinds = 0;
    
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        this.kinds = price.size();
        this.price = price;
        this.special = special;
        
        Integer answer = solve(needs, special.size() - 1);
        
        //System.out.println(needs);
        
        return answer;
    }
    
    private int solve(List<Integer> needs, int m) {
        if (m < 0) return noSpecial(needs);
        Integer key = buildKey(needs, m);
        Integer answer = memoi.get(key);
        if (answer != null) return answer;
        
        answer = noSpecial(needs);
        
        List<Integer> lastSpecial = special.get(m);
        if (applicable(needs, lastSpecial)) {
            for (int i = 0; i < needs.size(); ++i) {
                needs.set(i, needs.get(i) - lastSpecial.get(i));
            }
            answer = Math.min(answer, solve(needs, m) + lastSpecial.get(kinds));
            for (int i = 0; i < needs.size(); ++i) {
                needs.set(i, needs.get(i) + lastSpecial.get(i));
            }
        }
        
        answer = Math.min(answer, solve(needs, m - 1));
        
        memoi.put(key, answer);
        return answer;
    }
    
    private boolean applicable(List<Integer> needs, List<Integer> special) {
        for (int i = 0; i < needs.size(); ++i) {
            if (needs.get(i) < special.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    private int noSpecial(List<Integer> needs) {
        int p = 0;
        for (int i = 0; i < needs.size(); ++i) {
            p += needs.get(i) * this.price.get(i);
        }
        return p;
    }
}
