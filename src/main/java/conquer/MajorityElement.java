import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MajorityElement {
  public static int getMajorityElement(int[] a, int left, int right) {
    if (left == right) {
      return -1;
    }
    if (left + 1 == right) {
      return a[left];
    }

    int mid = left + (right - left) / 2;
    int leftResult = getMajorityElement(a, left, mid);
    int rightResult = getMajorityElement(a, mid, right);

    if (leftResult == -1 && rightResult == -1) {
      return -1;
    }

    if (leftResult == rightResult) {
      return leftResult;
    }

    int x = estimatePotential(a, left, right, leftResult);
    return x >= 0 ? x : estimatePotential(a, left, right, rightResult);
  }

  private static int estimatePotential(int[] a, int left, int right, int num) {
    if (num < 0) {
      return -1;
    }
    int potential = 0;
    for (int i = left; i < right; i++) {
      if (a[i] == num) {
        potential++;
      }
    }
    return potential > (right - left) / 2 ? num : -1;
  }

  public static int brute(int[] a) {
    for (int x : a) {
      int count = count(a, x);
      if (count > a.length / 2) {
        return x;
      }
    }
    return -1;
  }

  public static int count(int[] a, int x) {
    int result = 0;
    for (int i : a) {
      if (i == x) {
        result++;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    FastScanner scanner = new FastScanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    if (getMajorityElement(a, 0, a.length) != -1) {
      System.out.println(1);
    } else {
      System.out.println(0);
    }

    //Random random = new Random();
    //while (true) {
    //  int[] ints = IntStream.generate(() -> random.nextInt(10)).limit(5).toArray();
    //  //int[] ints = { 4, 6, 6, 4, 6 };
    //  int brute = MajorityElement.brute(ints);
    //  int element = MajorityElement.getMajorityElement(ints, 0, ints.length);
    //  if (brute != element) {
    //    System.out.println(String.format("brute=%s, element=%s", brute, element));
    //    Arrays.stream(ints).forEach(i -> System.out.print(i + " "));
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

