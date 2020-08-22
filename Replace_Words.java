class Solution {
    class Node {
        private boolean val;
        private Node left, right, mid;
        char c;
        
        private String s;
    }
    
    private Node root;

    /** Inserts a word into the trie. */
    public void insert(String word) {
        root = insert(root, word, 0);
    }
    
    private Node insert(Node x, String word, int d) {
        char c = word.charAt(d);
        if (x != null && c == x.c && x.val) return x;
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = insert(x.left, word, d);
        else if (c > x.c) x.right = insert(x.right, word, d);
        else if (d < word.length() - 1) {
            x.mid = insert(x.mid, word, d + 1);
            return x;
        }
        else {
            x.val = true;
            x.s = word;
            x.mid = null;
        }
        return x;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node x = search(root, word, 0);
        return x != null && x.val;
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
    
    private Node searchPrefix(Node x, String word, int d) {
        if (x == null || d == word.length()) return null;
        char c = word.charAt(d);
        if (c < x.c) return searchPrefix(x.left, word, d);
        if (c > x.c) return searchPrefix(x.right, word, d);
        if (x.val) {
            return x;
        }
        return searchPrefix(x.mid, word, d + 1);
    }
    
    public String searchPrefix(String word) {
        Node x = searchPrefix(root, word, 0);
        if (x == null) return word;
        return x.s;
    }
    
    public String replaceWords(List<String> dictionary, String sentence) {
        for (String root: dictionary) {
            insert(root);
        }
        return Arrays.stream(sentence.split(" ")).map(word -> searchPrefix(word)).collect(Collectors.joining(" "));
    }
}
