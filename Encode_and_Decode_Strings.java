public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null || strs.size() == 0) return "";
        StringBuilder builder = new StringBuilder();
        for (String str: strs) {
            if (str.equals("")) {
                builder.append("e");
            }
            else {
                builder.append(str.replace(" ", "\\ ").replace("e", "\\e"));
            }
            builder.append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        List<String> output = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            if (i < s.length() - 1 && s.charAt(i) == '\\') {
                if (s.charAt(i + 1) == ' ') {
                    builder.append(' ');
                    i++;
                    continue;
                }
                else if (s.charAt(i + 1) == 'e') {
                    builder.append('e');
                    i++;
                    continue;
                }
            }
            if (s.charAt(i) == ' ') {
                output.add(builder.toString());
                builder = new StringBuilder();
                continue;
            }
            if (s.charAt(i) == 'e') {
                continue;
            }
            builder.append(s.charAt(i));
        }
        output.add(builder.toString());
        return output;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs));
