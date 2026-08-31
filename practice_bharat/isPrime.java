public class isPrime {
    public static void main(String[] args) {
        int a = 173;
        boolean isPrime = true;
        for (int i = 2; i < a; i++) {
            if (a % i == 0) {
                isPrime = false;
                break;
            }
        }

        System.out.println(isPrime);
    }
}
