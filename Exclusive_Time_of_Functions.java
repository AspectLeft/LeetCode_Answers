class Solution {
    
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] output = new int[n];
        Stack<Integer> idStack = new Stack<>();
        int prevTime = 0;
        for (String log: logs) {
            String[] fields = log.split(":");
            int id = Integer.parseInt(fields[0]);
            String type = fields[1];
            int time = Integer.parseInt(fields[2]);
            
            if (type.equals("start")) {
                if (!idStack.empty()) {
                    output[idStack.peek()] += time - prevTime;
                }
                prevTime = time;
                idStack.push(id);
            }
            else {
                time++;
                output[id] += time - prevTime;
                prevTime = time;
                idStack.pop();
            }
        }
        return output;
    }
}
