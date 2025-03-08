public class Recurrence {
    public static void main(String[] args) {
        recur(10);
    }

    public static void recur(int k) {
        if (k == 0) {
            return;
        }
        System.out.println("K is: " + k);
        recur(k - 1);
    }
}