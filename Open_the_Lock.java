class Solution {
    class State implements Comparable<State>{
        String current;
        int step;
        String target;
        
        State(String current, int step, String target) {
            this.current = current;
            this.step = step;
            this.target = target;
        }
        
        private int diff() {
            int d = 0;
            int delta;
            for (int i = 0; i < 4; ++i) {
                delta = Math.abs(current.charAt(i) - '0' - (target.charAt(i) - '0'));
                if (delta > 6) delta = 10 - delta;
                d += delta;
            }
            return d;
        }
        
        @Override
        public int compareTo(State o) {
            return Integer.compare(step + diff(), o.step + o.diff());
        }
        
    }
    
    
    public int openLock(String[] deadends, String target) {
        PriorityQueue<State> queue = new PriorityQueue<State>();
        queue.add(new State("0000", 0, target));
        Set<String> set = new HashSet<>();
        for (String deadend: deadends) {
            set.add(deadend);
        }
        if (set.contains("0000")) return -1;
        int[] dirs = new int[]{-1, 1};
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (state.current.equals(target)) {
                return state.step;
            }
            for (int i = 0; i < 4; ++i) {
                for (int dir: dirs) {
                    char c = (char) (state.current.charAt(i) + dir);
                    if (c < '0') c = '9';
                    else if (c > '9') c = '0';
                    String current = state.current.substring(0, i) 
                        + c + state.current.substring(i + 1);
                    if (set.contains(current)) continue;
                    set.add(current);
                    queue.add(new State(current, state.step + 1, target));
                }
            }
        }
        return -1;
    }
    
    private int diff(String current, String target) {
        int d = 0;
        int delta;
        for (int i = 0; i < 4; ++i) {
            delta = Math.abs(current.charAt(i) - '0' - (target.charAt(i) - '0'));
            if (delta > 6) delta = 10 - delta;
            d += delta;
        }
        return d;
    }
}
