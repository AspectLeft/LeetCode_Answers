class Solution {
    // TST, Ternary search trie
    
    private static class Node {
        private char c;
        private Node left, mid, right;
        private boolean val = false;
        
        Node() {}
    }
    
    private Node root;
    
    private boolean get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.val;
    }
    
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        char c = key.charAt(d);
        
        if      (c < x.c)              return get(x.left,  key, d);
        else if (c > x.c)              return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid,   key, d+1);
        else                           return x;
    }
    
    private void put(String key) {
        root = put(root, key, 0);
    }
    
    private Node put(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if      (c < x.c)               x.left  = put(x.left,  key, d);
        else if (c > x.c)               x.right = put(x.right, key, d);
        else if (d < key.length() - 1)  x.mid   = put(x.mid,   key, d+1);
        else                            x.val   = true;
        return x;
    }
    
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new LinkedList<String>();
        Node x = get(root, prefix, 0);
        if (x == null) return queue;
        if (x.val) queue.add(prefix);
        collect(x.mid, new StringBuilder(prefix), queue);
        return queue;
    }
    
    private void collect(Node x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.val) queue.add(prefix.toString() + x.c);
        collect(x.mid,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }
    
    String[] words;
    int n;
    
    List<List<String>> output = new ArrayList<>();
    
    private void dfs(LinkedList<String> table) {
        if (table.size() == n) {
            List<String> result = new ArrayList<>();
            result.addAll(table);
            output.add(result);
            return;
        }
        
        StringBuilder prefixBuilder = new StringBuilder();
        
        int offset = table.size();
        for (int i = 0; i < table.size(); ++i) {
            prefixBuilder.append(table.get(i).charAt(offset));
        }
        Iterable<String> nextList;
        if (prefixBuilder.length() == 0) {
            nextList = Arrays.asList(words);
        }
        else {
            nextList = keysWithPrefix(prefixBuilder.toString());
        }
        
        for (String next: nextList) {
            table.addLast(next);
            dfs(table);
            table.removeLast();
        }
        
    }
    
    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        n = words[0].length();
        this.words = words;
        
        for (String word: words) {
            put(word);
        }
        
        dfs(new LinkedList<>());
        
        return output;
    }
}
