public class Solution {
    public String intToRoman(int num) {
        String roman = "";
        int dec0, dec1, dec2, dec3;
        dec0 = num % 10;
        num /= 10;
        dec1 = num % 10;
        num /= 10;
        dec2 = num % 10;
        num /= 10;
        dec3 = num % 10;
        for (int i = 0; i < dec3; ++i) {
            roman += "M";
        }
        switch (dec2) {
            case 4:
                roman += "CD";
                break;
            case 9:
                roman += "CM";
                break;
            default:
                if (dec2 > 4) {
                    roman += "D";
                }
                for (int i = 0; i < dec2 % 5; ++i) {
                    roman += "C";
                }
                break;
        }
        switch (dec1) {

            case 4:
                roman += "XL";
                break;
            case 9:
                roman += "XC";
                break;
            default:
                if (dec1 > 4) {
                    roman += "L";
                }
                for (int i = 0; i < dec1 % 5; ++i) {
                    roman += "X";
                }
                break;
        }

        switch (dec0) {

            case 4:
                roman += "IV";
                break;
            case 9:
                roman += "IX";
                break;
            default:
                if (dec0 > 4) {
                    roman += "V";
                }
                for (int i = 0; i < dec0 % 5; ++i) {
                    roman += "I";
                }
                break;
        }


        return roman;
    }
}
