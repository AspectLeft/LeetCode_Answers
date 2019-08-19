class Solution {
    int P;
    
    public int findRotateSteps(String ring, String key) {
        if (key == null || key.length() == 0) return 0;
        P = ring.length();
        // The mininum of steps opt.get(k).get(i):
        // key.substring(0, k + 1), ending at ring.charAt(i);
        List<Map<Integer, Integer>> opt = new ArrayList<>();
        // The indices of each character in ring
        Map<Character, List<Integer>> map = new HashMap<>();
        char c;
        for (int i = 0; i < ring.length(); ++i) {
            c = ring.charAt(i);
            List<Integer> indices = map.get(c);
            if (indices == null) {
                indices = new ArrayList<>();
                map.put(c, indices);
            }
            indices.add(i);
        }

        Map<Integer, Integer> init = new HashMap<>();
        c = key.charAt(0);
        for (int id: map.get(c)) {
            init.put(id, Math.min(id, P - id));
            System.out.println("init " + id + " " + init.get(id));
        }
        opt.add(init);
        for (int k = 1; k < key.length(); ++k) {
            Map<Integer, Integer> optk = new HashMap<>();
            opt.add(optk);
            c = key.charAt(k);
            List<Integer> indices = map.get(c);
            for (Map.Entry<Integer, Integer> entry: opt.get(k - 1).entrySet()) {
                int i = entry.getKey();
                int j, j1 = 0;
                boolean tie = false;
                if (indices.size() == 1) {
                    j = indices.get(0);
                }
                else if (i <= indices.get(0) || i >= indices.get(indices.size() - 1)) {
                    int l = indices.size() - 1, r = 0;
                    System.out.println("tie");
                    tie = true;
                    j = indices.get(l);
                    j1 = indices.get(r);
                }
                else {
                    int l = 0, r = indices.size() - 1, mid;
                    while (l + 1 < r) {
                        mid = (l + r) / 2;
                        if (i > indices.get(mid)) {
                            l = mid;
                        }
                        else {
                            r = mid;
                        }
                    }
                    if (l == r) j = indices.get(l);
                    else {
                        System.out.println("tie");
                        tie = true;
                        j = indices.get(l);
                        j1 = indices.get(r);
                    }
                }
                System.out.println("j " + j);
                int step = entry.getValue();
                step += dist(i, j);
                optk.put(j, Math.min(optk.getOrDefault(j, Integer.MAX_VALUE), step));
                if (tie) {
                    step = entry.getValue() + dist(i, j1);
                    optk.put(j1, Math.min(optk.getOrDefault(j1, Integer.MAX_VALUE), step));
                }
            }
        }
        int minStep = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> entry: opt.get(key.length() - 1).entrySet()) {
            minStep = Math.min(minStep, entry.getValue());
        }
        return minStep + key.length();
    }

    private int closer(int i, int j1, int j2) {
        int d1 = dist(i, j1), d2 = dist(i, j2);
        return d1 < d2 ? j1 : j2;
    }
    
    private int dist(int i, int j) {
        int d = Math.abs(i - j);
        return Math.min(d, P - d);
    }
}
