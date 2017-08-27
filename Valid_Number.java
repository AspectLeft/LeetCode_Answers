class Solution {
    
    public boolean isNumber(String s) {
        return s.trim().matches("[-+]?([0-9]+[.]?[0-9]*|[.]?[0-9]+)(e[-+]?[0-9]+)?");
    }
}
