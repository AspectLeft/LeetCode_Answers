class Solution {
    class IntegerListWithSum2 {
        List<Integer> list;
        List<Integer> index;
        int sum;
        IntegerListWithSum2() {
            list = new ArrayList<>();
            index = new ArrayList<>();
            sum = 0;
        }
        IntegerListWithSum2(IntegerListWithSum2 b) {
            this.list = new ArrayList<>(b.list);
            this.index = new ArrayList<>(b.index);
            this.sum = b.sum;
        }
        public void add(int n, int i) {
            list.add(n);
            index.add(i);
            sum += n;
        }

        public int getLastIndex() {
            return index.get(index.size() - 1);
        }


    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        IntegerListWithSum2 solution;
        IntegerListWithSum2 newSolution;
        List<List<Integer>> solutions = new ArrayList<>();
        Queue<IntegerListWithSum2> queue = new LinkedList<>();
        int newSum;
        int lastIndex;

        for (int i = 0; i < candidates.length; ++i) {
            if (candidates[i] < target) {
                solution = new IntegerListWithSum2();
                solution.add(candidates[i], i);
                queue.add(solution);
            }
            else if (candidates[i] == target) {
                solution = new IntegerListWithSum2();
                solution.add(candidates[i], i);
                solutions.add(solution.list);
            }
        }
        while (!queue.isEmpty()) {
            solution = queue.remove();

            lastIndex = solution.getLastIndex();
            for (int i = lastIndex + 1, candidate; i < candidates.length; ++i) {
                candidate = candidates[i];
                newSum = solution.sum + candidate;
                if (newSum < target) {
                    newSolution = new IntegerListWithSum2(solution);
                    newSolution.add(candidate, i);
                    queue.add(newSolution);
                }
                else if (newSum == target) {
                    solution.add(candidate, i);
                    solutions.add(solution.list);
                }
            }
        }
        Set<List<Integer>> set = new HashSet<>();
        set.addAll(solutions);
        solutions.clear();
        solutions.addAll(set);
        return solutions;
    }


}
