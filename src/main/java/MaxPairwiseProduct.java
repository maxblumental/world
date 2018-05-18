import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
  static long getMaxPairwiseProduct(int[] numbers) {
    long max = numbers[0];
    int iMax = 0;
    for (int i = 1; i < numbers.length; i++) {
      if (numbers[i] > max) {
        max = numbers[i];
        iMax = i;
      }
    }
    long secondMax = 0;
    for (int i = 0; i < numbers.length; i++) {
      if (i == iMax) {
        continue;
      }
      if (numbers[i] > secondMax) {
        secondMax = numbers[i];
      }
    }
    return max * secondMax;
  }

  public static void main(String[] args) {
    FastScanner scanner = new FastScanner(System.in);
    int n = scanner.nextInt();
    int[] numbers = new int[n];
    for (int i = 0; i < n; i++) {
      numbers[i] = scanner.nextInt();
    }
    System.out.println(getMaxPairwiseProduct(numbers));
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