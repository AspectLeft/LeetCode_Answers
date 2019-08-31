class Solution {
    
    class OddPair implements Comparable<OddPair>{
        int i, v;

        OddPair(int i, int v) {
            this.i = i;
            this.v = v;
        }

        @Override
        public int compareTo(OddPair o) {
            int a = Integer.compare(v, o.v);
            if (a == 0) {
                return Integer.compare(o.i, i);
            }
            return a;
        }
    }

    
    class EvenPair implements Comparable<EvenPair>{
        int i, v;

        EvenPair(int i, int v) {
            this.i = i;
            this.v = v;
        }

        @Override
        public int compareTo(EvenPair o) {
            int a = Integer.compare(v, o.v);
            if (a == 0) {
                return Integer.compare(i, o.i);
            }
            return a;
        }
    }

    public int oddEvenJumps(int[] A) {
        TreeMap<EvenPair, Boolean> evenMap = new TreeMap<>();
        TreeMap<OddPair, Boolean> oddMap = new TreeMap<>();
        int n = A.length;
        evenMap.put(new EvenPair(n - 1, A[n - 1]), true);
        oddMap.put(new OddPair(n - 1, A[n - 1]), true);
        int count = 1;
        for (int i = n - 2; i >= 0; --i) {
            EvenPair evenPair = new EvenPair(i, A[i]);
            OddPair oddPair = new OddPair(i, A[i]);

            Map.Entry<EvenPair, Boolean> odd = evenMap.ceilingEntry(evenPair);

            Map.Entry<OddPair, Boolean> even = oddMap.floorEntry(oddPair);

            if (odd != null && odd.getValue()) {
                count++;
                //System.out.println("i " + i);
                //System.out.println("next " + odd.getKey().i);
                oddMap.put(oddPair, true);
            }
            else {
                oddMap.put(oddPair, false);
            }

            if (even != null && even.getValue()) {
                evenMap.put(evenPair, true);
            }
            else {
                //System.out.println("i " + i);
                evenMap.put(evenPair, false);
            }

        }
        return count;
    }
}
