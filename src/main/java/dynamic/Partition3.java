package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Partition3 {

  static int partition3(int[] A) {
    int sum = Arrays.stream(A).sum();
    if (sum % 3 != 0) {
      return 0;
    }
    Arrays.sort(A);
    List<HashMap<Tuple, Boolean>> list = IntStream.range(0, A.length + 1)
        .mapToObj((IntFunction<HashMap<Tuple, Boolean>>) value -> new HashMap<>())
        .collect(Collectors.toList());
    Tuple tuple = new Tuple(sum / 3, sum / 3, sum / 3);
    int n = A.length;
    return canBeSplit(n, tuple, list, A) ? 1 : 0;
  }

  private static boolean canBeSplit(int n, Tuple tuple,
                                    List<HashMap<Tuple, Boolean>> list, int[] A) {
    if (n == 0) {
      list.get(n).put(tuple, tuple.isNull());
    } else if (n == 1) {
      boolean result = tuple.a == A[n - 1] && tuple.b == 0 && tuple.c == 0;
      list.get(n).put(tuple, result);
      return result;
    }

    int v = A[n - 1];

    if (tuple.a < v) {
      list.get(n).put(tuple, false);
      return false;
    }

    Tuple first = new Tuple(tuple.a - v, tuple.b, tuple.c);
    Tuple second = first;
    Tuple third = first;

    if (tuple.b >= v) {
      second = new Tuple(tuple.a, tuple.b - v, tuple.c);
    }

    if (tuple.c >= v) {
      third = new Tuple(tuple.a, tuple.b, tuple.c - v);
    }

    HashMap<Tuple, Boolean> prevMap = list.get(n - 1);

    prevMap.putIfAbsent(first, canBeSplit(n - 1, first, list, A));
    prevMap.putIfAbsent(second, canBeSplit(n - 1, second, list, A));
    prevMap.putIfAbsent(third, canBeSplit(n - 1, third, list, A));

    return prevMap.get(first) || prevMap.get(second) || prevMap.get(third);
  }

  static class Tuple {
    private int a;
    private int b;
    private int c;

    Tuple(int a, int b, int c) {
      this.a = a;
      this.b = b;
      this.c = c;
      sort();
    }

    private void sort() {
      int tmp;
      if (b < c) {
        tmp = c;
        c = b;
        b = tmp;
      }
      if (a < b) {
        tmp = a;
        a = b;
        b = tmp;
      }
      if (b < c) {
        tmp = c;
        c = b;
        b = tmp;
      }
    }

    boolean isNull() {
      return a == 0 && b == 0 && c == 0;
    }

    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Tuple tuple = (Tuple) o;
      return a == tuple.a && b == tuple.b && c == tuple.c;
    }

    @Override public int hashCode() {
      int result = 1;
      result = 31 * result + a;
      result = 31 * result + b;
      return 31 * result + c;
    }
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = scanner.nextInt();
    }
    System.out.println(partition3(A));
  }
}

