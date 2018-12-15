class Solution {
    StringBuilder builder = new StringBuilder();
    
    String[] ones = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                    "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
                    "Eighteen", "Nineteen"};
    String[] tens = {"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    private void hundred(int n) {
        if (n >= 100)  {
            builder.append(ones[n / 100]);
            builder.append(" Hundred ");
            n = n % 100;
        }
        if (n >= 10 && n <= 19) {
            builder.append(ones[n]);
            builder.append(" ");
            return;
        }
        if (n >= 20) {
            builder.append(tens[n / 10]);
            builder.append(" ");
            n = n % 10;
        }
        if (n > 0) {
            builder.append(ones[n]);
            builder.append(" ");
        }
    }
    
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        if (num >= 1000000000) {
            hundred(num / 1000000000);
            builder.append("Billion ");
        }
        num = num % 1000000000;
        if (num >= 1000000) {
            hundred(num / 1000000);
            builder.append("Million ");
        }
        num = num % 1000000;
        if (num >= 1000) {
            hundred(num / 1000);
            builder.append("Thousand ");
        }
        num = num % 1000;
        hundred(num);
        
        builder.deleteCharAt(builder.length() - 1);
        
        return builder.toString();
    }
}
