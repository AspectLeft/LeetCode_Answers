class Solution {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0) return 0;
        int output = 1;
        int[] length = new int[input.length() + 1];
        int[] parent = new int[input.length() + 1];
        int[] level = new int[input.length() + 1];
        char[] arr = input.toCharArray();
        int i = 0;
        int index = 1;
        int prev = 0;
        while (i < arr.length) {
            boolean isFile = false;
            int nameLength = 0;
            while (i < arr.length && arr[i] != '\n') {
                if (arr[i] == '.') isFile = true;
                nameLength++;
                i++;
            }
            parent[index] = prev;
            length[index] = length[prev] + nameLength + 1;
            level[index] = level[prev] + 1;
            if (isFile && length[index] > output) output = length[index];
            
            prev = index;
            index++;
            
            if (i < arr.length) {
                level[index] = 1;
                i++;
                while (arr[i] == '\t') {
                    level[index]++;
                    i++;
                }
                for (int j = level[index]; j <= level[index - 1]; ++j) {
                    prev = parent[prev];
                }
            }
        }
        // System.out.println(index);
        return output - 1;
    }
}
