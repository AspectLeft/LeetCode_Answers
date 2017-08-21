class Solution {
    class IntegerListWithSum {
        List<Integer> list;
        int sum;
        IntegerListWithSum() {
            list = new ArrayList<>();
            sum = 0;
        }
        IntegerListWithSum(IntegerListWithSum b) {
            this.list = new ArrayList<>(b.list);
            this.sum = b.sum;
        }
        public void add(int i) {
            list.add(i);
            sum += i;
        }
        
        public int getLast() {
            return list.get(list.size() - 1);
        }
        
        
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        IntegerListWithSum solution;
        IntegerListWithSum newSolution;
        List<List<Integer>> solutions = new ArrayList<>();
        Queue<IntegerListWithSum> queue = new ArrayDeque<>();
        int newSum;
        int lastCandidate;

        for (int candidate : candidates) {
            if (candidate < target) {
                solution = new IntegerListWithSum();
                solution.add(candidate);
                queue.add(solution);
            }
            else if (candidate == target) {
                solution = new IntegerListWithSum();
                solution.add(candidate);
                solutions.add(solution.list);
            }
        }
        while (!queue.isEmpty()) {
            solution = queue.remove();
            
            lastCandidate = solution.getLast();
            
            for (int candidate: candidates) {
                if (candidate >= lastCandidate) {
                    newSum = solution.sum + candidate;
                    if (newSum < target) {
                        newSolution = new IntegerListWithSum(solution);
                        newSolution.add(candidate);
                        queue.add(newSolution);
                    }
                    else if (newSum == target) {
                        solution.add(candidate);
                        solutions.add(solution.list);
                    }
                }
            }
        }
        return solutions;
    }
}
