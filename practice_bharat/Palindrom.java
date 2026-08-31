public class Palindrom {
    public static void main(String[] args) {

        /* @ FOR STRING */
        // String s = "Bharat";
        // String copy = s;

        // int i = 0;
        // int j = s.length() - 1;
        // boolean flag = false;

        // while (i < j) {
        // if (s.charAt(i) == s.charAt(j)) {
        // flag = true;
        // } else {
        // flag = false;
        // }
        // i++;
        // j--;
        // }
        // System.out.println(flag);

        /* @ FOR NUMBER */
        int a = 121;
        int copy = a;
        int rev = 0;

        while (a > 0) {
            int temp = a % 10;
            rev = rev * 10 + temp;
            a /= 10;
        }

        System.out.println(a);
        System.out.println(copy == rev ? "Palindrom" : "Not Palindrom ");

    }
}
