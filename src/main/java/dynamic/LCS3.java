package dynamic;

import java.util.Scanner;

import static java.lang.Math.max;

public class LCS3 {

  static int lcs3(int[] a, int[] b, int[] c) {
    int[][][] cube = new int[a.length + 1][b.length + 1][c.length + 1];
    for (int i = 1; i < a.length + 1; i++) {
      for (int j = 1; j < b.length + 1; j++) {
        for (int k = 1; k < c.length + 1; k++) {
          int x1 = cube[i - 1][j][k];
          int x2 = cube[i][j - 1][k];
          int x3 = cube[i][j][k - 1];
          int y1 = cube[i - 1][j - 1][k];
          int y2 = cube[i][j - 1][k - 1];
          int y3 = cube[i - 1][j][k - 1];
          int z = cube[i - 1][j - 1][k - 1];
          int diagonal = a[i - 1] == b[j - 1] && b[j - 1] == c[k - 1] ? z + 1 : z;
          int maxX = max(x1, max(x2, x3));
          int maxY = max(y1, max(y2, y3));
          cube[i][j][k] = max(diagonal, max(maxX, maxY));
        }
      }
    }
    return cube[a.length][b.length][c.length];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int an = scanner.nextInt();
    int[] a = new int[an];
    for (int i = 0; i < an; i++) {
      a[i] = scanner.nextInt();
    }
    int bn = scanner.nextInt();
    int[] b = new int[bn];
    for (int i = 0; i < bn; i++) {
      b[i] = scanner.nextInt();
    }
    int cn = scanner.nextInt();
    int[] c = new int[cn];
    for (int i = 0; i < cn; i++) {
      c[i] = scanner.nextInt();
    }
    System.out.println(lcs3(a, b, c));
  }
}

