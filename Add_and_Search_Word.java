class WordDictionary {
    
    class Node {
        private boolean val;
        private Node left, right, mid;
        char c;
    }
    
    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        root = insert(root, word, 0);
    }
    
    private Node insert(Node x, String word, int d) {
        char c = word.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = insert(x.left, word, d);
        else if (c > x.c) x.right = insert(x.right, word, d);
        else if (d < word.length() - 1) x.mid = insert(x.mid, word, d + 1);
        else x.val = true;
        return x;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        Node x = search(root, word, 0);
        return x != null && x.val;
    }
    
    private Node search(Node x, String word, int d) {
        if (x == null) return null;
        char c = word.charAt(d);
        if (c == '.') {
            Node r = search(x.left, word, d);
            if (r != null && r.val) return r;
            r = search(x.right, word, d);
            if (r != null && r.val) return r;
            if (d < word.length() - 1) return search(x.mid, word, d + 1);
            return x;
        }
        if (c < x.c) return search(x.left, word, d);
        if (c > x.c) return search(x.right, word, d);
        if (d < word.length() - 1) return search(x.mid, word, d + 1);
        return x;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
