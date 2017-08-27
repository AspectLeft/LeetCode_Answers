class Solution {
   
    public List<String> fullJustify(String[] words, int maxWidth) {
        StringBuilder builder = new StringBuilder();
        List<String> text = new ArrayList<>();
        int length = words.length;

        int i = 0, j = 0;
        int line = 0;

        while (i < length) {
            if (j < length && line + 1 + words[j].length() <= maxWidth + 1) {
                line += 1 + words[j].length();
                j++;
            }
            else if (j >= length) {
                for (int k = i; k < j; ++k) {
                    builder.append(words[k]);
                    builder.append(" ");
                }
                builder.delete(builder.length() - 1, builder.length());
                while (builder.length() < maxWidth) builder.append(" ");
                text.add(builder.toString());
                builder = new StringBuilder();
                i = length;
            }
            else {
                int spaceNum = maxWidth + 1 - line + j - i - 1;
                int spaceWidth;
                int odds;
                try {
                    spaceWidth = spaceNum / (j - i - 1);
                    odds = spaceNum % (j - i - 1);
                    for (int k = i; k < j - 1; ++k) {
                        builder.append(words[k]);
                        for (int l = 0; l < spaceWidth; ++l) builder.append(' ');
                        if (k - i < odds) builder.append(' ');
                    }
                    builder.append(words[j - 1]);
                }
                catch (ArithmeticException e) {
                    spaceWidth = spaceNum;
                    builder.append(words[j - 1]);
                    for (int l = 0; l < spaceWidth; ++l) builder.append(' ');
                }
                text.add(builder.toString());
                builder = new StringBuilder();
                i = j;
                line = 0;
            }
        }
        return text;
    }

}
