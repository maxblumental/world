package dynamic;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PlacingParentheses {
  static long getMaximValue(String exp) {
    List<Character> operators = Arrays.stream(exp.split("[^+*-]+"))
        .skip(1)
        .map(s -> s.charAt(0))
        .collect(Collectors.toList());
    long[] digits = Arrays.stream(exp.split("[^\\d]+"))
        .mapToLong(s -> (long) s.charAt(0) - '0')
        .toArray();
    return maximize(operators, digits);
  }

  private static long maximize(List<Character> operators, long[] digits) {
    int n = digits.length;
    long[][] m = new long[n][n];
    long[][] M = new long[n][n];

    for (int i = 0; i < n; i++) {
      m[i][i] = digits[i];
      M[i][i] = digits[i];
    }

    for (int s = 1; s < n; s++) {
      for (int i = 0; i < n - s; i++) {
        int j = i + s;
        long[] minAndMax = minAndMax(i, j, m, M, operators);
        m[i][j] = minAndMax[0];
        M[i][j] = minAndMax[1];
      }
    }

    return M[0][n - 1];
  }

  private static long[] minAndMax(int i, int j, long[][] m, long[][] M, List<Character> operators) {
    long min = Long.MAX_VALUE;
    long max = Long.MIN_VALUE;
    for (int k = i; k < j; k++) {
      Character op = operators.get(k);
      long a = eval(M[i][k], M[k + 1][j], op);
      long b = eval(M[i][k], m[k + 1][j], op);
      long c = eval(m[i][k], M[k + 1][j], op);
      long d = eval(m[i][k], m[k + 1][j], op);
      min = min(min, min(a, min(b, min(c, d))));
      max = max(max, max(a, max(b, max(c, d))));
    }
    return new long[] { min, max };
  }

  private static long eval(long a, long b, char op) {
    if (op == '+') {
      return a + b;
    } else if (op == '-') {
      return a - b;
    } else if (op == '*') {
      return a * b;
    } else {
      assert false;
      return 0;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String exp = scanner.next();
    System.out.println(getMaximValue(exp));
  }
}

