class Solution {
    public String[] expand(String S) {
        List<List<Character>> dict = new ArrayList<>();
        char c;
        for (int i = 0, j; i < S.length(); ++i) {
            List<Character> options = new ArrayList<>();
            dict.add(options);
            c = S.charAt(i);
            if (c == '{') {
                j = i + 1;
                while (S.charAt(j) != '}') {
                    if (S.charAt(j) != ',') {
                        options.add(S.charAt(j));
                    }
                    j++;
                }
                Collections.sort(options);
                i = j;
            }
            else {
                options.add(c);
            }
        }
        StringBuilder builder = new StringBuilder();
        List<String> result = new ArrayList<>();
        dfs(builder, 0, dict, result);
        String[] output = new String[result.size()];
        for (int i = 0; i < output.length; ++i) {
            output[i] = result.get(i);
        }
        return output;
    }
    
    
    private void dfs(StringBuilder builder, int i, List<List<Character>> dict, List<String> result) {
        if (i == dict.size()) {
            result.add(builder.toString());
            return;
        }
        for (char c: dict.get(i)) {
            builder.append(c);
            dfs(builder, i + 1, dict, result);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
