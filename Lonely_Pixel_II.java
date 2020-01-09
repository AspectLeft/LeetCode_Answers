class Solution {
    private String rowToString(char[] row) {
        return new String(row);
    }
    
    private int blackCount(char[] row) {
        int count = 0;
        for (char c: row) {
            if (c == 'B') count++;
        }
        return count;
    }
    
    public int findBlackPixel(char[][] picture, int N) {
        if (picture == null || picture.length == 0 || picture[0].length == 0)
            return 0;
        int count = 0;
        int m = picture.length;
        int n = picture[0].length;
        
        Map<String, Set<Integer>> rowMap = new HashMap<>();
        List<Set<Integer>> rowList = new ArrayList<>();
        
        for (int i = 0; i < m; ++i) {
            String row = rowToString(picture[i]);
            Set<Integer> idSet = rowMap.get(row);
            if (idSet == null) {
                idSet = new HashSet<>();
                rowMap.put(row, idSet);
            }
            idSet.add(i);
            rowList.add(idSet);
        }
        
        int[] colCount = new int[n];
        for (int j = 0; j < n; ++j) {
            int colC = 0;
            for (int i = 0; i < m; ++i) {
                if (picture[i][j] == 'B') colC++;
            }
            colCount[j] = colC;
        }
        
        for (int i = 0; i < picture.length; ++i) {
            if (N != blackCount(picture[i])) continue;
            Set<Integer> idSet = rowList.get(i);
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] != 'B') continue;
                if (colCount[j] != N) continue;
                boolean flag = true;
                for (int ii = 0; ii < m; ++ii) {
                    if (ii == i) continue;
                    if (picture[ii][j] != 'B') continue;
                    if (idSet != rowList.get(ii)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) count++;
            }
        }
        
        return count;
    }
}
