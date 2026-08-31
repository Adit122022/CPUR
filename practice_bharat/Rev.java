public class Rev {
    public static void main(String[] args) {
        String s = "Bharat";
        String rev = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            rev += s.charAt(i);
        }
        System.out.print(rev);
    }
}
