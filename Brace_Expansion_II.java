class Solution {
    public List<String> braceExpansionII(String exp) {
        if (exp.length() == 1) return Collections.singletonList(exp);
        List<String> expProduct = new ArrayList<>();
        for (int i = 0; i < exp.length(); ++i) {
            StringBuilder builder = new StringBuilder();
            char c = exp.charAt(i);
            builder.append(c);
            if (c == '{') {
                int balance = 1;
                int j = i;
                while (balance != 0) {
                    j++;
                    c = exp.charAt(j);
                    if (c == '}') {
                        balance--;
                    }
                    if (c == '{') {
                        balance++;
                    }
                    builder.append(c);
                }
                i = j;
            }
            expProduct.add(builder.toString());
        }
        if (expProduct.size() == 1) {
            List<String> expList = new ArrayList<>();
            for (int i = 1; i < exp.length() - 1; ++i) {
                if (exp.charAt(i) == ',') continue;
                StringBuilder builder = new StringBuilder();
                char c;
                int balance = 0;
                int j = i;
                while (!(balance == 0 && (j == exp.length() - 1 || exp.charAt(j) == ','))) {
                    c = exp.charAt(j);
                    if (c == '}') {
                        balance--;
                    }
                    if (c == '{') {
                        balance++;
                    }
                    builder.append(c);
                    j++;
                }
                
                i = j;
                expList.add(builder.toString());
            }
            
            Set<String> union = new HashSet<>();
            for (String e: expList) {
                union.addAll(braceExpansionII(e));
            }
            List<String> result = new ArrayList<String>(union);
            Collections.sort(result);
            return result;
        }
        else {
            List<List<String>> valProduct = new ArrayList<>();
            for (String e: expProduct) {
                valProduct.add(braceExpansionII(e));
            }
            //System.out.println(valProduct);
            List<String> result = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            dfs(builder, 0, valProduct, result);
            Collections.sort(result);
            return result;
        }
    }
    
    
    private void dfs(StringBuilder builder, int i, List<List<String>> dict, List<String> result) {
        if (i == dict.size()) {
            result.add(builder.toString());
            return;
        }
        for (String c: dict.get(i)) {
            builder.append(c);
            dfs(builder, i + 1, dict, result);
            builder.delete(builder.length() - c.length(), builder.length());
        }
    }
}
