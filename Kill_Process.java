class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> cpidListMap = new HashMap<>();
        for (int i = 0; i < pid.size(); ++i) {
            int p = pid.get(i);
            int pp = ppid.get(i);
            List<Integer> cpidList = cpidListMap.get(pp);
            if (cpidList == null) {
                cpidList = new ArrayList<>();
                cpidListMap.put(pp, cpidList);
            }
            cpidList.add(p);
        }
        List<Integer> result = new ArrayList<>();
        dfs(cpidListMap, kill, result);
        return result;
    }
    
    private void dfs(Map<Integer, List<Integer>> cpidListMap, int p,
                    List<Integer> result) {
        result.add(p);
        List<Integer> cpidList = cpidListMap.get(p);
        if (cpidList == null) return;
        for (int cp: cpidList) {
            dfs(cpidListMap, cp, result);
        }
    }
}
