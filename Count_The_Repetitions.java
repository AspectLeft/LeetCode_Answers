class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1 == null || s1.length() == 0) return 0;

        Set<Character> set1 = new HashSet<>();
        for (char c: s1.toCharArray()) set1.add(c);
        for (char c: s2.toCharArray())
            if (!set1.contains(c))
                return 0;

        int l1 = s1.length(), l2 = s2.length();
        int[][] count = new int[l1][2];
        char[] a1 = s1.toCharArray(), a2 = s2.toCharArray();
        for (int start = 0; start < l1; ++start) {
            int m1 = 1;
            int i1 = start, i2 = 0;
            boolean flag = false;
            while (i2 < l2) {
                if (a1[i1] == a2[i2]) {
                    i2++;
                }
                i1++;
                if (flag && i1 == 1) {
                    m1++;
                }
                if (i1 == l1) {
                    i1 = 0;
                    flag = true;
                }
            }
            count[start][0] = i1; // end
            count[start][1] = m1; // count
        }

        Set<Integer> startSet = new HashSet<>();
        int start = 0;
        int initM1 = 0;
        int initM2 = 0;
        int initStart;
        while (!startSet.contains(start)) {
            //System.out.println("start " + start);
            startSet.add(start);
            initM1 += count[start][1] - (start == 0 ? 0 : 1);
            initM2++;
            start = count[start][0];
        }
        //System.out.println("InitM2 " + initM2);
        initStart = start;
        int end = start;
        int loopM1 = 0;
        int loopM2 = 0;
        do {
            //System.out.println("end " + end);
            loopM1 += count[end][1] - (end == 0 ? 0 : 1);
            loopM2++;
            end = count[end][0];
        } while (end != start);
        //System.out.println("loopM1 " + loopM1);
        //System.out.println("loopM2 " + loopM2);

        int m1, m2 = 0;
        //System.out.println("InitM1 " + initM1);
        if (initM1 > n1) {
            start = 0;
            m1 = 0;
            while (m1 + count[start][1] - (start == 0 ? 0 : 1) <= n1
                    // || (m1 + count[start][1] == n1 && (count[start][1] == 0 || count[start][0] == 0))
                    ) {
                m1 += count[start][1] - (start == 0 ? 0 : 1);
                m2++;
                start = count[start][0];
            }
        }
        else {
            m1 = initM1;
            m2 = initM2;
            m2 += (n1 - m1) / loopM1 * loopM2;
            m1 += (n1 - m1) / loopM1 * loopM1;

            start = initStart;

            //System.out.println("m1 tail " + m1);
            //System.out.println("count " + count[start][1]);
            while (m1 + count[start][1] - (start == 0 ? 0 : 1) <= n1
                    // || (m1 + count[start][1] == n1 && (count[start][1] == 0 || count[start][0] == 0))
                    ) {
                m1 += count[start][1] - (start == 0 ? 0 : 1);
                m2++;
                //System.out.println("start tail " + start);
                //System.out.println("m2 tail " + m2);
                start = count[start][0];
            }
        }
        //System.out.println(m2);
        return m2 / n2;
    }

}
