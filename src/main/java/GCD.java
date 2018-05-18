import java.util.*;

public class GCD {
  private static int gcdNaive(int a, int b) {
    if (a < b) {
      return gcdNaive(b, a);
    }
    if (a == 0 || b == 0) {
      return a == 0 ? b : a;
    }
    return gcdNaive(b, a % b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    System.out.println(gcdNaive(a, b));
  }
}
