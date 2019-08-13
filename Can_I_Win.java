class Solution {
    class State {
        int table;
        int sum;
        State() {
        }

        public void setTrue(int i) {
            table |= (1 << i);
        }

        public void setFalse(int i) {
            table &= ~(1 << i);
        }

        public boolean get(int i) {
            return (table & (1 << i)) != 0;
        }

        @Override
        public int hashCode() {
            return table;
        }
    }

    int target;
    int size;

    Map<State, Boolean> map = new HashMap<>();

    public boolean canIWin(int size, int target) {
        if (size * (size + 1) / 2 < target) return false;
        this.target = target;
        this.size = size;
        State state = new State();
        return canWin(state);
    }

    private boolean canWin(State state) {
        Boolean memoi = map.get(state);
        if (memoi != null) return memoi;
        for (int i = size; i > 0; --i) {
            if (state.get(i)) {
                // System.out.println("continue");
                continue;
            }
            state.setTrue(i);
            state.sum += i;
            // System.out.println(state.sum);
            if (state.sum >= target) {
                // System.out.println(state.sum);

                state.setFalse(i);
                state.sum -= i;

                map.put(state, Boolean.TRUE);

                return true;
            }
            if (!canWin(state)) {
                state.setFalse(i);
                state.sum -= i;

                map.put(state, Boolean.TRUE);

                return true;
            }
            state.setFalse(i);
            state.sum -= i;
        }
        map.put(state, Boolean.FALSE);
        return false;
    }

}
