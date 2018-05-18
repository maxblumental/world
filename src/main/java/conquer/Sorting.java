import java.io.*;
import java.util.*;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class Sorting {
  private static Random random = new Random(3);

  private static int[] partition3(int[] a, int l, int r) {
    int m1 = l;
    int m2 = 0;
    int x = a[l];

    for (int i = l + 1; i <= r; i++) {
      if (a[i] < x) {
        m1++;
        swap(a, i, m2 + m1);
        swap(a, m1, m1 + m2);
      } else if (a[i] == x) {
        m2++;
        swap(a, m1 + m2, i);
      }
    }
    swap(a, l, m1);

    return new int[] { m1, m1 + m2 };
  }

  private static int partition2(int[] a, int l, int r) {
    int x = a[l];
    int j = l;
    for (int i = l + 1; i <= r; i++) {
      if (a[i] <= x) {
        j++;
        swap(a, i, j);
      }
    }
    swap(a, l, j);
    return j;
  }

  private static void randomizedQuickSort(int[] a, int l, int r) {
    if (l >= r) {
      return;
    }
    int k = random.nextInt(r - l + 1) + l;
    swap(a, l, k);
    //use partition3
    int[] m = partition3(a, l, r);
    randomizedQuickSort(a, l, m[0]);
    randomizedQuickSort(a, m[1] + 1, r);
  }

  private static void randomizedQuickSort1(int[] a, int l, int r) {
    if (l >= r) {
      return;
    }
    int k = random.nextInt(r - l + 1) + l;
    swap(a, l, k);
    //use partition3
    int m = partition2(a, l, r);
    randomizedQuickSort(a, l, m - 1);
    randomizedQuickSort(a, m + 1, r);
  }

  private static void swap(int[] a, int l, int k) {
    int t = a[l];
    a[l] = a[k];
    a[k] = t;
  }

  public static void main(String[] args) {
    FastScanner scanner = new FastScanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    //int[] a = { 9, 4, 8, 4, 2 };
    //int n = a.length;
    randomizedQuickSort(a, 0, n - 1);
    for (int i = 0; i < n; i++) {
      System.out.print(a[i] + " ");
    }

    //while (true) {
    //  Random random = new Random();
    //  int[] ints = IntStream.generate(new IntSupplier() {
    //    @Override public int getAsInt() {
    //      return random.nextInt(10);
    //    }
    //  }).limit(5).toArray();
    //
    //  int[] ints1 = Arrays.copyOf(ints, ints.length);
    //  int[] ints2 = Arrays.copyOf(ints, ints.length);
    //  randomizedQuickSort(ints, 0, ints.length - 1);
    //  randomizedQuickSort1(ints1, 0, ints1.length - 1);
    //  if (!Arrays.equals(ints, ints1)) {
    //    Arrays.stream(ints2)
    //        .forEach(value -> System.out.println(value + " "));
    //    break;
    //  }
    //}
  }

  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;

    FastScanner(InputStream stream) {
      try {
        br = new BufferedReader(new InputStreamReader(stream));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}

