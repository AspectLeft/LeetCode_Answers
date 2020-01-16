class FileSystem {
    abstract class FileObj implements Comparable<FileObj> {
        protected String name;
        
        FileObj(String name) {
            this.name = name;
        }
        
        @Override
        public int compareTo(FileObj f2) {
            return name.compareTo(f2.name);
        }
        
        abstract public List<String> ls();
    }
    
    class File extends FileObj {
        private String content;
        
        File(String name, String content) {
            super(name);
            this.content = content;
        }
        
        public String addContent(String content) {
            this.content += content;
            return this.content;
        }
        
        public List<String> ls() {
            List<String> result = new ArrayList<>(1);
            result.add(this.name);
            return result;
        }
    }
    
    class Dir extends FileObj {
        TreeSet<FileObj> children;
        HashMap<String, FileObj> childrenMap;
        
        Dir(String name) {
            super(name);
            this.children = new TreeSet<>();
            this.childrenMap = new HashMap<>();
        }
        
        public List<String> ls() {
            List<String> result = new ArrayList<>();
            for (FileObj o: children) {
                result.add(o.name);
            }
            return result;
        }
        
        public void addChild(FileObj child) {
            children.add(child);
            childrenMap.put(child.name, child);
        }
    }
    
    private Dir root;

    public FileSystem() {
        root = new Dir("");
    }
    
    public List<String> ls(String path) {
        if (path.equals("/")) {
            return root.ls();
        }
        String[] names = path.split("/");
        Dir current = root;
        for (int i = 1; i < names.length - 1; ++i) {
            current = (Dir) current.childrenMap.get(names[i]);
        }
        return current.childrenMap.get(names[names.length - 1]).ls();
    }
    
    public void mkdir(String path) {
        String[] names = path.split("/");
        Dir current = root;
        Dir next;
        for (int i = 1; i < names.length; ++i) {
            next = (Dir) current.childrenMap.get(names[i]);
            if (next == null) {
                next = new Dir(names[i]);
                current.addChild(next);
            }
            current = next;
        }
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] names = filePath.split("/");
        Dir current = root;
        for (int i = 1; i < names.length - 1; ++i) {
            current = (Dir) current.childrenMap.get(names[i]);
        }
        String fileName = names[names.length - 1];
        File file = (File) current.childrenMap.get(fileName);
        if (file == null) {
            file = new File(fileName, content);
            current.addChild(file);
        }
        else {
            file.addContent(content);
        }
    }
    
    public String readContentFromFile(String filePath) {
        String[] names = filePath.split("/");
        Dir current = root;
        for (int i = 1; i < names.length - 1; ++i) {
            current = (Dir) current.childrenMap.get(names[i]);
        }
        String fileName = names[names.length - 1];
        File file = (File) current.childrenMap.get(fileName);
        return file.content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */
