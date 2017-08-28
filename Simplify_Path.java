class Solution {
    
    public String simplifyPath(String path) {
        StringBuilder builder = new StringBuilder();
        String[] dirs = path.split("/");
        List<String> list = new LinkedList<>();
        for (String dir: dirs) {
            if (dir.equals(".") || dir.equals(""))
                continue;
            
            if (dir.equals("..")) {
                if (!list.isEmpty())
                    list.remove(list.size() - 1);
            }
            else list.add(dir);
        }
        if (list.isEmpty()) 
            return "/";
        else {
            for (String dir: list) {
                builder.append("/");
                builder.append(dir);
            }
        }
        return builder.toString();
    }

}
