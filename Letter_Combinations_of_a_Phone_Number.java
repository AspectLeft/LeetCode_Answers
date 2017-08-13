public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();
        if (digits == null || digits.equals(""))
            return results;
        results.add("");

        int length = digits.length();
        char c;
        for (int i = 0; i < length; ++i) {
            List<String> newResults = new ArrayList<>();
            c = digits.charAt(i);
            switch (c) {
                case '2':
                    for (String s : results) {
                        newResults.add(s + "a");
                        newResults.add(s + "b");
                        newResults.add(s + "c");
                    }
                    break;
                case '3':
                    for (String s : results) {
                        newResults.add(s + "d");
                        newResults.add(s + "e");
                        newResults.add(s + "f");
                    }
                    break;
                case '4':
                    for (String s : results) {
                        newResults.add(s + "g");
                        newResults.add(s + "h");
                        newResults.add(s + "i");
                    }
                    break;
                case '5':
                    for (String s : results) {
                        newResults.add(s + "j");
                        newResults.add(s + "k");
                        newResults.add(s + "l");
                    }
                    break;
                case '6':
                    for (String s : results) {
                        newResults.add(s + "m");
                        newResults.add(s + "n");
                        newResults.add(s + "o");
                    }
                    break;
                case '7':
                    for (String s : results) {
                        newResults.add(s + "p");
                        newResults.add(s + "q");
                        newResults.add(s + "r");
                        newResults.add(s + "s");
                    }
                    break;
                case '8':
                    for (String s : results) {
                        newResults.add(s + "t");
                        newResults.add(s + "u");
                        newResults.add(s + "v");
                    }
                    break;
                case '9':

                    for (String s : results) {
                        newResults.add(s + "w");
                        newResults.add(s + "x");
                        newResults.add(s + "y");
                        newResults.add(s + "z");
                    }
                    break;
                default:
                    for (String s : results) {
                        newResults.add(s);
                    }
                    break;
            }
            results = newResults;

        }
        return results;
    }
}
