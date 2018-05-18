import java.util.Scanner;

public class FibonacciHuge {

  private static long calcFibMod(long n, long m) {
    if (n <= 1) {
      return n;
    }
    long a = 0;
    long b = 1;
    long c;
    long i = 1;
    while (i < n) {
      c = a;
      a = b;
      b = (b + c) % m;
      i++;
    }
    return b;
  }

  private static long getFibonacciHuge(long n, long m) {
    if (n <= 1) {
      return n;
    }

    long period = period(m);
    long i = n % period;
    return calcFibMod(i, m);
  }

  private static long period(long m) {
    long a = 1 % m;
    long b = 2 % m;
    long i = 2;
    long tmp;
    while (true) {
      if (a == 0 && b == 1) {
        break;
      }
      i++;
      tmp = a;
      a = b;
      b = (a + tmp) % m;
    }
    return i;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long n = scanner.nextLong();
    long m = scanner.nextLong();
    System.out.println(getFibonacciHuge(n, m));
  }
}

