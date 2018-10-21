class Solution {
    public String largestNumber(int[] nums) {
        String[] a = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            a[i] = Integer.toString(nums[i]);
        }
        Arrays.sort(a, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char a1, b;
                for (int i = 0; i < o1.length() && i < o2.length(); ++i) {
                    a1 = o1.charAt(i);
                    b = o2.charAt(i);
                    if (a1 < b) return -1;
                    if (a1 > b) return 1;
                }
                if (o1.length() == o2.length()) return 0;
                if (o1.length() > o2.length()) {
                    return compare(o1.substring(o2.length()), o2);
                }
                else return compare(o1, o2.substring(o1.length()));
            }
        });
        if (a[a.length - 1].equals("0")) return "0";
        StringBuilder builder = new StringBuilder();
        for (int i = nums.length - 1; i >= 0; --i) {
            builder.append(a[i]);
        }
        return builder.toString();
    }
}
