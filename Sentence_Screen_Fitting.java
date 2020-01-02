class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        for (String word: sentence) {
            if (word.length() > cols) return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int line = 0;
        int start = 0;
        Map<Integer, Integer> linesMap = new HashMap<>();
        Map<Integer, Integer> endMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        int count = 0;
        while (true) {
            if (!map.containsKey(start)) {
                int tmp = start;
                int lines = 0;
                for (String word: sentence) {
                    if (start + word.length() > cols) {
                        lines++;
                        start = word.length() + 1;
                    }
                    else {
                        start += word.length() + 1;
                    }
                }
                if (line + lines >= rows) return count;
                map.put(tmp, line);
                linesMap.put(tmp, lines);
                endMap.put(tmp, start);
                countMap.put(tmp, count);
                
                line += lines;
                count++;
                // System.out.println(count);
            }
            else {
                int end = endMap.get(start);
                int lines = linesMap.get(start);
                int prevLine = map.get(start);
                int prevCount = countMap.get(start);
                
                int countStride = count - prevCount;
                int lineStride = line - prevLine;
                int repeat = (rows - line - 1) / lineStride;
                
                count += repeat * countStride;
                line += repeat * lineStride;
                
                while (line + lines < rows) {
                    line += lines;
                    start = end;
                    count++;
                    // System.out.println(count);
                    
                    end = endMap.get(start);
                    lines = linesMap.get(start);
                }
                return count;
            }
        }
    }
}
