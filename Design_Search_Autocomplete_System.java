class AutocompleteSystem {
    class History implements Comparable<History> {
        String s;
        int freq;
        
        History(String s, int freq) {
            this.s = s;
            this.freq = freq;
        }
        
        public char charAt(int i) {
            return s.charAt(i);
        }
        
        public int length() {
            return s.length();
        }
        
        @Override
        public int compareTo(History b) {
            int freqDiff = Integer.compare(b.freq, freq);
            if (freqDiff != 0) return freqDiff;
            return s.compareTo(b.s);
        }
    }
    
    // TST, Tenary Search Trie
    class Node {
        private boolean val;
        private Node left, right, mid;
        char c;
        private History history;
    }
    
    private Node root;
    
    /** Inserts a word into the trie. */
    public void insert(History word) {
        root = insert(root, word, 0);
    }
    
    private Node insert(Node x, History word, int d) {
        char c = word.charAt(d);
        if (x == null) { x = new Node(); x.c = c; }
        if (c < x.c) x.left = insert(x.left, word, d);
        else if (c > x.c) x.right = insert(x.right, word, d);
        else if (d < word.length() - 1) x.mid = insert(x.mid, word, d + 1);
        else {
            x.val = true;
            if (x.history == null) {
                x.history = word;
            }
            else {
                x.history.freq += word.freq;
            }
        }
        return x;
    }
    
    
    
    /** Returns if the word is in the trie. */
    public boolean search(History word) {
        Node x = search(root, word, 0);
        return x != null && x.val;
    }
    
    private Node search(Node x, History word, int d) {
        if (x == null) return null;
        char c = word.charAt(d);
        if (c < x.c) return search(x.left, word, d);
        if (c > x.c) return search(x.right, word, d);
        if (d < word.length() - 1) return search(x.mid, word, d + 1);
        return x;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(History prefix) {
        Node x = search(root, prefix, 0);
        return x != null;
    }

    
    private String currentWord = "";
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        for (int i = 0; i < sentences.length; ++i) {
            insert(new History(sentences[i], times[i]));
        }
    }
    
    private void collect(Node x, PriorityQueue<History> container) {
        if (x == null) return;
        if (x.val) {
            container.add(x.history);
        }
        collect(x.left, container);
        collect(x.right, container);
        collect(x.mid, container);
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            insert(new History(currentWord, 1));
            currentWord = "";
            return new ArrayList<>();
        }
        currentWord = currentWord + c;
        
        Node prev = search(root, new History(currentWord, 0), 0);
        if (prev == null) {
            return new ArrayList<>();
        }
        PriorityQueue<History> container = new PriorityQueue<>();
        if (prev.val) {
            container.add(prev.history);
        }
        collect(prev.mid, container);
        
        List<String> output = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            if (container.isEmpty()) break;
            output.add(container.poll().s);
        }
        return output;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
