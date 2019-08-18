class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        if (k == 0 || Profits == null || Profits.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> -o));
        int[][] projects = new int[Profits.length][2];
        for (int i = 0; i < Profits.length; ++i) {
            projects[i][0] = Profits[i];
            projects[i][1] = Capital[i];
        }
        Arrays.sort(projects, Comparator.comparingInt(p -> p[1]));
        int id = 0;
        while (k > 0) {
            while (id < projects.length && W >= projects[id][1]) {
                queue.offer(projects[id][0]);
                id++;
            }
            if (queue.isEmpty()) break;
            int profit = queue.poll();
            W += profit;
            k--;
        }
        return W;
    }
}
