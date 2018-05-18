import java.util.*;

public class DifferentSummands {
  private static List<Integer> optimalSummands(int n) {
    List<Integer> summands = new ArrayList<>();
    int l = 1;
    while (true) {
      if (n <= 2 * l) {
        summands.add(n);
        break;
      } else {
        n -= l;
        summands.add(l);
        l++;
      }
    }
    return summands;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    List<Integer> summands = optimalSummands(n);
    System.out.println(summands.size());
    for (Integer summand : summands) {
      System.out.print(summand + " ");
    }
  }
}

