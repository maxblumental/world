import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DotProduct {
  private static long maxDotProduct(int[] a, int[] b) {
    //write your code here
    long result = 0;
    List<Integer> list1 = Arrays.stream(a).boxed().sorted().collect(Collectors.toList());
    List<Integer> list2 = Arrays.stream(b).boxed().sorted().collect(Collectors.toList());
    for (int i = 0; i < a.length; i++) {
      result += ((long) list1.get(i)) * ((long) list2.get(i));
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.nextInt();
    }
    int[] b = new int[n];
    for (int i = 0; i < n; i++) {
      b[i] = scanner.nextInt();
    }
    System.out.println(maxDotProduct(a, b));
  }
}

