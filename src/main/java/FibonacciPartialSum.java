import java.util.*;

public class FibonacciPartialSum {

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

  private static long getFibonacciPartialSumNaive(long from, long to) {
    if (to <= 1) {
      return to;
    }
    long sum = getFibonacciLastDigitNaive(to + 2) - getFibonacciLastDigitNaive(from + 1);

    return sum < 0 ? 10 + sum : sum;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long from = scanner.nextLong();
    long to = scanner.nextLong();
    System.out.println(getFibonacciPartialSumNaive(from, to));
  }
}

