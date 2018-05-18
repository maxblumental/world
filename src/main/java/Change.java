import java.util.Scanner;

public class Change {
  private static int getChange(int m) {
    int tens = m / 10;
    int units = m % 10;
    return tens + units / 5 + (units - (units / 5) * 5);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int m = scanner.nextInt();
    System.out.println(getChange(m));
  }
}

