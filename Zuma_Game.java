class Solution {
    Map<Character, Integer> map = new HashMap<>();
    int used = 0;

    public int findMinStep(String board, String hand) {
        for (char c: hand.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return dfs(board);
    }

    private int dfs(String board) {
        if (board.isEmpty()) {
            return used;
        }
        char c;
        int minUse = Integer.MAX_VALUE;
        for (int i = 0; i < board.length(); ++i) {
            c = board.charAt(i);
            if (i > 0 && c == board.charAt(i - 1)) continue;
            if (map.get(c) == null || map.get(c) == 0) continue;
            map.put(c, map.get(c) - 1);
            used++;
            int tmp = dfs(crack(board.substring(0, i) + c + board.substring(i)));
            if (tmp > 0 && tmp < minUse) {
                minUse = tmp;
            }
            used--;
            map.put(c, map.get(c) + 1);
        }
        return minUse < Integer.MAX_VALUE ? minUse : -1;
    }

    private String crack(String board) {
        if (board.length() < 3) return board;
        boolean flag = false;
        int i, j = 0;
        char c;
        for (i = 2; i < board.length(); ++i) {
            c = board.charAt(i);
            if (board.charAt(i - 2) == c && board.charAt(i - 1) == c) {
                j = i + 1;
                while (j < board.length() && board.charAt(j) == c) j++;
                flag = true;
                break;
            }
        }
        if (flag) {
            return crack(board.substring(0, i - 2) + board.substring(j));
        }
        else {
            return board;
        }
    }
}
