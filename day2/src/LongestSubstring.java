public class LongestSubstring {
    public static void main(String[] args) {
        String s="abcfxzyvwqfgabc";
        longestSubstring(s);
    }
    public static void  longestSubstring(String str) {
        for (int first = 0; first <= str.length(); first++) {

            for (int i = first+1; i < str.length(); i++) {
                if (str.charAt(first) == str.charAt(i)) {

                    if ((i + 1) > (str.length() - i - 1)) {
                        str = str.substring(0, i);
//                        i = fi;
                    } else {
                        str = str.substring(i, str.length());
//                        i = 0;
                    }
                }

            }



        }
        System.out.println(str);
    }

}
