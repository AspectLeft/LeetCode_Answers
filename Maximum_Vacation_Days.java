class Solution {
    public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights == null || flights.length == 0
           || days == null || days.length == 0 || days[0].length == 0) 
            return 0;
        int N = flights.length;
        int K = days[0].length;
        
        List<List<Integer>> prevList = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            prevList.add(new ArrayList<>());
            prevList.get(i).add(i);
        }
        
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (flights[i][j] == 1) {
                    prevList.get(j).add(i);
                }
            }
        }
        
        int[] opt = new int[N];
        int[] tmp = new int[N];
        boolean[] valid = new boolean[N];
        valid[0] = true;
        
        for (int d = 0; d < K; ++d) {
            for (int j = 0; j < N; ++j) {
                tmp[j] = 0;
                for (int i: prevList.get(j)) {
                    if (valid[i]) valid[j] = true;
                    tmp[j] = Math.max(tmp[j], opt[i]);
                }
                tmp[j] += days[j][d];
                if (!valid[j]) tmp[j] = 0;
            }
            for (int i = 0; i < N; ++i) {
                opt[i] = tmp[i];
            }
        }
        int result = 0;
        for (int i = 0; i < N; ++i) {
            if (!valid[i]) continue;
            if (opt[i] > result) result = opt[i];
        }
        return result;
    }
}
