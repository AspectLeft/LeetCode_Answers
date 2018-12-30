class Solution {
    
    private boolean fit(int[] out, int[] in) {
        return out[0] > in[0] && out[1] > in[1];
    }
    
    public int maxEnvelopes(int[][] envelopes) {
        
        if (envelopes == null || envelopes.length == 0) return 0;
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o2[1], o1[1]);
        });
        int[][] tail = new int[envelopes.length + 1][2];
        int size = 0;
        for (int[] envelope: envelopes) {
            int i = 0, j = size, mid;
            while (i < j) {
                mid = (i + j) / 2;
                if (fit(envelope, tail[mid]))
                    i = mid + 1;
                else j = mid;
            }
            tail[i] = envelope;
            if (i == size) size++;
        }

        return size;
    }
}
