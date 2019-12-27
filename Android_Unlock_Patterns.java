class Solution {
    private boolean hasThrough(int a, int b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        switch (a) {
            case 1:
                return b == 3 || b == 7 || b == 9;
            case 2:
                return b == 8;
            case 3:
                return b == 9 || b == 7;
            case 4:
                return b == 6;
            case 7:
                return b == 9;
            default:
                return false;
        }
    }
    
    
    public int numberOfPatterns(int m, int n) {
        Set<List<Integer>> level = new HashSet<>();
        level.add(new ArrayList<>());
        int count = 0;
        for (int k = 1; k <= n; ++k) {
            Set<List<Integer>> nextLevel = new HashSet<>();
            for (List<Integer> path: level) {
                for (int next = 1; next <= 9; ++next) {
                    if (path.contains(next)) continue;
                    if (path.isEmpty()) {
                        List<Integer> nextPath = new ArrayList<>();
                        nextPath.add(next);
                        nextLevel.add(nextPath);
                        continue;
                    }
                    int tail = path.get(path.size() - 1);
                    if (hasThrough(next, tail)) {
                        if (!path.contains((next + tail) / 2)) {
                            continue;
                        }
                    }
                    List<Integer> nextPath = new ArrayList<>(path);
                    nextPath.add(next);
                    nextLevel.add(nextPath);
                }
            }
            level = nextLevel;
            // System.out.println(level.size());
            if (k >= m) {
                count += level.size();
            }
        }
        return count;
    }
}
