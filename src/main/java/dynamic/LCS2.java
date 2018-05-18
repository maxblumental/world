package dynamic;

import java.util.Scanner;

import static java.lang.Integer.max;

public class LCS2 {

  static int lcs2(int[] s, int[] t) {
    int l1 = s.length;
    int l2 = t.length;
    int[][] matrix = new int[l1 + 1][l2 + 1];

    for (int i = 0; i < l1 + 1; i++) {
      matrix[i][0] = 0;
    }

    for (int i = 0; i < l2 + 1; i++) {
      matrix[0][i] = 0;
    }

    for (int i = 1; i < l1 + 1; i++) {
      for (int j = 1; j < l2 + 1; j++) {
        int insertion = matrix[i][j - 1];
        int deletion = matrix[i - 1][j];
        int behind = matrix[i - 1][j - 1];
        int diagonal = s[i - 1] == t[j - 1] ? behind + 1 : behind;
        matrix[i][j] = max(max(insertion, deletion), diagonal);
      }
    }

    return matrix[l1][l2];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }

    int m = scanner.nextInt();
    int[] b = new int[m];
    for (int i = 0; i < m; i++) {
      b[i] = scanner.nextInt();
    }

    System.out.println(lcs2(a, b));
  }
}

