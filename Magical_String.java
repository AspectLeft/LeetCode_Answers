class Solution {
    public int magicalString(int n) {
        if (n <= 0) return 0;
        if (n < 4) return 1;
        LinkedList<Integer> queue = new LinkedList<>();
        int count = 1;
        int i = 3;
        queue.addLast(2);
        while (i < n) {
            if (queue.getFirst() == 1) {
                if (queue.getLast() == 1) {
                    queue.addLast(2);
                    i++;
                }
                else {
                    queue.addLast(1);
                    i++;
                    count++;
                }
            }
            else {
                if (queue.getLast() == 1) {
                    queue.addLast(2);
                    queue.addLast(2);
                    i += 2;
                }
                else {
                    queue.addLast(1);
                    queue.addLast(1);
                    i += 2;
                    count += 2;
                }
            }
            queue.removeFirst();
        }
        if (i == n + 1 && queue.getLast() == 1) count--;
        return count;
    }
}
