package dynamic;

import java.util.*;

import static java.lang.Math.min;

class EditDistance {

  static int EditDistance(String s, String t) {
    int l1 = s.length();
    int l2 = t.length();
    int[][] matrix = new int[l1 + 1][l2 + 1];

    for (int i = 0; i < l1 + 1; i++) {
      matrix[i][0] = i;
    }

    for (int i = 0; i < l2 + 1; i++) {
      matrix[0][i] = i;
    }

    for (int i = 1; i < l1 + 1; i++) {
      for (int j = 1; j < l2 + 1; j++) {
        int insertion = matrix[i][j - 1] + 1;
        int deletion = matrix[i - 1][j] + 1;
        int behind = matrix[i - 1][j - 1];
        int diagonal = s.charAt(i - 1) == t.charAt(j - 1) ? behind : behind + 1;
        matrix[i][j] = min(min(insertion, deletion), diagonal);
      }
    }

    return matrix[l1][l2];
  }

  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }
}
