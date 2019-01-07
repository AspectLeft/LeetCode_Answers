class Solution {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return true;
        int i = 0;
        while (i < data.length) {
            int b = data[i];
            if ((b & 0x80) == 0) {
                i++;
            }
            else if ((b & 0x40) == 0)
                return false;
            else if ((b & 0x20) == 0) {
                if (i + 1 >= data.length) return false;
                if ((data[i + 1] & 0xc0) != 0x80) return false;
                i += 2;
            }
            else if ((b & 0x10) == 0) {
                if (i + 2 >= data.length) return false;
                if ((data[i + 1] & 0xc0) != 0x80 || (data[i + 2] & 0xc0) != 0x80) return false;
                i += 3;
            }
            else if ((b & 0x8) == 0) {
                if (i + 3 >= data.length) return false;
                if ((data[i + 1] & 0xc0) != 0x80 || (data[i + 2] & 0xc0) != 0x80
                   || (data[i + 3] & 0xc0) != 0x80) return false;
                i += 4;
            }
            else return false;
        }
        
        
        return true;
    }
}
