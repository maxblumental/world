import java.util.Arrays;
import java.util.Scanner;

public class LargestNumber {
  public static String largestNumber(String[] a) {
    Arrays.asList(a).sort((o1, o2) -> (o2 + o1).compareTo(o1 + o2));
    return Arrays.stream(a)
        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
        .toString();
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    String[] a = new String[n];
    for (int i = 0; i < n; i++) {
      a[i] = scanner.next();
    }
    System.out.println(largestNumber(a));
  }
}

