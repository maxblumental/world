import java.util.*;

public class LCM {

  private static int gcdNaive(int a, int b) {
    if (a < b) {
      return gcdNaive(b, a);
    }
    if (a == 0 || b == 0) {
      return a == 0 ? b : a;
    }
    return gcdNaive(b, a % b);
  }

  private static long lcmNaive(int a, int b) {
    int gcd = gcdNaive(a, b);
    return (long) (a / gcd) * b;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(lcmNaive(a, b));
  }
}
