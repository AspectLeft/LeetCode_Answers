class Solution {
    private StringBuilder builder = new StringBuilder();
    private Set<String> set = new HashSet<>();
    private char[] arr;
    private int length;
    private int editCount;
    
    private void dfs(int index, int balance, int edited) {
        if (index == length) {
            if (balance == 0 && edited <= editCount) {
                set.add(builder.toString());
                editCount = edited;
            }
            return;
        }
        if (edited > editCount) return;
        if (arr[index] != '(' && arr[index] != ')') {
            builder.append(arr[index]);
            dfs(index + 1, balance, edited);
            builder.deleteCharAt(builder.length() - 1);
        }
        else if (arr[index] == '(') {
            builder.append('(');
            dfs(index + 1, balance + 1, edited);
            builder.deleteCharAt(builder.length() - 1);
            
            dfs(index + 1, balance, edited + 1);
        }
        else {
            if (balance > 0) {
                builder.append(')');
                dfs(index + 1, balance - 1, edited);
                builder.deleteCharAt(builder.length() - 1);
            }
            
            dfs(index + 1, balance, edited + 1);
        }
    }
    
    public List<String> removeInvalidParentheses(String s) {
        arr = s.toCharArray();
        length = arr.length;
        editCount = length;
        List<String> output = new ArrayList<>();
        dfs(0, 0, 0);
        output.addAll(set);
        return output;
    }
}
