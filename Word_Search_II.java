class Solution {
    class Node {
        private String val;
        private Node left, right, mid;
        char c;
    }
    
    
    private Node root;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root = insert(root, word, 0);
    }
    
    private Node insert(Node x, String word, int d) {
        char c = word.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = insert(x.left, word, d);
        else if (c > x.c) x.right = insert(x.right, word, d);
        else if (d < word.length() - 1) x.mid = insert(x.mid, word, d + 1);
        else x.val = word;
        return x;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node x = search(root, word, 0);
        return x != null && x.val != null;
    }
    
    private Node search(Node x, String word, int d) {
        if (x == null) return null;
        char c = word.charAt(d);
        if (c < x.c) return search(x.left, word, d);
        if (c > x.c) return search(x.right, word, d);
        if (d < word.length() - 1) return search(x.mid, word, d + 1);
        return x;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node x = search(root, prefix, 0);
        return x != null;
    }
    
    private boolean[][] visited;
    private int m, n;
    private List<String> output;
    private char[][] board;
    private char c;
    
    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board[0] == null || board[0].length == 0 || words == null || words.length == 0)
            return new ArrayList<>();
        m = board.length;
        n = board[0].length;
        visited = new boolean[m][n];
        output = new ArrayList<>();
        this.board = board;
        for (String word: words) insert(word);
        for (int x = 0; x < m; ++x) {
            for (int y = 0; y < n; ++y) {
                dfs(x, y, root);
            }
        }
        return output;
    }
    
    private void dfs(int x, int y, Node node) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) return;
        visited[x][y] = true;
        if (node == null) {
            visited[x][y] = false;
            return;
        }
        c = board[x][y];
        if (c < node.c) {
            visited[x][y] = false;
            dfs(x, y, node.left);
            return;
        }
        if (c > node.c) {
            visited[x][y] = false;
            dfs(x, y, node.right);
            return;
        }
        if (node.val != null) {
            output.add(node.val);
            node.val = null;
        }
        dfs(x - 1, y, node.mid);
        dfs(x + 1, y, node.mid);
        dfs(x, y - 1, node.mid);
        dfs(x, y + 1, node.mid);
        visited[x][y] = false;
    }
    
}
