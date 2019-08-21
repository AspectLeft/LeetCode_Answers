class Solution {
    public int findLUSlength(String[] strs) {
        List<List<String>> buckets = new ArrayList<>();
        for (int l = 0; l < 11; ++l) {
            buckets.add(new ArrayList<>());
        }
        for (String str: strs) {
            buckets.get(str.length()).add(str);
        }
        for (int al = 10; al >= 0; --al) {
            List<String> bucket = buckets.get(al);
            for (int i = 0; i < bucket.size(); ++i) {
                String a = bucket.get(i);
                boolean flag = false;
                for (int bl = 10; bl > al; --bl) {
                    for (String b: buckets.get(bl)) {
                        if (isSubseq(a, b)) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
                if (flag) continue;
                for (int j = 0; j < bucket.size(); ++j) {
                    if (i == j) continue;
                    if (a.equals(bucket.get(j))) {
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                return al;
            }
        }
        return -1;
        
    }
    
    private boolean isSubseq(String a, String b) {
        if (a.length() == 0) return true;
        int i = 0, j = 0;
        while (i < a.length()) {
            while (j < b.length() && a.charAt(i) != b.charAt(j)) j++;
            if (j == b.length()) return false;
            i++;
            j++;
        }
        return true;
    }
}
