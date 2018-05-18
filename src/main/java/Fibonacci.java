import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
    if (n <= 1) {
      return n;
    }
    long a = 0, b = 1, c;
    int i = 1;
    while (i < n) {
      c = a;
      a = b;
      b = b + c;
      i++;
    }
    return b;
  }

  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
