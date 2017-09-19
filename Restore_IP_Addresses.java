class Solution {
    
    private String s;
    private List<String> list = new ArrayList<>();
    private String[] fields = new String[4];

    private void dfs(int field, int offset) {

            for (int i = 1; i <= 3; ++i) {
                try {
                    String sub = s.substring(offset, offset + i);
                    if (sub.length() > 1 && sub.charAt(0) == '0') continue;
                    int n = Integer.valueOf(sub);
                    if (n >= 0 && n <= 255) {
                        fields[field] = sub;
                        if (field < 3) {
                            dfs(field + 1, offset + i);
                        }
                        else {
                            if (offset + i == s.length()) {
                                list.add(fields[0] + "." + fields[1] + "." + fields[2] + "." + fields[3]);
                            }
                        }
                    }

                }
                catch (StringIndexOutOfBoundsException e) {
                    break;
                }
            }


    }

    public List<String> restoreIpAddresses(String s) {
        this.s = s;
        dfs(0, 0);
        return list;
    }

}
