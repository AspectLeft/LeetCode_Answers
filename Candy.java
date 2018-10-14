class Solution {
    class ArrayIndexComparator implements Comparator<Integer> {
        private final int[] a;
        ArrayIndexComparator(int[] a) {
            this.a = a;
        }
        public Integer[] createIndexArray() {
            Integer[] indexes = new Integer[a.length];
            for (int i = 0; i < a.length; i++)
                indexes[i] = i;
            return indexes;
        }
        @Override
        public int compare(Integer index1, Integer index2)
        {
            // Autounbox from Integer to int to use as array indexes
            if (a[index1] < a[index2])
                return -1;
            return a[index1] == a[index2] ? 0 : 1;
        }
    }
    
    private void refine(int[] ratings) {
        int min = Integer.MAX_VALUE;
        for (int v: ratings)
            if (v < min) min = v;
        
        for (int i = 0; i < ratings.length; ++i)
            ratings[i] += 1 - min;
        
        
    }
    
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        refine(ratings);
        
        List<Integer> list = new ArrayList<>();
        list.add(ratings[0]);
        int prev = ratings[0];
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] == prev)
                list.add(0);
            list.add(ratings[i]);
            prev = ratings[i];
        }
        ratings = new int[list.size()];
        for (int i = 0; i < ratings.length; ++i)
            ratings[i] = list.get(i);
        
        ArrayIndexComparator comparator = new ArrayIndexComparator(ratings);
        Integer[] indices = comparator.createIndexArray();
        Arrays.sort(indices, comparator);
        for (int index: indices) {
            if (ratings[index] == 0) continue;
            int l = (index > 0) ? ratings[index - 1] : Integer.MAX_VALUE;
            int r = (index < ratings.length - 1) ? ratings[index + 1] : Integer.MAX_VALUE;
            if (ratings[index] <= l && ratings[index] <= r)
                ratings[index] = 1;
            else if (ratings[index] <= l)
                ratings[index] = r + 1;
            else if (ratings[index] <= r)
                ratings[index] = l + 1;
            else ratings[index] = 1 + (l > r ? l : r);
        }
        int sum = 0;
        for (int candy: ratings)
            sum += candy;
        return sum;
    }
    
    
}
