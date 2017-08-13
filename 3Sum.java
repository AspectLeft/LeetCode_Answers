public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> solutions = new ArrayList<>();
        if (nums.length < 3)
            return solutions;
        List<Integer> S = new ArrayList<>();
        for (int i : nums) {
            S.add(i);
        }
        S.sort(Integer::compareTo);
        List<Integer> S2 = new ArrayList<>();
        int dup = 0;
        int prev = S.get(0);
        for (Integer i : S) {
            if (i == prev) {
                dup += 1;
            }
            else {
                dup = 0;
            }
            if (dup < 4) {
                S2.add(i);
            }
        }


        System.out.print(S2);
        S = S2;
        List<Integer> solution;
        for (int i = 0; i < S.size() - 2; ++i) {
            int a = S.get(i), b, c;
            if (a > 0) break;
            if (i > 0 && a == S.get(i - 1)) continue;
            int l = i + 1, r = S.size() - 1;
            while (l < r) {
                b = S.get(l);
                c = S.get(r);
                if (b + c > - a) {
                    r--;
                }
                else if (b + c < -a) {
                    l++;
                }
                else {
                    l++;
                    r--;
                    solution = new ArrayList<>();
                    solution.add(a);
                    solution.add(b);
                    solution.add(c);
                    solutions.add(solution);
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
