class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) return people;
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return Integer.compare(o1[0], o2[0]);
                else return Integer.compare(o1[1], o2[1]);
            }
        });
        int n = people.length;

        int[][] output = new int[n][2];
        boolean[] taken = new boolean[n];
        int[] prev = null;
        for (int[] person: people) {
            int h = person[0];
            int k = person[1];
            if (prev != null) {
                if (prev[0] < h) {
                    int index = 0;
                    while (k > 0) {
                        if (!taken[index]) k--;
                        index++;
                    }
                    while (taken[index]) index++;
                    
                    taken[index] = true;
                    output[index] = person;
                }
                else {
                    int index = 0;
                    while (k > 0) {
                        if (!taken[index] || output[index][0] == h) k--;
                        index++;
                    }
                    while (taken[index]) index++;
                    
                    taken[index] = true;
                    output[index] = person;
                }
            }
            else {
                taken[k] = true;
                output[k] = person;
            }


            prev = person;
        }
        
        return output;
    }


}
