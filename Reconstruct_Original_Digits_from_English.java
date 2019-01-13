class Solution {
    public String originalDigits(String s) {
        int[] table = new int[256];
        for (char c: s.toCharArray()) {
            table[c]++;
        }
        int[] count = new int[10];
        count[0] = table['z'];
        table['z'] = 0;
        table['e'] -= count[0];
        table['r'] -= count[0];
        table['o'] -= count[0];
        
        count[6] = table['x'];
        table['s'] -= count[6];
        table['i'] -= count[6];
        table['x'] = 0;
        
        count[8] = table['g'];
        table['e'] -= count[8];
        table['i'] -= count[8];
        table['g'] = 0;
        table['h'] -= count[8];
        table['t'] -= count[8];
        
        count[3] = table['h'];
        table['t'] -= count[3];
        table['h'] = 0;
        table['r'] -= count[3];
        table['e'] -= 2 * count[3];
        
        count[4] = table['r'];
        table['f'] -= count[4];
        table['o'] -= count[4];
        table['u'] -= count[4];
        table['r'] = 0;
        
        count[5] = table['f'];
        table['f'] = 0;
        table['i'] -= count[5];
        table['v'] -= count[5];
        table['e'] -= count[5];
        
        count[7] = table['v'];
        table['s'] -= count[7];
        table['e'] -= 2 * count[7];
        table['v'] = 0;
        table['n'] -= count[7];
        
        count[2] = table['w'];
        table['t'] -= count[2];
        table['w'] = 0;
        table['o'] -= count[2];
        
        count[1] = table['o'];
        table['o'] = 0;
        table['n'] -= count[1];
        table['e'] -= count[1];
        
        count[9] = table['n'] / 2;
        table['n'] = 0;
        table['i'] -= count[9];
        table['e'] -= count[9];
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < count[i]; ++j)
                builder.append(i);
        }
        
        return builder.toString();
    }
}
