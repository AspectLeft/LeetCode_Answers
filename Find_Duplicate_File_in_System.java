class Solution {
    class TextFile {
        String path;
        String name;
        String content;
        
        TextFile(String p, String n, String c) {
            this.path = p;
            this.name = n;
            this.content = c;
        }
    }
    
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<TextFile>> map = new HashMap<>();
        for (String dir: paths) {
            String[] splitted = dir.split(" ");
            String path = splitted[0];
            for (int i = 1; i < splitted.length; ++i) {
                int start = splitted[i].indexOf('(');
                String name = splitted[i].substring(0, start);
                String content = splitted[i].substring(start);
                TextFile file = new TextFile(path, name, content);
                map.computeIfAbsent(content, k -> new ArrayList<>());
                map.get(content).add(file);
            }
        }
        List<List<String>> output = new ArrayList<>();
        for (String content: map.keySet()) {
            List<TextFile> list = map.get(content);
            if (list.size() > 1) {
                output.add(list.stream().map(file -> file.path + '/' + file.name)
                           .collect(Collectors.toList()));
            }
        }
        return output;
    }
}
