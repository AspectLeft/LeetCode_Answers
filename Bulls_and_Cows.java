class Solution {
    public String getHint(String secret, String guess) {
        if (secret.length() == 0) return "0A0B";
        char[] a1 = secret.toCharArray(), a2 = guess.toCharArray();
        int length = a1.length;
        int[] table = new int[10];
        for (int i = 0; i < length; ++i)
            table[a1[i] - '0']++;
        int bull = 0;
        for (int i = 0; i < length; ++i) {
            if (a1[i] == a2[i]) {
                bull++;
                table[a1[i] - '0']--;
            }
        }
        int cow = 0;
        for (int i = 0; i < length; ++i) {
            if (a2[i] != a1[i] && table[a2[i] - '0'] > 0) {
                cow++;
                table[a2[i] - '0']--;
            }
        }
        return bull + "A" + cow + "B";
    }
}
