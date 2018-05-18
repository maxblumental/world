package dynamic;

import java.util.Scanner;

public class ChangeDP {

  static int getChange(int m) {
    int[] amounts = new int[m];
    amounts[0] = 1;
    for (int i = 1; i < m; i++) {
      int minAmount = amounts[i - 1] + 1;
      if (i == 2 || i == 3) {
        amounts[i] = 1;
        continue;
      }
      if (i - 4 >= 0) {
        minAmount = Math.min(amounts[i - 4] + 1, minAmount);
      }
      if (i - 3 >= 0) {
        minAmount = Math.min(amounts[i - 3] + 1, minAmount);
      }
      amounts[i] = minAmount;
    }
    return amounts[m - 1];
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int m = scanner.nextInt();
    System.out.println(getChange(m));
  }
}

