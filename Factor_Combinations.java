class Solution {
    class Factor {
        int p, count;
        Factor(int p, int count) {
            this.p = p;
            this.count = count;
        }
    }
    
    
    public List<List<Integer>> getFactors(int n) {
        if (n < 4) return new ArrayList<>();
        int t = n;
        List<Factor> factors = new ArrayList<>();
        for (int p = 2; p <= t; ++p) {
            int count = 0;
            while (t % p == 0) {
                t /= p;
                count++;
            }
            if (count > 0) {
                factors.add(new Factor(p, count));
            }
        }
        if (factors.size() == 0) return new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        set.add(new ArrayList<>());
        for (Factor factor: factors) {
            for (int i = 0; i < factor.count; ++i) {
                Set<List<Integer>> nextSet = new HashSet<>();
                for (List<Integer> comb: set) {
                    ArrayList<Integer> appending = new ArrayList<>(comb);
                    appending.add(factor.p);
                    
                    int kk = appending.size() - 1;
                    while (kk > 0 && appending.get(kk) < appending.get(kk - 1)) {
                        int tmp = appending.get(kk);
                        appending.set(kk, appending.get(kk - 1));
                        appending.set(kk - 1, tmp);
                        kk--;
                    }
                    
                    
                    nextSet.add(appending);
                    
                    for (int j = comb.size() - 1; j >= 0; --j) {
                        if (j < comb.size() - 1 
                            && comb.get(j) == comb.get(j + 1)) {
                            continue;
                        }
                        ArrayList<Integer> mul = new ArrayList<>(comb);
                        mul.set(j, mul.get(j) * factor.p);
                        int k = j;
                        while (k < mul.size() - 1 && mul.get(k) > mul.get(k + 1)) {
                            int tmp = mul.get(k);
                            mul.set(k, mul.get(k + 1));
                            mul.set(k + 1, tmp);
                            k++;
                        }
                        nextSet.add(mul);
                    }
                }
                set = nextSet;
            }
        }
        List<Integer> self = new ArrayList<>();
        self.add(n);
        set.remove(self);
        List<List<Integer>> output = new ArrayList<>();
        output.addAll(set);
        return output;
    }
}
