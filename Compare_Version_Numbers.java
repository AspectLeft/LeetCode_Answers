class Solution {
    public int compareVersion(String version1, String version2) {
        String[] a1 = version1.split("\\."), a2 = version2.split("\\.");
        int i, v1, v2;
        for (i = 0; i < a1.length && i < a2.length; ++i) {
            v1 = Integer.parseInt(a1[i]);
            v2 = Integer.parseInt(a2[i]);
            if (v1 > v2) return 1;
            if (v1 < v2) return -1;
        }
        if (a1.length == a2.length) return 0;
        String[] a = a1.length > a2.length ? a1 : a2;
        for (; i < a.length; ++i) {
            if (Integer.parseInt(a[i]) > 0) return a == a1 ? 1 : -1;
        }
        return 0;
    }
}
