import java.util.*;

public class FibonacciLastDigit {

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

  private static long getFibonacciLastDigitNaive(long n) {
    long period = period(10);
    long i = n % period;
    return calcFibMod(i, 10);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long n = scanner.nextLong();
    long c = getFibonacciLastDigitNaive(n);
    System.out.println(c);
  }
}

