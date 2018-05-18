import java.util.*;

public class FibonacciSumLastDigit {

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

  private static long getFibonacciSumNaive(long n) {
    if (n <= 1) {
      return n;
    }

    long sum = getFibonacciLastDigitNaive(n + 2) - 1;

    return sum < 0 ? 9 : sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long n = scanner.nextLong();
    long s = getFibonacciSumNaive(n);
    System.out.println(s);
  }
}

